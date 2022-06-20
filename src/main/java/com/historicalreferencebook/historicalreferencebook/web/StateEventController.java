package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.*;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.*;
import com.historicalreferencebook.historicalreferencebook.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class StateEventController {
    @Autowired
    StateEventService service;
    @Autowired
    StateEventRepository repository;

    @Autowired
    StateRepository stateRepository;
    @Autowired
    StateService stateService;
    @Autowired
    EventService eventService;

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/stateEvents/new")
    public String showNewStateEventForm(Model model){
        List<State> listStates = stateService.findAll();
        List <Event> listEvents = eventService.findAllEvents();
        model.addAttribute("listStates", listStates);
        model.addAttribute("listEvents", listEvents);
        model.addAttribute("stateEvent", new StateEvent());
        model.addAttribute("pageTitleSE", "Add New State-Event");
        return "stateEvent_form";
    }

    @PostMapping("/stateEvents/save")
    public String saveStateEvent (StateEvent requestStateEvent, RedirectAttributes redirectAttributes){
        repository.save(requestStateEvent);
        redirectAttributes.addFlashAttribute("messageE", "The state-event has been saved successfully!");
        return "redirect:/events";
    }

    @GetMapping("/stateEvents/edit/{id}")
    public String showEditStateEventForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            StateEvent stateEvent = service.findStateEventById(id);
            List <State>  listStates = stateService.findAll();
            List <Event>  listEvents = eventService.findAllEvents();
            model.addAttribute("listStates", listStates);
            model.addAttribute("listEvents", listEvents);
            model.addAttribute("stateEvent", stateEvent);
            model.addAttribute("pageTitleSE", "Edit State-Event (ID: " + id + ")");
            return "stateEvent_form";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageE", e.getMessage());
            return "redirect:/events";
        }
    }

    @GetMapping("/stateEvents/delete/{id}")
    public String deleteStateEvent(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            service.deleteStateEvent(id);
            redirectAttributes.addFlashAttribute("messageE", "The state-event ID " + id + " has been deleted!");
        } catch (StateNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageE", e.getMessage());
        }
        return "redirect:/events";
    }
}
