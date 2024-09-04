package eni.demo.demo.module4.ihm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Arrays;
import java.util.List;

@SessionAttributes({"basket"})
@Controller
public class DemoSessionController {

    @GetMapping("make-basket")
    public String makeBasket(Model model) {
        // generer de 3 produits
        List<String> basket = Arrays.asList(
                "Pinte de nutella",
                "Beurre de cacahuète",
                "Cassoulet oeuf");

        // Envoyer le panier dans le front (envoyer dans le context de la réponse)
        // Si "basket" est dans @SessionAttributes alors il sera en meme temps en session grace au model.addAttribute
        model.addAttribute("basket", basket);

        // affiche la page (qui affiche le panier
        return "basket/show-basket";
    }

    @GetMapping("show-basket-2")
    public String showBasket() {

        // affiche la page (qui affiche le panier)
        return "basket/show-basket";
    }

    @GetMapping("clear-basket")
    public String clearBasket(SessionStatus sessionStatus) {

        // Methode 1 : Se serait de supprimer individuellement un attribut de session
        // model.addAttribute("basket", null)

        // Methode 2 : Nettoyer toute la session
        sessionStatus.setComplete();

        // rediriger vers show-basket
        // car nettoyer une session c'est appliqué qu'aprés la réponse de cette url
        return "redirect:/show-basket-2";
    }
}
