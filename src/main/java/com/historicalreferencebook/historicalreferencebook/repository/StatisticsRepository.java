package com.historicalreferencebook.historicalreferencebook.repository;

import com.historicalreferencebook.historicalreferencebook.domain.Statistics;
import com.historicalreferencebook.historicalreferencebook.jpql.StatesMoreAvgPopulation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StatisticsRepository extends CrudRepository<Statistics, Integer> {
    Long countByIdStats(int id);

    @Transactional
    @Query(value = "SELECT official_state_name, CAST(AVG(statistics.general_population/statistics.general_territory) AS DECIMAL(5,2)) FROM state\n" +
            "\tINNER JOIN statistics ON state.id_state = statistics.id_state AND state.id_state IN\n" +
            "\t(SELECT id_state FROM statistics AS s\n" +
            "\tWHERE general_population/general_territory >=\n" +
            "\t\t(SELECT AVG(general_population/general_territory) FROM statistics\n" +
            "\t\tWHERE id_stats = s.id_stats))\n" +
            "\t\tGROUP BY state.official_state_name", nativeQuery = true)
    List<Object[]> findStatesWithPopulationDensityMoreAvG();
}
