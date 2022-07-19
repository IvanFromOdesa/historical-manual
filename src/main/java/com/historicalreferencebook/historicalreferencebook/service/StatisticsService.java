package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.Statistics;
import com.historicalreferencebook.historicalreferencebook.jpql.StatesMoreAvgPopulation;
import com.historicalreferencebook.historicalreferencebook.repository.StatisticsRepository;
import com.historicalreferencebook.historicalreferencebook.util.StateDensityPDFExporter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticsService implements GeneralContent{

    private final StatisticsRepository statisticsRepository;

    public List<Statistics> findAllStats(){
        return (List<Statistics>) statisticsRepository.findAll();
    }

    public Statistics findStatById(Integer id) {
        try {
            Optional<Statistics> result = statisticsRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any stat with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveStatistics(Statistics statistics) {
        statisticsRepository.save(statistics);
    }

    public void deleteStat(Integer id) throws EntityNotFoundException{
        Long count = statisticsRepository.countByIdStats(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any stat with id " + id);
        }
        statisticsRepository.deleteById(id);
    }

    public Set<StatesMoreAvgPopulation> findStatesWithHigherPopDensity() {
        return perform();
    }

    public void exportToPdfStatesDensity(HttpServletResponse response) throws IOException {
        setPdfParams(response);
        new StateDensityPDFExporter(perform()).export(response);
    }

    /**
     * Searches for any existing state with state density higher than average.
     *
     * @return - default values provided by Collections.singleton() (if the resulting Set is empty)
     * or the Set of StatesMoreAvgPopulation containing states.
     *
     */

    private Set<StatesMoreAvgPopulation> perform() {
        Set<StatesMoreAvgPopulation> set = new HashSet<>();
        return statisticsRepository.findStatesWithPopulationDensityMoreAvG().stream()
                .flatMap(row -> {
                    set.add(new StatesMoreAvgPopulation((String)row[0], (BigDecimal)row[1]));
                    return set.stream();
                }).collect(Collectors.toSet())
                .stream().findAny().isEmpty() ?
                Collections.singleton(new StatesMoreAvgPopulation("None",
                        BigDecimal.valueOf(0))) : set;
    }
}
