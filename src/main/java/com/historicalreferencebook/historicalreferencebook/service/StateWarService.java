package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.StateWar;
import com.historicalreferencebook.historicalreferencebook.repository.StateWarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class StateWarService {

    private final StateWarRepository repository;

    public Set<StateWar> findAllStateWars(){
        return (Set<StateWar>) repository.findAll();
    }

    public StateWar findStateWarById(Integer id) {
        try {
            Optional<StateWar> result = repository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any state-war with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveStateWar(StateWar stateWar) {
        repository.save(stateWar);
    }

    public void deleteStateWar(Integer id) throws EntityNotFoundException {
        Long count = repository.countByWarSKey(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any state-war with id " + id);
        }
        repository.deleteById(id);
    }
}
