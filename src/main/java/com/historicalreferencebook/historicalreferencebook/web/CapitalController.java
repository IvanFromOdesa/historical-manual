package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.Capital;
import com.historicalreferencebook.historicalreferencebook.domain.Event;
import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.domain.StateCapital;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.CapitalRepository;
import com.historicalreferencebook.historicalreferencebook.repository.StateRepository;
import com.historicalreferencebook.historicalreferencebook.service.CapitalService;
import com.historicalreferencebook.historicalreferencebook.service.StateCapitalService;
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
public class CapitalController {

    @Autowired
    CapitalService capitalService;

    @Autowired
    CapitalRepository capitalRepository;

    @Autowired
    StateRepository stateRepository;
    @Autowired
    StateService stateService;
    @Autowired
    StateCapitalService stateCapitalService;

    @GetMapping("/capitals")
    public String showAllCapitals(Model model, @ModelAttribute ("myState1") State myState1, @ModelAttribute StateCapital stateCapital1){
        List<Capital> listCapitals = capitalService.findAllCapitals();
        List<StateCapital> stateCapitals = stateCapitalService.findAllStateCapitals();
        Boolean flag = false;
        model.addAttribute("flag", flag);
        model.addAttribute("listCapitals", listCapitals);
        model.addAttribute("stateCapitals", stateCapitals);
        return "capitals";
    }

    @GetMapping("/capitals/new")
    public String showNewCapitalForm(Model model){
        List<State>  listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("capital", new Capital());
        model.addAttribute("pageTitleC", "Add New Capital");
        return "capital_form";
    }

    @PostMapping("/capitals/save")
    public String saveCapital (@Valid Integer idState, Capital requestCapital, RedirectAttributes redirectAttributes) throws StateNotFoundException {
        capitalRepository.save(requestCapital);
        redirectAttributes.addFlashAttribute("messageC", "The capital has been saved successfully!");
        return "redirect:/capitals";
    }

    @GetMapping("/capitals/edit/{id}")
    public String showEditCapitalForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            Capital capital= capitalService.findCapitalById(id);
            List<State>  listStates = stateService.findAll();
            model.addAttribute("listStates", listStates);
            model.addAttribute("capital", capital);
            model.addAttribute("pageTitleC", "Edit Capital (ID: " + id + ")");
            return "capital_form";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageC", e.getMessage());
            return "redirect:/capitals";
        }
    }

    @GetMapping("/capitals/delete/{id}")
    public String deleteCapital(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            capitalService.deleteCapital(id);
            redirectAttributes.addFlashAttribute("messageC", "The capital ID " + id + " has been deleted!");
        } catch (StateNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageC", e.getMessage());
        }
        return "redirect:/capitals";
    }

    @GetMapping("/capitals/showByState")
    public String getCapital(Model model, @ModelAttribute("myState1") State myState1, BindingResult result){
        List <Capital> listCapitals = this.capitalRepository.findCapitalsByState(myState1.getOfficialStateName());
        model.addAttribute("listCapitals", listCapitals);
        return "capitals";
    }

    @GetMapping("/capitals/showByDate")
    public String getCapitalDate(Model model, @ModelAttribute("myState1") State myState1, @ModelAttribute StateCapital stateCapital1, BindingResult result){
        Integer resultNum = this.capitalRepository.findAllByDateOfFormationIsGreaterThan(stateCapital1.getDateOfFormation());
        List<Capital> listCapitals = capitalRepository.showAllCapitalsByDateOfFormationIsGreaterThan(stateCapital1.getDateOfFormation());
        model.addAttribute("listCapitals", listCapitals);
        model.addAttribute("resultNum", resultNum);
        return "capitals";
    }
}
