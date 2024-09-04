package eni.demo.demo.module4.dao;

import eni.demo.demo.module4.bo.Aliment;
import org.springframework.data.repository.CrudRepository;

public interface AlimentRepository extends CrudRepository<Aliment, Long> {
}
