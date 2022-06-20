package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.Capital;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CapitalService {
    @Autowired
    CapitalRepository capitalRepository;

    public List<Capital> findAllCapitals(){
        return (List<Capital>) capitalRepository.findAll();
    }

    public Capital findCapitalById(Integer id) throws EntityNotFoundException {
        Optional<Capital> result= capitalRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new EntityNotFoundException("Could not find any capital with id " + id);
    }

    public void deleteCapital(Integer id) throws StateNotFoundException {
        Long count = capitalRepository.countByIdCapital(id);
        if(count == null||count == 0){
            throw new StateNotFoundException("Could not find any capital with id " + id);
        }
        capitalRepository.deleteById(id);
    }
}