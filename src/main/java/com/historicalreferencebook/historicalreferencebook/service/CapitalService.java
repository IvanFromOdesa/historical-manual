package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.Capital;
import com.historicalreferencebook.historicalreferencebook.repository.CapitalRepository;
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
public class CapitalService {

    private final CapitalRepository capitalRepository;

    public List<Capital> findAllCapitals(){
        return (List<Capital>) capitalRepository.findAll();
    }

    public Capital findCapitalById(Integer id) {
        try {
            Optional<Capital> result= capitalRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any capital with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveCapital(Capital capital) {
        capitalRepository.save(capital);
    }

    public void deleteCapital(Integer id) throws EntityNotFoundException {
        Long count = capitalRepository.countByIdCapital(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any capital with id " + id);
        }
        capitalRepository.deleteById(id);
    }

    public Set<Capital> capitalsByState(String name) {
        Set<Capital> capitals = capitalRepository.findCapitalsByState(name);
        return findCapitals(capitals);
    }

    public Integer findCapitalsAfter(Date date) {
        return capitalRepository.findAllByDateOfFormationIsGreaterThan(date)
                .describeConstable().orElse(0);
    }

    public Set<Capital> showCapitalsAfter(Date date) {
        Set<Capital> capitals = capitalRepository.showAllCapitalsByDateOfFormationIsGreaterThan(date);
        return findCapitals(capitals);
    }

    private Set<Capital> findCapitals(Set<Capital> capitals) {
        return capitals.stream().findAny().isEmpty() ?
                Collections.singleton(new Capital
                        (0, "None", 0, 0)) : capitals;
    }
}