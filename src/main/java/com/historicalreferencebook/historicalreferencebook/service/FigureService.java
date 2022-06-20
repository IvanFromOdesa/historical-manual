package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.Figure;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.FigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class FigureService {
    @Autowired
    FigureRepository figureRepository;

    public List<Figure> findAllFigures(){
        return (List<Figure>) figureRepository.findAll();
    }

    public Figure findFigureById(Integer id) throws EntityNotFoundException {
        Optional<Figure> result= figureRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new EntityNotFoundException("Could not find any event with id " + id);
    }

    public void deleteFigure(Integer id) throws StateNotFoundException {
        Long count = figureRepository.countByIdFigure(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any event with id " + id);
        }
        figureRepository.deleteById(id);
    }
}
