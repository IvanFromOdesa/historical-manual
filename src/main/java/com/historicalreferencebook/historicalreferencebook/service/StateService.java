package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.jpql.CurrencyCount;
import com.historicalreferencebook.historicalreferencebook.jpql.LanguageCount;
import com.historicalreferencebook.historicalreferencebook.repository.StateRepository;
import com.historicalreferencebook.historicalreferencebook.util.StatePDFExporter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class StateService implements GeneralContent{

    private final StateRepository stateRepository;

    public Set<State> findAll(){
        return (Set<State>) stateRepository.findAll();
    }

    public void saveState(State state) {
        stateRepository.save(state);
    }

    public State get(Integer id) {
        try {
            Optional<State> result= stateRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new StateNotFoundException("Could not find any state with id " + id);
        } catch (StateNotFoundException e) {
            throw new RuntimeException();
        }

    }

    public void delete(Integer id) throws StateNotFoundException {
        Long count = stateRepository.countByIdState(id);
        if(count == null||count == 0){
            throw new StateNotFoundException("Could not find any state with id " + id);
        }
        stateRepository.deleteById(id);
    }

    public Set<LanguageCount> findLanguages() {
        return findLs();
    }

    public Set<CurrencyCount> findCurrencies() {
        Set<CurrencyCount> currencyCounts = stateRepository.findAllCurrencies();
        return currencyCounts.stream().findAny().isEmpty() ?
                Collections.singleton(new CurrencyCount("None",0L)) : currencyCounts;
    }

    public void exportToPdfAllLanguages(HttpServletResponse response) throws IOException {
        setPdfParams(response);
        new StatePDFExporter(findLs()).export(response);
    }

    private Set<LanguageCount> findLs() {
        Set<LanguageCount> allLanguages = stateRepository.findAllLanguages();
        return allLanguages.stream().findAny().isEmpty() ?
                Collections.singleton(new LanguageCount("None",0L)) : allLanguages;
    }
}
