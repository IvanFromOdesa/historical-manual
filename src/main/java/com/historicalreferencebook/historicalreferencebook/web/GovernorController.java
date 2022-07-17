package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.Governor;
import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.service.GovernorService;
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
public class GovernorController {

    private final GovernorService governorService;

    private final StateService stateService;

    @GetMapping("/governors")
    public String showAllGovernors(Model model,  @ModelAttribute("myGovernor") Governor myGovernor) {
        Set<Governor> listGovernors = governorService.findAllGovernors();
        model.addAttribute("listGovernors", listGovernors);
        return "governors";
    }

    @GetMapping("/governors/new")
    public String showNewGovernorForm(Model model) {
        Set<State> listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("governor", new Governor());
        model.addAttribute("pageTitleG", "Add New Governor");
        return "governor_form";
    }

    @PostMapping("/governors/save")
    public String saveGovernor (Governor requestGovernor, RedirectAttributes redirectAttributes) {
        governorService.saveGovernor(requestGovernor);
        redirectAttributes.addFlashAttribute("messageG", "The governor has been saved successfully!");
        return "redirect:/governors";
    }

    @GetMapping("/governors/edit/{id}")
    public String showEditGovernorForm(@PathVariable("id") Integer id, Model model) {
        Governor governor = governorService.findGovernorById(id);
        Set<State> listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("governor", governor);
        model.addAttribute("pageTitleG", "Edit Governor (ID: " + id + ")");
        return "governor_form";
    }

    @GetMapping("/governors/delete/{id}")
    public String deleteGovernor(@PathVariable("id") Integer id, Model model,
                                 RedirectAttributes redirectAttributes) {
        governorService.deleteGovernor(id);
        redirectAttributes.addFlashAttribute("messageG", "The governor ID " + id + " has been deleted!");
        return "redirect:/governors";
    }

    @GetMapping("/governors/showByDate")
    public String showGovernorsNumByDate (Model model, @ModelAttribute("myGovernor") Governor myGovernor) {
        Integer resultNumG = governorService.findNumOfGovernorsElectedAfter(myGovernor.getDateOfIntercession());
        Set<Governor> listGovernors = governorService.showGovernorsElectedAfter(myGovernor.getDateOfIntercession());
        model.addAttribute("listGovernors", listGovernors);
        model.addAttribute("resultNumG", resultNumG);
        return "governors";
    }

    @GetMapping("/governors/showByNoGovernor")
    public String showStatesWithNoGovernor(Model model) {
        Set<State> listState = governorService.showStatesWithoutGovernors();
        model.addAttribute("list", listState);
        return "statesWithNoGovernor";
    }
}
