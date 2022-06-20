package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.Governor;
import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.GovernorRepository;
import com.historicalreferencebook.historicalreferencebook.repository.StateRepository;
import com.historicalreferencebook.historicalreferencebook.service.GovernorService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class GovernorController {
    @Autowired
    GovernorService governorService;
    @Autowired
    GovernorRepository governorRepository;

    @Autowired
    StateRepository stateRepository;
    @Autowired
    StateService stateService;

    @GetMapping("/governors")
    public String showAllGovernors(Model model,  @ModelAttribute("myGovernor") Governor myGovernor){
        List<Governor> listGovernors = governorService.findAllGovernors();
        model.addAttribute("listGovernors", listGovernors);
        return "governors";
    }

    @GetMapping("/governors/new")
    public String showNewGovernorForm(Model model){
        List <State>  listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("governor", new Governor());
        model.addAttribute("pageTitleG", "Add New Governor");
        return "governor_form";
    }

    @PostMapping("/governors/save")
    public String saveGovernor (Governor requestGovernor, RedirectAttributes redirectAttributes){
        governorRepository.save(requestGovernor);
        redirectAttributes.addFlashAttribute("messageG", "The governor has been saved successfully!");
        return "redirect:/governors";
    }

    @GetMapping("/governors/edit/{id}")
    public String showEditGovernorForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            Governor governor = governorService.findGovernorById(id);
            List <State>  listStates = stateService.findAll();
            model.addAttribute("listStates", listStates);
            model.addAttribute("governor", governor);
            model.addAttribute("pageTitleG", "Edit Governor (ID: " + id + ")");
            return "governor_form";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageG", e.getMessage());
            return "redirect:/governors";
        }
    }

    @GetMapping("/governors/delete/{id}")
    public String deleteGovernor(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            governorService.deleteGovernor(id);
            redirectAttributes.addFlashAttribute("messageG", "The governor ID " + id + " has been deleted!");
        } catch (StateNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageG", e.getMessage());
        }
        return "redirect:/governors";
    }

    @GetMapping("/governors/showByDate")
    public String showGovernorsNumByDate (Model model, @ModelAttribute("myGovernor") Governor myGovernor, BindingResult result){
        Integer resultNumG = governorRepository.findGovernorsByDateOfIntercessionAfter(myGovernor.getDateOfIntercession());
        List <Governor> listGovernors = governorRepository.findAllByDateOfIntercessionAfter(myGovernor.getDateOfIntercession());
        model.addAttribute("listGovernors", listGovernors);
        model.addAttribute("resultNumG", resultNumG);
        return "governors";
    }

    @GetMapping("/governors/showByNoGovernor")
    public String showStatesByNoGovernor(Model model){
        List<Object[]> listState1 = governorRepository.findStatesWithNoGovernors();
        List<State> listState = new ArrayList<>();
        for(Object[] row: listState1){
            listState.add(new State((Integer)row[0], (String)row[1]));
        }
        model.addAttribute("list", listState);
        return "statesWithNoGovernor";
    }
}
