package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.StateWar;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.StateWarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class StateWarService {
    @Autowired
    StateWarRepository repository;

    public List<StateWar> findAllStateWars(){
        return (List<StateWar>) repository.findAll();
    }

    public StateWar findStateWarById(Integer id) throws EntityNotFoundException {
        Optional<StateWar> result = repository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new EntityNotFoundException("Could not find any state-war with id " + id);
    }

    public void deleteStateWar(Integer id) throws StateNotFoundException {
        Long count = repository.countByWarSKey(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any state-war with id " + id);
        }
        repository.deleteById(id);
    }
}
