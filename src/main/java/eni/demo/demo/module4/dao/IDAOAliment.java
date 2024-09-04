package eni.demo.demo.module4.dao;

import eni.demo.demo.module4.bo.Aliment;

import java.util.List;

/**
 * Ca sert uniquement pour avoir plus tard des spécifications
 * Exemple : Deux DAO qui font la même chose mais pas de la même manière
 * DAOAlimentMock : Récupérer des aliments instancié à la volée
 * DAOAlimentSQL : Récupérer des aliments select en base de données
 */
public interface IDAOAliment {

    List<Aliment> selectAliments();

    Aliment selectAlimentById(long id);

    void save(Aliment aliment);
}
