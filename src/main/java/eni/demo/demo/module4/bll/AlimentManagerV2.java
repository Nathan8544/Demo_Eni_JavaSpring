package eni.demo.demo.module4.bll;

import eni.demo.demo.module4.bo.Aliment;
import eni.demo.demo.module4.dao.IDAOAliment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlimentManagerV2 {

    @Autowired
    IDAOAliment daoAliment;

    public EniManagerResponse<List<Aliment>> getAliments() {

        // Récupérer les aliments de la DAO
        List<Aliment> aliments = daoAliment.selectAliments();

        // Cas 1 : Succès
        return EniManagerResponse.performResponse("200", "Les aliments ont été récupérés avec succès", aliments);
    }

    public EniManagerResponse<Aliment> getById(Long id) {
        // récupérer un aliment via la DAO
        Aliment aliment = daoAliment.selectAlimentById(id);

        // Cas 1 : Erreur 701
        if (aliment == null){
           return EniManagerResponse.performResponse("701", "Impossible de récupérer un aliment inexistant", null);
        }

        // Cas 2 : Succès
        return EniManagerResponse.performResponse("200", "L'aliment à été récupéré avec succès", aliment);
    }
}
