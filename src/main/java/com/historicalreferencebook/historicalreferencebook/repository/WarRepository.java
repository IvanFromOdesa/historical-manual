package com.historicalreferencebook.historicalreferencebook.repository;

import com.historicalreferencebook.historicalreferencebook.domain.War;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface WarRepository extends CrudRepository<War, Integer> {

    Long countByIdWar(int id);

    @Query(value = "SELECT * FROM war WHERE war.war_name LIKE ?1%", nativeQuery = true)
    Set<War> findWarByWarNameContaining(String name);

    @Query
    Set<War> findWarsByDateOfBeginningBetween(java.sql.Date dateStart, java.sql.Date dateEnd);
}
