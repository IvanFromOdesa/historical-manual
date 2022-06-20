package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.domain.StateWar;
import com.historicalreferencebook.historicalreferencebook.domain.War;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.StateRepository;
import com.historicalreferencebook.historicalreferencebook.repository.StateWarRepository;
import com.historicalreferencebook.historicalreferencebook.repository.WarRepository;
import com.historicalreferencebook.historicalreferencebook.service.StateService;
import com.historicalreferencebook.historicalreferencebook.service.StateWarService;
import com.historicalreferencebook.historicalreferencebook.service.WarService;
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
public class StateWarController {
    @Autowired
    StateWarService service;
    @Autowired
    StateWarRepository repository;

    @Autowired
    StateRepository stateRepository;
    @Autowired
    StateService stateService;
    @Autowired
    WarService warService;

    @Autowired
    WarRepository warRepository;

    @GetMapping("/stateWars/new")
    public String showNewStateWarForm(Model model){
        List <State>  listStates = stateService.findAll();
        List <War> listWars = warService.findAllWars();
        model.addAttribute("listStates", listStates);
        model.addAttribute("listWars", listWars);
        model.addAttribute("stateWar", new StateWar());
        model.addAttribute("pageTitleSW", "Add New State-War");
        return "stateWar_form";
    }

    @PostMapping("/stateWars/save")
    public String saveStateWar (StateWar requestStateWar, RedirectAttributes redirectAttributes){
        repository.save(requestStateWar);
        redirectAttributes.addFlashAttribute("messageW", "The state-war has been saved successfully!");
        return "redirect:/wars";
    }

    @GetMapping("/stateWars/edit/{id}")
    public String showEditStateWarForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            StateWar stateWar = service.findStateWarById(id);
            List <State>  listStates = stateService.findAll();
            List <War>  listWars = warService.findAllWars();
            model.addAttribute("listStates", listStates);
            model.addAttribute("listWars", listWars);
            model.addAttribute("stateWar", stateWar);
            model.addAttribute("pageTitleSW", "Edit State-War (ID: " + id + ")");
            return "stateWar_form";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageW", e.getMessage());
            return "redirect:/wars";
        }
    }

    @GetMapping("/stateWars/delete/{id}")
    public String deleteStateWar(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            service.deleteStateWar(id);
            redirectAttributes.addFlashAttribute("messageW", "The state-war ID " + id + " has been deleted!");
        } catch (StateNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageW", e.getMessage());
        }
        return "redirect:/wars";
    }
}
