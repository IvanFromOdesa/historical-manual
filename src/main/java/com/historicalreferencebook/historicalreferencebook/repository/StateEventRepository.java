package com.historicalreferencebook.historicalreferencebook.repository;

import com.historicalreferencebook.historicalreferencebook.domain.StateEvent;
import org.springframework.data.repository.CrudRepository;

public interface StateEventRepository extends CrudRepository<StateEvent, Integer> {

    Long countByStatKey(int id);
}
