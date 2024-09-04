package eni.demo.demo.module4.dao;

import eni.demo.demo.module4.bo.Aliment;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Profile("mock")
@Component
public class DAOAlimentMock implements IDAOAliment {

    // Initialiser une fausse liste d'aliments
    List<Aliment> aliments = Arrays.asList(
            new Aliment(1, "Chocolatine"),
            new Aliment(2, "Beurre Salé"));

    @Override
    public List<Aliment> selectAliments() {

        return aliments;
    }

    @Override
    public Aliment selectAlimentById(long id) {
        // Le code pour récuperer l'aliment selon l'id
        /*
        Aliment alimentToFound = null;
        for (Aliment aliment : aliments){
            if (aliment.id == id){
                alimentToFound = aliment;
                break;
            }
        }
        */

        // Filtrer avec un Predicate
        // .stream => possibilité d'etendre la manipulation de la liste
        // .filter => retourne les elements filtrés (qui respecte la condition)
        // .findFirst => car je veux forcer à avoir/retourner qu'un seul element
        // .get() => comme c'est nullable (Optionnal) je pars du principe qu'il n'est pas null pour l'instant
        Aliment alimentToFound = aliments.stream().filter(aliment -> aliment.id == id).findFirst().orElse(null);

        return alimentToFound;
    }

    @Override
    public void save(Aliment aliment) {
        // TODO
    }
}
