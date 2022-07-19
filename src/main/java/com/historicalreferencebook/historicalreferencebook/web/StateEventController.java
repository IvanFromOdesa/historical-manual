package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.Event;
import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.domain.StateEvent;
import com.historicalreferencebook.historicalreferencebook.service.EventService;
import com.historicalreferencebook.historicalreferencebook.service.StateEventService;
import com.historicalreferencebook.historicalreferencebook.service.StateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class StateEventController {

    private final StateEventService service;

    private final StateService stateService;

    private final EventService eventService;

    @GetMapping("/stateEvents/new")
    public String showNewStateEventForm(Model model){
        List<State> listStates = stateService.findAll();
        List<Event> listEvents = eventService.findAllEvents();
        model.addAttribute("listStates", listStates);
        model.addAttribute("listEvents", listEvents);
        model.addAttribute("stateEvent", new StateEvent());
        model.addAttribute("pageTitleSE", "Add New State-Event");
        return "stateEvent_form";
    }

    @PostMapping("/stateEvents/save")
    public String saveStateEvent (StateEvent requestStateEvent, RedirectAttributes redirectAttributes){
        service.saveStateEvent(requestStateEvent);
        redirectAttributes.addFlashAttribute("messageE", "The state-event has been saved successfully!");
        return "redirect:/events";
    }

    @GetMapping("/stateEvents/edit/{id}")
    public String showEditStateEventForm(@PathVariable("id") Integer id, Model model,
                                         RedirectAttributes redirectAttributes){
        StateEvent stateEvent = service.findStateEventById(id);
        List<State> listStates = stateService.findAll();
        List<Event> listEvents = eventService.findAllEvents();
        model.addAttribute("listStates", listStates);
        model.addAttribute("listEvents", listEvents);
        model.addAttribute("stateEvent", stateEvent);
        model.addAttribute("pageTitleSE", "Edit State-Event (ID: " + id + ")");
        return "stateEvent_form";
    }

    @GetMapping("/stateEvents/delete/{id}")
    public String deleteStateEvent(@PathVariable("id") Integer id, Model model,
                                   RedirectAttributes redirectAttributes){
        service.deleteStateEvent(id);
        redirectAttributes.addFlashAttribute("messageE", "The state-event ID " + id + " has been deleted!");
        return "redirect:/events";
    }
}
