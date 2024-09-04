package eni.demo.demo.module4.ihm;

import eni.demo.demo.module4.bll.AlimentManager;
import eni.demo.demo.module4.bll.AlimentManagerV2;
import eni.demo.demo.module4.bll.EniManagerResponse;
import eni.demo.demo.module4.bo.Aliment;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DemoController {

    @Autowired
    AlimentManager alimentManager;

    @Autowired
    AlimentManagerV2 alimentManagerV2;

    @GetMapping(value={"chocolatine"})
    //@RolesAllowed()
    public String chocolatine() {

        // Retourne le nom du fichier HTML à charger
        // PS: A partir du dossier resources/templates
        // resources/templates est la racine des fichiers HTML
        return "todo";
    }

    // Dans l'annotion XXXMapping, le première paramètre => le nom de l'url
    // exemple "login" => http://monhost:monport/login
    // donc en localhost avec le port 8080 par défaut (en dev) => http://localhost:8080/login
    @GetMapping("login")
    public String showLoginForm(){
        // Retourne le nom du fichier HTML à charger
        // PS: A partir du dossier resources/templates
        // resources/templates est la racine des fichiers HTML
        return "login-page";
    }

    @GetMapping("show-aliments")
    public String showAliments(Model model){
        // V1 : Envoyer la liste d'aliments dans le Modèle
        //model.addAttribute("aliments", alimentManager.getAliments());



        // V2 : On récupère la réponse métier (controle métier)
        // Envoyer la liste d'aliments dans le Modèle
        EniManagerResponse<List<Aliment>> response = alimentManagerV2.getAliments();

        model.addAttribute("aliments", response.data);

        // Afficher la page
        // return "aliments-page";
        // return "v2/aliments-page-v2";
        return "v3/aliments-page-v3";
    }

    @GetMapping("show-aliment/{id}")
    public String showAliment(@PathVariable("id") Long id, Model model){
        // V1: Récupérer l'aliment via la manager avec comme paramètre l'id provenant de la requête (URL)
        //Aliment aliment = alimentManager.getById(id);

        // V2: Récupérer l'aliment via la manager avec comme paramètre l'id provenant de la requête (URL)
        EniManagerResponse<Aliment> response = alimentManagerV2.getById(id);

        // Tester si l'aliment n'existe (controle métier)
        if (response.code.equals("701")){
            // Afficher la page d'erreur qui s'appelle aliment-not-found
            return "aliment-not-found";
        }

        // Envoyer l'aliment trouvé dans la vue (dans le modèle)
        model.addAttribute("aliment", response.data);

        // Afficher la page detail aliment
        return "detail-aliment-page";
    }

    @GetMapping("demo-debug")
    public String showDemoDebug(){

        // Demo 1
        /*
        String pseudo1 = "Isaac";
        String pseudo2 = "SacréSacré";

        pseudo1 = pseudo2;

        pseudo1 = "Pas 16h30. :'( sniff. Bilan module va piquer pour la peine";
        */

        Aliment a1 = new Aliment(1L, "Chocolatine");
        Aliment a2 = new Aliment(2L, "Pain au chocolat");

        a1 = a2;

        a1.name = "Pizza Ananas Nutella Crevette";

        // Push en base des aliments
        return "todo";
    }
    @GetMapping("/")
    public String showHome(){
        return "/index";
    }
}
