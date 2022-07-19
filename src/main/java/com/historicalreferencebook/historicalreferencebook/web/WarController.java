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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class WarController {

    private final WarService warService;

    private final StateService stateService;

    private final StateWarService stateWarService;

    @GetMapping("/wars")
    public String showAllWars(Model model, @ModelAttribute("myWar") War myWar) {
        List<War> listWars = warService.findAllWars();
        List<StateWar> stateWars = stateWarService.findAllStateWars();
        Boolean flag = false;
        model.addAttribute("flag", flag);
        model.addAttribute("listWars", listWars);
        model.addAttribute("stateWars", stateWars);
        return "wars";
    }

    @GetMapping("/wars/new")
    public String showNewWarForm(Model model) {
        List<State> listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("war", new War());
        model.addAttribute("pageTitleW", "Add New War");
        return "war_form";
    }

    @PostMapping("/wars/save")
    public String saveWar (War requestWar, RedirectAttributes redirectAttributes) {
        warService.saveWar(requestWar);
        redirectAttributes.addFlashAttribute("messageW", "The war has been saved successfully!");
        return "redirect:/wars";
    }

    @GetMapping("/wars/edit/{id}")
    public String showEditWarForm(@PathVariable("id") Integer id, Model model) {
        War war = warService.findWarById(id);
        List<State> listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("war", war);
        model.addAttribute("pageTitleW", "Edit War (ID: " + id + ")");
        return "war_form";
    }

    @GetMapping("/wars/delete/{id}")
    public String deleteWar(@PathVariable("id") Integer id, Model model,
                            RedirectAttributes redirectAttributes) {
        warService.deleteWar(id);
        redirectAttributes.addFlashAttribute("messageW", "The war ID " + id + " has been deleted!");
        return "redirect:/wars";
    }

    @GetMapping("/wars/findByName")
    public String showWarsByName(Model model, @ModelAttribute("myWar") War myWar) {
        Set<War> listWars = warService.findWarsByName(myWar.getWarName());
        model.addAttribute("listWars", listWars);
        return "wars";
    }

    @GetMapping("/wars/findByDate")
    public String showWarsByDate(Model model, @ModelAttribute("myWar") War myWar) {
        Set<War> listWars = warService.findWarsByDate(myWar.getDateOfBeginning(), myWar.getDateOfEnd());
        model.addAttribute("listWars", listWars);
        return "wars";
    }
}
