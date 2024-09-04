package eni.demo.demo.module4.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //@Bean
    public UserDetailsService userDetailsService() {
        String password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password");

        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select pseudo, password, 1 from UTILISATEUR Where pseudo = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select pseudo, role from ROLES Where pseudo = ?");
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/chocolatine").hasRole("EMPLOYE")
                        .requestMatchers("/show-aliments").hasRole("FORMATEUR")
                        .requestMatchers("/show-aliment/**").hasRole("FORMATEUR")
                        .requestMatchers("/demo-debug").hasAnyRole("EMPLOYE","FORMATEUR","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/show-aliment-form").hasAnyRole("EMPLOYE","FORMATEUR")
                        .requestMatchers(HttpMethod.POST,"/show-aliment-form").hasAnyRole("EMPLOYE","FORMATEUR")
                        .requestMatchers("/make-basket").hasRole("ADMIN")
                        .requestMatchers("/show-basket-2").hasRole("ADMIN")
                        .requestMatchers("/clear-basket").hasRole("ADMIN")
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/vendor/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").authenticated()
                        .anyRequest().denyAll()
                );
        http.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/"));
        HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL));
        http.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).logoutSuccessUrl("/login?logout").addLogoutHandler(clearSiteData));
        return http.build();
    }
}
