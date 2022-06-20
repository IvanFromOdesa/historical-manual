package com.historicalreferencebook.historicalreferencebook.repository;

import com.historicalreferencebook.historicalreferencebook.domain.StateCapital;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface StateCapitalRepository extends CrudRepository<StateCapital, Integer> {
    Long countByCapKey(int id);

    @Transactional
    @Query(value="SELECT * FROM state_capital\n" +
            "WHERE state_capital.date_of_formation <= ALL \n" +
            "\t(SELECT state_capital.date_of_formation FROM state_capital)", nativeQuery = true)
    StateCapital searchByOldestDateOfFormation();
}
