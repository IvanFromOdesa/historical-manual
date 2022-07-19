package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.StateCapital;
import com.historicalreferencebook.historicalreferencebook.repository.StateCapitalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class StateCapitalService {

    private final StateCapitalRepository repository;

    public List<StateCapital> findAllStateCapitals(){
        return (List<StateCapital>) repository.findAll();
    }

    public StateCapital findStateCapitalById(Integer id) {
        Optional<StateCapital> result = repository.findById(id);
        try {
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any state-capital with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveStateCapital(StateCapital stateCapital) {
        repository.save(stateCapital);
    }

    public void deleteStateCapital(Integer id) throws EntityNotFoundException {
        Long count = repository.countByCapKey(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any state-capital with id " + id);
        }
        repository.deleteById(id);
    }

    public StateCapital findTheOldestCapital() {
        return repository.searchByOldestDateOfFormation();
    }
}
