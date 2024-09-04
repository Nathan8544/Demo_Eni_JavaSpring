package eni.demo.demo.module4.ihm;

import eni.demo.demo.module4.bll.AlimentManager;
import eni.demo.demo.module4.bo.Aliment;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoFormController {

    @Autowired
    AlimentManager alimentManager;

    @GetMapping("show-aliment-form")
    public String showAlimentForm(Model model) {
        // Préparer ce que tu va envoyer dans le formulaire par défaut
        Aliment aliment = new Aliment(-1L, "");

        // Envoyer l'aliment dans le front (dans la réponse)
        // pour le mettre dans le formulaire
        model.addAttribute("aliment", aliment);

        // Afficher la page formulaire
        return "form/form-aliment-page";
    }

    /**
     * Traiter les données qui nous serons envoyé
     * @return
     */
    @PostMapping("show-aliment-form")
    public String alimentForm(@Valid @ModelAttribute Aliment aliment, BindingResult bindingResult) {
        // Objectif tester la validité de la donnée (controle de surface)
        if (bindingResult.hasErrors()){
            System.out.println("Erreur de contrôle surface");
        }

        // On sauvegarde l'aliment dans la BDD
        alimentManager.saveAliment(aliment);

        // Afficher la page formulaire
        return "form/form-aliment-page";
    }
}
