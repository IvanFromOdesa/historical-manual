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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    private final StateService stateService;

    private final StateEventService stateEventService;

    @GetMapping("/events")
    public String showAllEvents(Model model, @ModelAttribute ("myState") State myState,
                                @ModelAttribute ("myEvent") Event myEvent){
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
    public String saveEvent (Event requestEvent,
                             RedirectAttributes redirectAttributes) {
        eventService.saveEvent(requestEvent);
        redirectAttributes.addFlashAttribute("messageE", "The event has been saved successfully!");
        return "redirect:/events";
    }

    @GetMapping("/events/edit/{id}")
    public String showEditEventForm(@PathVariable("id") Integer id, Model model) {
        Event event = eventService.findEventById(id);
        List<State> listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("event", event);
        model.addAttribute("pageTitleE", "Edit Event (ID: " + id + ")");
        return "event_form";
    }

    @GetMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable("id") Integer id, Model model,
                              RedirectAttributes redirectAttributes) {
        eventService.deleteEvent(id);
        redirectAttributes.addFlashAttribute("messageE", "The event ID " + id + " has been deleted!");
        return "redirect:/events";
    }

    @GetMapping("/events/showByState")
    public String getEvent(Model model, @ModelAttribute ("myState") State myState, BindingResult result){
        Set<Event> listEvents = eventService.eventsByState(myState.getOfficialStateName());
        model.addAttribute("listEvents", listEvents);
        return "events";
    }

    @GetMapping("/events/showByDate")
    public String getEventByDate(Model model, @ModelAttribute ("myState") State myState,
                                 @ModelAttribute ("myEvent") Event myEvent) {
        Set<Event> listEvents = eventService.findEventsBetween(myEvent.getDateOfBeginning(),
                myEvent.getDateOfEnd());
        model.addAttribute("listEvents", listEvents);
        return "events";
    }
}
