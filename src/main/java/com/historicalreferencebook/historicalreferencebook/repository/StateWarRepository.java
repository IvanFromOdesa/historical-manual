package com.historicalreferencebook.historicalreferencebook.repository;

import com.historicalreferencebook.historicalreferencebook.domain.StateWar;
import org.springframework.data.repository.CrudRepository;

public interface StateWarRepository extends CrudRepository<StateWar, Integer> {
    Long countByWarSKey(int id);
}
