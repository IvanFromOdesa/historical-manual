package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.StateCapital;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.StateCapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class StateCapitalService {
    @Autowired
    StateCapitalRepository repository;

    public List<StateCapital> findAllStateCapitals(){
        return (List<StateCapital>) repository.findAll();
    }

    public StateCapital findStateCapitalById(Integer id) throws EntityNotFoundException {
        Optional<StateCapital> result = repository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new EntityNotFoundException("Could not find any state-capital with id " + id);
    }

    public void deleteStateCapital(Integer id) throws StateNotFoundException {
        Long count = repository.countByCapKey(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any state-capital with id " + id);
        }
        repository.deleteById(id);
    }
}
