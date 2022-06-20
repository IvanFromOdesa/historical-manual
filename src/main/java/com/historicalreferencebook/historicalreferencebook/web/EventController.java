package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.Event;
import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.domain.StateEvent;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.EventRepository;
import com.historicalreferencebook.historicalreferencebook.repository.StateRepository;
import com.historicalreferencebook.historicalreferencebook.service.EventService;
import com.historicalreferencebook.historicalreferencebook.service.StateEventService;
import com.historicalreferencebook.historicalreferencebook.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    StateRepository stateRepository;
    @Autowired
    StateService stateService;

    @Autowired
    StateEventService stateEventService;

    @GetMapping("/events")
    public String showAllEvents(Model model, @ModelAttribute ("myState") State myState, @ModelAttribute ("myEvent") Event myEvent){
        List<Event> listEvents = eventService.findAllEvents();
        List<StateEvent> stateEvents = stateEventService.findAllStateEvents();
        Boolean flag = false;
        model.addAttribute("flag", flag);
        model.addAttribute("listEvents", listEvents );
        model.addAttribute("stateEvents", stateEvents);
        return "events";
    }

    @GetMapping("/events/new")
    public String showNewEventForm(Model model){
        List<State>  listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("event", new Event());
        model.addAttribute("pageTitleE", "Add New Event");
        return "event_form";
    }

    @PostMapping("/events/save")
    public String saveEvent (Event requestEvent, RedirectAttributes redirectAttributes) throws StateNotFoundException {
        eventRepository.save(requestEvent);
        redirectAttributes.addFlashAttribute("messageE", "The event has been saved successfully!");
        return "redirect:/events";
    }

    @GetMapping("/events/edit/{id}")
    public String showEditEventForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            Event event = eventService.findEventById(id);
            List<State>  listStates = stateService.findAll();
            model.addAttribute("listStates", listStates);
            model.addAttribute("event", event);
            model.addAttribute("pageTitleE", "Edit Event (ID: " + id + ")");
            return "event_form";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageE", e.getMessage());
            return "redirect:/events";
        }
    }

    @GetMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            eventService.deleteEvent(id);
            redirectAttributes.addFlashAttribute("messageE", "The event ID " + id + " has been deleted!");
        } catch (StateNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageE", e.getMessage());
        }
        return "redirect:/events";
    }

    @GetMapping("/events/showByState")
    public String getEvent(Model model, @ModelAttribute ("myState") State myState, BindingResult result){
        List <Event> listEvents = this.eventRepository.findEventsByState(myState.getOfficialStateName());
        model.addAttribute("listEvents", listEvents);
        return "events";
    }

    @GetMapping("/events/showByDate")
    public String getEventByDate(Model model, @ModelAttribute ("myState") State myState, @ModelAttribute ("myEvent") Event myEvent, BindingResult result){
        List <Event> listEvents = this.eventRepository.findEventsByDateOfBeginningBetween(myEvent.getDateOfBeginning(), myEvent.getDateOfEnd());
        model.addAttribute("listEvents", listEvents);
        return "events";
    }
}
