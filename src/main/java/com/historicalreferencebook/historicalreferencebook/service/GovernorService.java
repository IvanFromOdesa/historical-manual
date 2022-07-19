package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.Governor;
import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.repository.GovernorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GovernorService {

    private final GovernorRepository governorRepository;

    public List<Governor> findAllGovernors(){
        return (List<Governor>) governorRepository.findAll();
    }

    public Governor findGovernorById(Integer id) {
        Optional<Governor> result= governorRepository.findById(id);
        try {
            if(result.isPresent()) {
                return result.get();
            } else throw new EntityNotFoundException("Could not find any governor with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }

    }

    public void saveGovernor(Governor governor) {
        governorRepository.save(governor);
    }

    public void deleteGovernor(Integer id) throws EntityNotFoundException {
        Long count = governorRepository.countByIdGovernor(id);
        if(count == null||count == 0) {
            throw new EntityNotFoundException("Could not find any governor with id " + id);
        }
        governorRepository.deleteById(id);
    }

    public Integer findNumOfGovernorsElectedAfter(Date date) {
        return governorRepository.findGovernorsByDateOfIntercessionAfter(date)
                .describeConstable().orElse(0);
    }

    public Set<Governor> showGovernorsElectedAfter(Date date) {
        Set<Governor> governors = governorRepository.findAllByDateOfIntercessionAfter(date);
        return governors.stream().findAny().isEmpty() ?
                Collections.singleton(new Governor(
                        0,
                        "None",
                        0,
                        "None",
                        Date.valueOf("01-01-0001"),
                        Date.valueOf("01-01-0001"),
                        0.0,
                        0,
                        new State("No state"))) : governors;
    }

    /**
     * Searches for any existing states without governors.
     *
     * @return - default values provided by Collections.singleton() (if the resulting Set is empty)
     * or the Set of State containing states with no governors.
     *
     */

    public Set<State> showStatesWithoutGovernors() {
        Set<State> listState = new HashSet<>();
        return governorRepository.findStatesWithNoGovernors().stream().flatMap(row -> {
            listState.add(new State((Integer)row[0], (String)row[1]));
            return listState.stream();
        }).collect(Collectors.toSet())
                .stream().findAny().isEmpty() ?
                Collections.singleton(new State(
                        0, "None")) : listState;
    }
}
