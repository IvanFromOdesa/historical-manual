package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.Event;
import com.historicalreferencebook.historicalreferencebook.domain.War;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.EventRepository;
import com.historicalreferencebook.historicalreferencebook.repository.WarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class WarService {
    @Autowired
    WarRepository warRepository;

    public List<War> findAllWars(){
        return (List<War>) warRepository.findAll();
    }

    public War findWarById(Integer id) throws EntityNotFoundException {
        Optional<War> result= warRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new EntityNotFoundException("Could not find any war with id " + id);
    }

    public void deleteWar(Integer id) throws StateNotFoundException {
        Long count = warRepository.countByIdWar(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any war with id " + id);
        }
        warRepository.deleteById(id);
    }
}
