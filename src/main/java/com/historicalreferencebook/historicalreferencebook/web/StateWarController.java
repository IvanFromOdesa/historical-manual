package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.domain.StateWar;
import com.historicalreferencebook.historicalreferencebook.domain.War;
import com.historicalreferencebook.historicalreferencebook.service.StateService;
import com.historicalreferencebook.historicalreferencebook.service.StateWarService;
import com.historicalreferencebook.historicalreferencebook.service.WarService;
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
public class StateWarController {

    private final StateWarService service;

    private final StateService stateService;

    private final WarService warService;

    @GetMapping("/stateWars/new")
    public String showNewStateWarForm(Model model){
        List<State> listStates = stateService.findAll();
        List<War> listWars = warService.findAllWars();
        model.addAttribute("listStates", listStates);
        model.addAttribute("listWars", listWars);
        model.addAttribute("stateWar", new StateWar());
        model.addAttribute("pageTitleSW", "Add New State-War");
        return "stateWar_form";
    }

    @PostMapping("/stateWars/save")
    public String saveStateWar (StateWar requestStateWar, RedirectAttributes redirectAttributes){
        service.saveStateWar(requestStateWar);
        redirectAttributes.addFlashAttribute("messageW", "The state-war has been saved successfully!");
        return "redirect:/wars";
    }

    @GetMapping("/stateWars/edit/{id}")
    public String showEditStateWarForm(@PathVariable("id") Integer id, Model model) {
        StateWar stateWar = service.findStateWarById(id);
        List<State> listStates = stateService.findAll();
        List<War> listWars = warService.findAllWars();
        model.addAttribute("listStates", listStates);
        model.addAttribute("listWars", listWars);
        model.addAttribute("stateWar", stateWar);
        model.addAttribute("pageTitleSW", "Edit State-War (ID: " + id + ")");
        return "stateWar_form";
    }

    @GetMapping("/stateWars/delete/{id}")
    public String deleteStateWar(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAttributes) {
        service.deleteStateWar(id);
        redirectAttributes.addFlashAttribute("messageW", "The state-war ID " + id + " has been deleted!");
        return "redirect:/wars";
    }
}
