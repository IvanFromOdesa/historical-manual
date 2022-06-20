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
public class StateCapitalController {
    @Autowired
    StateCapitalService service;
    @Autowired
    StateCapitalRepository repository;

    @Autowired
    StateRepository stateRepository;
    @Autowired
    StateService stateService;
    @Autowired
    CapitalService capitalService;

    @Autowired
    CapitalRepository capitalRepository;

    @GetMapping("/stateCapitals/new")
    public String showNewStateCapitalForm(Model model){
        List <State> listStates = stateService.findAll();
        List <Capital> listCapitals = capitalService.findAllCapitals();
        model.addAttribute("listStates", listStates);
        model.addAttribute("listCapitals", listCapitals);
        model.addAttribute("stateCapital", new StateCapital());
        model.addAttribute("pageTitleSC", "Add New State-Capital");
        return "stateCapital_form";
    }

    @PostMapping("/stateCapitals/save")
    public String saveStateCapital (StateCapital requestStateCapital, RedirectAttributes redirectAttributes){
        repository.save(requestStateCapital);
        redirectAttributes.addFlashAttribute("messageC", "The state-capital has been saved successfully!");
        return "redirect:/capitals";
    }

    @GetMapping("/stateCapitals/edit/{id}")
    public String showEditStateCapitalForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            StateCapital stateCapital = service.findStateCapitalById(id);
            List <State>  listStates = stateService.findAll();
            List <Capital>  listCapitals = capitalService.findAllCapitals();
            model.addAttribute("listStates", listStates);
            model.addAttribute("listCapitals", listCapitals);
            model.addAttribute("stateCapital", stateCapital);
            model.addAttribute("pageTitleSC", "Edit State-Capital (ID: " + id + ")");
            return "stateCapital_form";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageC", e.getMessage());
            return "redirect:/capitals";
        }
    }

    @GetMapping("/stateCapitals/delete/{id}")
    public String deleteStateCapital(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            service.deleteStateCapital(id);
            redirectAttributes.addFlashAttribute("messageC", "The state-capital ID " + id + " has been deleted!");
        } catch (StateNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageC", e.getMessage());
        }
        return "redirect:/capitals";
    }

    @GetMapping("/stateCapitals/showTheOldestCapital")
    public String showTheOldestCapital(Model model){
        StateCapital stateCapital1 = repository.searchByOldestDateOfFormation();
        model.addAttribute("stateCapital1", stateCapital1);
        return "oldestCapital";
    }
}
