package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.jpql.CurrencyCount;
import com.historicalreferencebook.historicalreferencebook.jpql.LanguageCount;
import com.historicalreferencebook.historicalreferencebook.repository.StateRepository;
import com.historicalreferencebook.historicalreferencebook.service.StateService;
import com.historicalreferencebook.historicalreferencebook.util.StatePDFExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StateController {

    @Autowired
    private StateService service;

    @Autowired
    private StateRepository repository;

    @GetMapping("/states")
    public String showAllStates(Model model){
        List<State>  listStates = service.findAll();
        model.addAttribute("listStates", listStates);
        return "states";
    }

    @GetMapping("/states/new")
    public String showNewForm(Model model){
        model.addAttribute("state", new State());
        model.addAttribute("pageTitle", "Add New State");
        return "state_form";
    }

    @PostMapping("/states/save")
    public String saveState (State state, RedirectAttributes redirectAttributes){
        service.save(state);
        redirectAttributes.addFlashAttribute("message", "The state has been saved successfully!");
        return "redirect:/states";
    }

    @GetMapping("/states/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            State state = service.get(id);
            model.addAttribute("state", state);
            model.addAttribute("pageTitle", "Edit State (ID: " + id + ")");
            return "state_form";
        } catch (StateNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/states";
        }
    }

    @GetMapping("/states/delete/{id}")
    public String deleteState(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message", "The state ID " + id + " has been deleted!");
        } catch (StateNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/states";
    }

    @GetMapping("/states/showLanguages")
    public String displayLanguages(Model model){
        List <LanguageCount> listStates1 = repository.findAllLanguages();
        model.addAttribute("listStates1", listStates1);
        return "languageCount";
    }

    @GetMapping("/states/showCurrencies")
    public String displayCurrencies(Model model){
        List <CurrencyCount> listStates2 = repository.findAllCurrencies();
        model.addAttribute("listStates2", listStates2);
        return "currencyCount";
    }

    @GetMapping("/states/export")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateFormat = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=states_" + currentDateFormat + ".pdf";
        response.setHeader(headerKey, headerValue);

        List <LanguageCount> listStates = repository.findAllLanguages();

        StatePDFExporter exporter = new StatePDFExporter(listStates);
        exporter.export(response);
    }
}
