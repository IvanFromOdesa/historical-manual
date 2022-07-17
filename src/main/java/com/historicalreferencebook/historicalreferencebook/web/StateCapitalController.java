package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.Capital;
import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.domain.StateCapital;
import com.historicalreferencebook.historicalreferencebook.service.CapitalService;
import com.historicalreferencebook.historicalreferencebook.service.StateCapitalService;
import com.historicalreferencebook.historicalreferencebook.service.StateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
@AllArgsConstructor
public class StateCapitalController {

    private final StateCapitalService service;

    private final StateService stateService;

    private final CapitalService capitalService;

    @GetMapping("/stateCapitals/new")
    public String showNewStateCapitalForm(Model model) {
        Set<State> listStates = stateService.findAll();
        Set<Capital> listCapitals = capitalService.findAllCapitals();
        model.addAttribute("listStates", listStates);
        model.addAttribute("listCapitals", listCapitals);
        model.addAttribute("stateCapital", new StateCapital());
        model.addAttribute("pageTitleSC", "Add New State-Capital");
        return "stateCapital_form";
    }

    @PostMapping("/stateCapitals/save")
    public String saveStateCapital (StateCapital requestStateCapital,
                                    RedirectAttributes redirectAttributes) {
        service.saveStateCapital(requestStateCapital);
        redirectAttributes.addFlashAttribute("messageC", "The state-capital has been saved successfully!");
        return "redirect:/capitals";
    }

    @GetMapping("/stateCapitals/edit/{id}")
    public String showEditStateCapitalForm(@PathVariable("id") Integer id, Model model) {
        StateCapital stateCapital = service.findStateCapitalById(id);
        Set<State> listStates = stateService.findAll();
        Set<Capital> listCapitals = capitalService.findAllCapitals();
        model.addAttribute("listStates", listStates);
        model.addAttribute("listCapitals", listCapitals);
        model.addAttribute("stateCapital", stateCapital);
        model.addAttribute("pageTitleSC", "Edit State-Capital (ID: " + id + ")");
        return "stateCapital_form";
    }

    @GetMapping("/stateCapitals/delete/{id}")
    public String deleteStateCapital(@PathVariable("id") Integer id, Model model,
                                     RedirectAttributes redirectAttributes) {
        service.deleteStateCapital(id);
        redirectAttributes.addFlashAttribute("messageC", "The state-capital ID " + id + " has been deleted!");
        return "redirect:/capitals";
    }

    @GetMapping("/stateCapitals/showTheOldestCapital")
    public String showTheOldestCapital(Model model) {
        StateCapital stateCapital1 = service.findTheOldestCapital();
        model.addAttribute("stateCapital1", stateCapital1);
        return "oldestCapital";
    }
}
