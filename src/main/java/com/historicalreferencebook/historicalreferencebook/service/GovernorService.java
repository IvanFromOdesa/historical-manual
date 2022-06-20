package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.Governor;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.GovernorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class GovernorService {

    @Autowired
    GovernorRepository governorRepository;

    public List<Governor> findAllGovernors(){
        return (List<Governor>) governorRepository.findAll();
    }

    public Governor findGovernorById(Integer id) throws EntityNotFoundException {
        Optional<Governor> result= governorRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new EntityNotFoundException("Could not find any governor with id " + id);
    }

    public void deleteGovernor(Integer id) throws StateNotFoundException {
        Long count = governorRepository.countByIdGovernor(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any governor with id " + id);
        }
        governorRepository.deleteById(id);
    }
}
