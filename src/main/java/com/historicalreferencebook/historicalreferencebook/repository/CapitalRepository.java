package com.historicalreferencebook.historicalreferencebook.repository;

import com.historicalreferencebook.historicalreferencebook.domain.Capital;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CapitalRepository extends CrudRepository<Capital, Integer> {
    Long countByIdCapital(int id);

    @Transactional
    @Query(value="SELECT * FROM capital\n" +
            "WHERE id_capital IN\n" +
            "\t(SELECT capital_id FROM state_capital\n" +
            "\tWHERE state_id IN\n" +
            "\t\t(SELECT id_state FROM state\n" +
            "\t\tWHERE state.official_state_name = :name))", nativeQuery = true)
    List<Capital> findCapitalsByState(String name);

    @Transactional
    @Query(value="SELECT COUNT (id_capital) as id_capital FROM capital\n" +
            "WHERE id_capital IN\n" +
            "\t(SELECT capital_id FROM state_capital\n" +
            "\tWHERE date_of_formation > ?1)", nativeQuery = true)
    Integer findAllByDateOfFormationIsGreaterThan(java.sql.Date date);

    @Transactional
    @Query(value="SELECT * FROM capital\n" +
            "WHERE id_capital IN\n" +
            "\t(SELECT capital_id FROM state_capital\n" +
            "\tWHERE date_of_formation > ?1)", nativeQuery = true)
    List <Capital> showAllCapitalsByDateOfFormationIsGreaterThan(java.sql.Date date);
}
