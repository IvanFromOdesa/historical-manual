package com.historicalreferencebook.historicalreferencebook.repository;

import com.historicalreferencebook.historicalreferencebook.domain.War;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WarRepository extends CrudRepository<War, Integer> {
    Long countByIdWar(int id);

    @Transactional
    @Query(value = "SELECT * FROM war WHERE war.war_name LIKE ?1%", nativeQuery = true)
    List<War> findWarByWarNameContaining(String name);

    @Query
    @Transactional
    List <War> findWarsByDateOfBeginningBetween(java.sql.Date dateStart, java.sql.Date dateEnd);
}
