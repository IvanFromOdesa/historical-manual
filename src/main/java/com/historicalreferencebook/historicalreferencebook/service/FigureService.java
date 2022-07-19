package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.Figure;
import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.jpql.Person;
import com.historicalreferencebook.historicalreferencebook.repository.FigureRepository;
import com.historicalreferencebook.historicalreferencebook.util.PersonPDFExporter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FigureService implements GeneralContent {

    private final FigureRepository figureRepository;

    public List<Figure> findAllFigures(){
        return (List<Figure>) figureRepository.findAll();
    }

    public Figure findFigureById(Integer id) {
        try {
            Optional<Figure> result = figureRepository.findById(id);
            if(result.isPresent()) {
                return result.get();
            } else throw new EntityNotFoundException("Could not find any event with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public void saveFigure(Figure figure) {
        figureRepository.save(figure);
    }

    public void deleteFigure(Integer id) throws EntityNotFoundException {
        Long count = figureRepository.countByIdFigure(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any event with id " + id);
        }
        figureRepository.deleteById(id);
    }

    public Set<Figure> findByName(String name) {
        Set<Figure> figures = figureRepository.findByFullNameContaining(name);
        return figures.stream().findAny().isEmpty() ?
                Collections.singleton(new Figure(
                        0,
                        "None",
                        Date.valueOf("01-01-0001"),
                        Date.valueOf("01-01-0001"),
                        "None",
                        new State("No state"))) : figures;
    }

    public Set<Person> findAllPeople() {
        return perform();
    }

    public void exportToPdfAllPeople(HttpServletResponse response) throws IOException {
        setPdfParams(response);
        new PersonPDFExporter(perform()).export(response);
    }

    /**
     * Searches for any existing governors and figures and returns a Set of Person containing them.
     *
     * @return - default values provided by Collections.singleton() (if the resulting Set is empty)
     * or the Set of Person containing all Figures and Governors.
     *
     */

    private Set <Person> perform() {
        Set<Person> list = new HashSet<>();
        return figureRepository.findFiguresAndGovernors().stream().flatMap(row -> {
            list.add(new Person((String)row[0], (String)row[1], (String)row[2]));
            return list.stream();
                }).collect(Collectors.toSet())
                .stream().findAny().isEmpty() ?
                Collections.singleton(new Person(
                        "No name", "None", "None")) : list;
    }
}
