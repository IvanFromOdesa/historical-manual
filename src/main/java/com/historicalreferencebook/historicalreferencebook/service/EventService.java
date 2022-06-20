package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.Event;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public List<Event> findAllEvents(){
        return (List<Event>) eventRepository.findAll();
    }

    public Event findEventById(Integer id) throws EntityNotFoundException {
        Optional<Event> result= eventRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new EntityNotFoundException("Could not find any event with id " + id);
    }

    public void deleteEvent(Integer id) throws StateNotFoundException {
        Long count = eventRepository.countByIdEvent(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any event with id " + id);
        }
        eventRepository.deleteById(id);
    }
}
