package br.sc.senai.repository;

import br.sc.senai.model.Store;
import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface StoreRepository extends CrudRepository<Store, Integer> {
    @Query(value = "SELECT u FROM Store u WHERE u.name = :name")
    Collection<Store> findAllByName(@Param("name") String name);
}

