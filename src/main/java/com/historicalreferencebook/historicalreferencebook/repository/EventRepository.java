package com.historicalreferencebook.historicalreferencebook.repository;

import com.historicalreferencebook.historicalreferencebook.domain.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {
    Long countByIdEvent(int id);

    @Transactional
    @Query(value="SELECT * FROM event\n" +
            "WHERE id_event IN\n" +
            "\t(SELECT event_id FROM state_event\n" +
            "\tWHERE state_id In\n" +
            "\t\t(SELECT id_state FROM state\n" +
            "\t\tWHERE state.official_state_name = :name))", nativeQuery = true)
    List <Event> findEventsByState(String name);

    @Transactional
    @Query
    List <Event> findEventsByDateOfBeginningBetween(java.sql.Date dateStart, java.sql.Date dateEnd);
}
