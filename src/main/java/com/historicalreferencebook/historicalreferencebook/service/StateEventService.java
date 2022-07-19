package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.StateEvent;
import com.historicalreferencebook.historicalreferencebook.repository.StateEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class StateEventService {

    private final StateEventRepository repository;

    public List<StateEvent> findAllStateEvents(){
        return (List<StateEvent>) repository.findAll();
    }

    public StateEvent findStateEventById(Integer id) {
        try {
            Optional<StateEvent> result = repository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any state-event with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveStateEvent(StateEvent stateEvent) {
        repository.save(stateEvent);
    }

    public void deleteStateEvent(Integer id) throws EntityNotFoundException {
        Long count = repository.countByStatKey(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any state-event with id " + id);
        }
        repository.deleteById(id);
    }
}
