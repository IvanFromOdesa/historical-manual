package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.War;
import com.historicalreferencebook.historicalreferencebook.repository.WarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class WarService {

    private final WarRepository warRepository;

    public List<War> findAllWars(){
        return (List<War>)warRepository.findAll();
    }

    public War findWarById(Integer id) {
        try {
            Optional<War> result= warRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any war with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveWar(War war) {
        warRepository.save(war);
    }

    public void deleteWar(Integer id) throws EntityNotFoundException {
        Long count = warRepository.countByIdWar(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any war with id " + id);
        }
        warRepository.deleteById(id);
    }

    public Set<War> findWarsByName(String name) {
        Set<War> wars = warRepository.findWarByWarNameContaining(name);
        return findWars(wars);
    }

    public Set<War> findWarsByDate(Date dateStart, Date dateEnd) {
        Set<War> wars = warRepository.findWarsByDateOfBeginningBetween(dateStart, dateEnd);
        return findWars(wars);
    }

    private Set<War> findWars(Set<War> wars) {
        return wars.stream().findAny().isEmpty() ?
                Collections.singleton(new War(
                        0,
                        "None",
                        "No States",
                        Date.valueOf("01-01-0001"),
                        Date.valueOf("01-01-0001"),
                        "None",
                        "None")) : wars;
    }
}
