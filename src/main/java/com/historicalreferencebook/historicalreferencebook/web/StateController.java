package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.jpql.CurrencyCount;
import com.historicalreferencebook.historicalreferencebook.jpql.LanguageCount;
import com.historicalreferencebook.historicalreferencebook.service.StateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class StateController {

    private final StateService service;

    @GetMapping("/states")
    public String showAllStates(Model model) {
        List<State> listStates = service.findAll();
        model.addAttribute("listStates", listStates);
        return "states";
    }

    @GetMapping("/states/new")
    public String showNewForm(Model model) {
        model.addAttribute("state", new State());
        model.addAttribute("pageTitle", "Add New State");
        return "state_form";
    }

    @PostMapping("/states/save")
    public String saveState (State state, RedirectAttributes redirectAttributes) {
        service.saveState(state);
        redirectAttributes.addFlashAttribute("message", "The state has been saved successfully!");
        return "redirect:/states";
    }

    @GetMapping("/states/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        State state = service.get(id);
        model.addAttribute("state", state);
        model.addAttribute("pageTitle", "Edit State (ID: " + id + ")");
        return "state_form";
    }

    @GetMapping("/states/delete/{id}")
    public String deleteState(@PathVariable("id") Integer id, Model model,
                              RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("message", "The state ID " + id + " has been deleted!");
        return "redirect:/states";
    }

    @GetMapping("/states/showLanguages")
    public String displayLanguages(Model model) {
        Set<LanguageCount> listStates1 = service.findLanguages();
        model.addAttribute("listStates1", listStates1);
        return "languageCount";
    }

    @GetMapping("/states/showCurrencies")
    public String displayCurrencies(Model model) {
        Set<CurrencyCount> listStates2 = service.findCurrencies();
        model.addAttribute("listStates2", listStates2);
        return "currencyCount";
    }

    @GetMapping("/states/export")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        service.exportToPdfAllLanguages(response);
    }
}
