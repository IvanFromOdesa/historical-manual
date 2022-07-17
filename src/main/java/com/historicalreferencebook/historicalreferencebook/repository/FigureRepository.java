package com.historicalreferencebook.historicalreferencebook.repository;

import com.historicalreferencebook.historicalreferencebook.domain.Figure;
import com.historicalreferencebook.historicalreferencebook.jpql.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface FigureRepository extends CrudRepository<Figure, Integer> {

    Long countByIdFigure(int id);

    @Query(value = "SELECT * FROM figure WHERE figure.full_name LIKE ?1%", nativeQuery = true)
    Set<Figure> findByFullNameContaining(String name);

    @Query(value="SELECT pib, position AS activity, 'governor' as role FROM governor\n" +
            "UNION ALL\n" +
            "SELECT full_name, kind_of_activity, 'figure' FROM figure\n" +
            "ORDER BY activity", nativeQuery = true)
    Set<Object[]> findFiguresAndGovernors();
}
