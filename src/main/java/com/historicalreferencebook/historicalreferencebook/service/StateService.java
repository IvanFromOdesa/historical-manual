package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    public List<State> findAll(){
        return (List<State>) stateRepository.findAll();
    }

    public void save(State state) {
        stateRepository.save(state);
    }

    public State get(Integer id) throws StateNotFoundException {
        Optional<State> result= stateRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new StateNotFoundException("Could not find any state with id " + id);
    }

    public void delete(Integer id) throws StateNotFoundException {
        Long count = stateRepository.countByIdState(id);
        if(count == null||count == 0){
            throw new StateNotFoundException("Could not find any state with id " + id);
        }
        stateRepository.deleteById(id);
    }
}
