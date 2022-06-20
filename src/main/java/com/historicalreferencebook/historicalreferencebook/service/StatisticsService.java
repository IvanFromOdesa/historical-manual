package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.Governor;
import com.historicalreferencebook.historicalreferencebook.domain.Statistics;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.GovernorRepository;
import com.historicalreferencebook.historicalreferencebook.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService {

    @Autowired
    StatisticsRepository statisticsRepository;

    public List<Statistics> findAllStats(){
        return (List<Statistics>) statisticsRepository.findAll();
    }

    public Statistics findStatById(Integer id) throws EntityNotFoundException {
        Optional<Statistics> result = statisticsRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new EntityNotFoundException("Could not find any stat with id " + id);
    }

    public void deleteStat(Integer id) throws StateNotFoundException{
        Long count = statisticsRepository.countByIdStats(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any stat with id " + id);
        }
        statisticsRepository.deleteById(id);
    }
}
