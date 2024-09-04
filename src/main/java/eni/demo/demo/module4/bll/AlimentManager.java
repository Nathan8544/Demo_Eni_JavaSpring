package eni.demo.demo.module4.bll;

import eni.demo.demo.module4.bo.Aliment;
import eni.demo.demo.module4.dao.IDAOAliment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlimentManager {

    @Autowired
    IDAOAliment daoAliment;

    /**
     * Le manager qui récupère la liste des Aliments
     * @deprecated La méthode sera supprimée dans une version future
     * Utilisez plutot la fonction {@link AlimentManagerV2#getAliments()}
     *
     * @return
     */
    @Deprecated
    public List<Aliment> getAliments() {
        // récupérer les aliments de la DAO

        return daoAliment.selectAliments();
    }

    public Aliment getById(long id) {
        // récupérer un aliment via la DAO

        return daoAliment.selectAlimentById(id);
    }

    /**
     * Appellera la DAO pour sauvegarder un Aliment
     * @param aliment
     */
    public void saveAliment(Aliment aliment) {
        daoAliment.save(aliment);
    }
}
