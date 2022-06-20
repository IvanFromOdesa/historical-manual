package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.*;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.repository.StateRepository;
import com.historicalreferencebook.historicalreferencebook.repository.WarRepository;
import com.historicalreferencebook.historicalreferencebook.service.StateService;
import com.historicalreferencebook.historicalreferencebook.service.StateWarService;
import com.historicalreferencebook.historicalreferencebook.service.WarService;
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
public class WarController {
    @Autowired
    WarService warService;
    @Autowired
    WarRepository warRepository;

    @Autowired
    StateRepository stateRepository;
    @Autowired
    StateService stateService;
    @Autowired
    StateWarService stateWarService;

    @GetMapping("/wars")
    public String showAllWars(Model model, @ModelAttribute("myWar") War myWar){
        List<War> listWars = warService.findAllWars();
        List<StateWar> stateWars = stateWarService.findAllStateWars();
        Boolean flag = false;
        model.addAttribute("flag", flag);
        model.addAttribute("listWars", listWars);
        model.addAttribute("stateWars", stateWars);
        return "wars";
    }

    @GetMapping("/wars/new")
    public String showNewWarForm(Model model){
        List <State>  listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("war", new War());
        model.addAttribute("pageTitleW", "Add New War");
        return "war_form";
    }

    @PostMapping("/wars/save")
    public String saveWar (@Valid Integer idState, War requestWar, RedirectAttributes redirectAttributes) throws StateNotFoundException {
        warRepository.save(requestWar);
        redirectAttributes.addFlashAttribute("messageW", "The war has been saved successfully!");
        return "redirect:/wars";
    }

    @GetMapping("/wars/edit/{id}")
    public String showEditWarForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            War war = warService.findWarById(id);
            List<State>  listStates = stateService.findAll();
            model.addAttribute("listStates", listStates);
            model.addAttribute("war", war);
            model.addAttribute("pageTitleW", "Edit War (ID: " + id + ")");
            return "war_form";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageW", e.getMessage());
            return "redirect:/wars";
        }
    }

    @GetMapping("/wars/delete/{id}")
    public String deleteWar(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            warService.deleteWar(id);
            redirectAttributes.addFlashAttribute("messageW", "The war ID " + id + " has been deleted!");
        } catch (StateNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageW", e.getMessage());
        }
        return "redirect:/wars";
    }

    @GetMapping("/wars/findByName")
    public String showWarsByName(Model model, @ModelAttribute("myWar") War myWar, BindingResult result){
        List<War> listWars = this.warRepository.findWarByWarNameContaining(myWar.getWarName());
        model.addAttribute("listWars", listWars);
        return "wars";
    }

    @GetMapping("/wars/findByDate")
    public String showWarsByDate(Model model, @ModelAttribute("myWar") War myWar, BindingResult result){
        List<War> listWars = this.warRepository.findWarsByDateOfBeginningBetween(myWar.getDateOfBeginning(), myWar.getDateOfEnd());
        model.addAttribute("listWars", listWars);
        return "wars";
    }
}
