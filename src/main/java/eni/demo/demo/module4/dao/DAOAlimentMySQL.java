package eni.demo.demo.module4.dao;

import eni.demo.demo.module4.bo.Aliment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Profile("mysql")
@Component
public class DAOAlimentMySQL implements IDAOAliment {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Le code qui permet de savoir comment convertir/mapper un resultat en SQL en Objet/Classe
     * Comment mapper un resultat SQL en Aliment
     */
    static final RowMapper<Aliment> ALIMENT_ROW_MAPPER = new RowMapper<Aliment>() {
        @Override
        public Aliment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Aliment aliment = new Aliment();

            aliment.id = rs.getLong("id");
            aliment.name = rs.getString("name");

            return aliment;
        }
    };

    @Override
    public List<Aliment> selectAliments() {
        // Execute la requete pour trouver tout les elements d'une table
        return jdbcTemplate.query("SELECT * FROM aliment", ALIMENT_ROW_MAPPER);
    }

    @Override
    public Aliment selectAlimentById(long id) {

        // Execute la requete pour trouver les elements d'une table selon la clause
        List<Aliment> aliments = jdbcTemplate.query("SELECT * FROM aliment WHERE id = ?", ALIMENT_ROW_MAPPER, id);

        // Si on trouve aucun element on retourne null
        if (aliments.size() == 0) {
            return null;
        }

        // Retourner le premier element
        return aliments.get(0);
    }

    @Override
    public void save(Aliment aliment) {
        // Tester si il existe en base, SI OUI => Update SINON => Insert
        if (aliment.id !=null && selectAlimentById(aliment.id) != null) {
            // Update en base un aliment
            jdbcTemplate.update("UPDATE aliment SET name = ? WHERE id = ?", aliment.name, aliment.id);

            // PS : Return = Arreter la fonction
            return;
        }
        // Insérer en base un aliment
        jdbcTemplate.update("INSERT INTO aliment(id, name) VALUES (?, ?)", aliment.id, aliment.name);
    }
}
