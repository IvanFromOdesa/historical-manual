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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
@AllArgsConstructor
public class CapitalController {

    private final CapitalService capitalService;

    private final StateService stateService;

    private final StateCapitalService stateCapitalService;

    @GetMapping("/capitals")
    public String showAllCapitals(Model model, @ModelAttribute ("myState1") State myState1,
                                  @ModelAttribute StateCapital stateCapital1){
        Set<Capital> listCapitals = capitalService.findAllCapitals();
        Set<StateCapital> stateCapitals = stateCapitalService.findAllStateCapitals();
        Boolean flag = false;
        model.addAttribute("flag", flag);
        model.addAttribute("listCapitals", listCapitals);
        model.addAttribute("stateCapitals", stateCapitals);
        return "capitals";
    }

    @GetMapping("/capitals/new")
    public String showNewCapitalForm(Model model){
        Set<State> listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("capital", new Capital());
        model.addAttribute("pageTitleC", "Add New Capital");
        return "capital_form";
    }

    @PostMapping("/capitals/save")
    public String saveCapital (Capital requestCapital, RedirectAttributes redirectAttributes) {
        capitalService.saveCapital(requestCapital);
        redirectAttributes.addFlashAttribute("messageC", "The capital has been saved successfully!");
        return "redirect:/capitals";
    }

    @GetMapping("/capitals/edit/{id}")
    public String showEditCapitalForm(@PathVariable("id") Integer id,
                                      Model model){
        Capital capital= capitalService.findCapitalById(id);
        Set<State> listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("capital", capital);
        model.addAttribute("pageTitleC", "Edit Capital (ID: " + id + ")");
        return "capital_form";
    }

    @GetMapping("/capitals/delete/{id}")
    public String deleteCapital(@PathVariable("id") Integer id, Model model,
                                RedirectAttributes redirectAttributes){
        capitalService.deleteCapital(id);
        redirectAttributes.addFlashAttribute("messageC", "The capital ID " + id + " has been deleted!");
        return "redirect:/capitals";
    }

    @GetMapping("/capitals/showByState")
    public String getCapital(Model model, @ModelAttribute("myState1") State myState1) {
        Set<Capital> listCapitals = capitalService.capitalsByState(myState1.getOfficialStateName());
        model.addAttribute("listCapitals", listCapitals);
        return "capitals";
    }

    @GetMapping("/capitals/showByDate")
    public String getCapitalDate(Model model, @ModelAttribute("myState1") State myState1,
                                 @ModelAttribute StateCapital stateCapital1) {
        Integer resultNum = capitalService.findCapitalsAfter(stateCapital1.getDateOfFormation());
        Set<Capital> listCapitals = capitalService.showCapitalsAfter(stateCapital1.getDateOfFormation());
        model.addAttribute("listCapitals", listCapitals);
        model.addAttribute("resultNum", resultNum);
        return "capitals";
    }
}
