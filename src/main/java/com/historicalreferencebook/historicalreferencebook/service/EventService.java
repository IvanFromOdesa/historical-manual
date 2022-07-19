package com.historicalreferencebook.historicalreferencebook.service;

import com.historicalreferencebook.historicalreferencebook.domain.Event;
import com.historicalreferencebook.historicalreferencebook.repository.EventRepository;
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
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> findAllEvents(){
        return (List<Event>) eventRepository.findAll();
    }

    public Event findEventById(Integer id) {
        try {
            Optional<Event> result= eventRepository.findById(id);
            if(result.isPresent()){
                return result.get();
            } else throw new EntityNotFoundException("Could not find any event with id " + id);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException();
        }

    }

    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    public void deleteEvent(Integer id) throws EntityNotFoundException {
        Long count = eventRepository.countByIdEvent(id);
        if(count == null||count == 0){
            throw new EntityNotFoundException("Could not find any event with id " + id);
        }
        eventRepository.deleteById(id);
    }

    public Set<Event> eventsByState(String name) {
        Set<Event> events = eventRepository.findEventsByState(name);
        return findEvents(events);
    }

    public Set<Event> findEventsBetween(java.sql.Date dateStart, java.sql.Date dateEnd) {
        Set<Event> events = eventRepository.findEventsByDateOfBeginningBetween(dateStart, dateEnd);
        return findEvents(events);
    }

    private Set<Event> findEvents(Set<Event> events) {
        return events.stream().findAny().isEmpty() ?
                Collections.singleton(new Event(
                        0,
                        "None",
                        Date.valueOf("01-01-0001"),
                        Date.valueOf("01-01-0001"),
                        "None")) : events;
    }
}
