package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.StateEvent;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.StateEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class StateEventService {

    @Autowired
    StateEventRepository repository;

    public List<StateEvent> findAllStateEvents(){
        return (List<StateEvent>) repository.findAll();
    }

    public StateEvent findStateEventById(Integer id) throws EntityNotFoundException {
        Optional<StateEvent> result = repository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new EntityNotFoundException("Could not find any state-event with id " + id);
    }

    public void deleteStateEvent(Integer id) throws StateNotFoundException {
        Long count = repository.countByStatKey(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any state-event with id " + id);
        }
        repository.deleteById(id);
    }
}
