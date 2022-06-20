package com.historicalreferencebook.historicalreferencebook.repository;

import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.jpql.CurrencyCount;
import com.historicalreferencebook.historicalreferencebook.jpql.LanguageCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StateRepository extends CrudRepository<State, Integer> {
    Long countByIdState(int id);

    @Transactional
    @Query("SELECT new com.historicalreferencebook.historicalreferencebook.jpql.LanguageCount(c.officialLanguage, COUNT(c.officialStateName))" +
    "FROM State AS c GROUP BY c.officialLanguage ORDER BY c.officialLanguage DESC ")
    List <LanguageCount> findAllLanguages();

    @Transactional
    @Query("SELECT new com.historicalreferencebook.historicalreferencebook.jpql.CurrencyCount(c.stateCurrency, COUNT(c.officialStateName))" +
    "FROM State AS c GROUP BY c.stateCurrency ORDER BY c.stateCurrency DESC ")
    List <CurrencyCount> findAllCurrencies();
}
