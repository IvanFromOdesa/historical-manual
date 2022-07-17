package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.Figure;
import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.jpql.Person;
import com.historicalreferencebook.historicalreferencebook.service.FigureService;
import com.historicalreferencebook.historicalreferencebook.service.StateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Controller
@AllArgsConstructor
public class FigureController {

    private final FigureService figureService;

    private final StateService stateService;

    @GetMapping("/figures")
    public String showAllFigures(Model model,  @ModelAttribute("myFigure") Figure myFigure){
        Set<Figure> listFigures = figureService.findAllFigures();
        model.addAttribute("listFigures", listFigures);
        return "figures";
    }

    @GetMapping("/figures/new")
    public String showNewFigureForm(Model model){
        Set<State> listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("figure1", new Figure());
        model.addAttribute("pageTitleF", "Add New Figure");
        return "figure_form";
    }

    @PostMapping("/figures/save")
    public String saveFigure (Figure requestFigure, RedirectAttributes redirectAttributes){
        figureService.saveFigure(requestFigure);
        redirectAttributes.addFlashAttribute("messageF", "The figure has been saved successfully!");
        return "redirect:/figures";
    }

    @GetMapping("/figures/edit/{id}")
    public String showEditFigureForm(@PathVariable("id") Integer id, Model model){
        Figure figure = figureService.findFigureById(id);
        Set<State> listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("figure1", figure);
        model.addAttribute("pageTitleF", "Edit Figure (ID: " + id + ")");
        return "figure_form";
    }

    @GetMapping("/figures/delete/{id}")
    public String deleteFigure(@PathVariable("id") Integer id, Model model,
                               RedirectAttributes redirectAttributes){
        figureService.deleteFigure(id);
        redirectAttributes.addFlashAttribute("messageF", "The figure ID " + id + " has been deleted!");
        return "redirect:/figures";
    }

    @GetMapping("/figures/findByName")
    public String showFiguresByName(Model model, @ModelAttribute("myFigure") Figure myFigure) {
        Set<Figure> listFigures = figureService.findByName(myFigure.getFullName());
        model.addAttribute("listFigures", listFigures);
        return "figures";
    }

    @GetMapping("/figures/findFiguresAndGovernors")
    public String showFiguresAndGovernors(Model model) {
        Set<Person> list = figureService.findAllPeople();
        model.addAttribute("list", list);
        return "persons";
    }

    @GetMapping("/persons/export")
    public void exportPersonsToPDF(HttpServletResponse response) throws IOException {
       figureService.exportToPdfAllPeople(response);
    }
}
