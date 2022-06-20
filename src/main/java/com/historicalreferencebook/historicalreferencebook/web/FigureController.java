package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.Figure;
import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.jpql.Person;
import com.historicalreferencebook.historicalreferencebook.jpql.StatesMoreAvgPopulation;
import com.historicalreferencebook.historicalreferencebook.repository.FigureRepository;
import com.historicalreferencebook.historicalreferencebook.repository.StateRepository;
import com.historicalreferencebook.historicalreferencebook.service.FigureService;
import com.historicalreferencebook.historicalreferencebook.service.StateService;
import com.historicalreferencebook.historicalreferencebook.util.PersonPDFExporter;
import com.historicalreferencebook.historicalreferencebook.util.StateDensityPDFExporter;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class FigureController {
    @Autowired
    FigureService figureService;

    @Autowired
    FigureRepository figureRepository;

    @Autowired
    StateRepository stateRepository;
    @Autowired
    StateService stateService;

    @GetMapping("/figures")
    public String showAllFigures(Model model,  @ModelAttribute("myFigure") Figure myFigure){
        List<Figure> listFigures = figureService.findAllFigures();
        model.addAttribute("listFigures", listFigures);
        return "figures";
    }

    @GetMapping("/figures/new")
    public String showNewFigureForm(Model model){
        List <State>  listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("figure1", new Figure());
        model.addAttribute("pageTitleF", "Add New Figure");
        return "figure_form";
    }

    @PostMapping("/figures/save")
    public String saveFigure (Figure requestFigure, RedirectAttributes redirectAttributes){
        figureRepository.save(requestFigure);
        redirectAttributes.addFlashAttribute("messageF", "The figure has been saved successfully!");
        return "redirect:/figures";
    }

    @GetMapping("/figures/edit/{id}")
    public String showEditFigureForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            Figure figure = figureService.findFigureById(id);
            List <State>  listStates = stateService.findAll();
            model.addAttribute("listStates", listStates);
            model.addAttribute("figure1", figure);
            model.addAttribute("pageTitleF", "Edit Figure (ID: " + id + ")");
            return "figure_form";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageF", e.getMessage());
            return "redirect:/figures";
        }
    }

    @GetMapping("/figures/delete/{id}")
    public String deleteFigure(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            figureService.deleteFigure(id);
            redirectAttributes.addFlashAttribute("messageF", "The figure ID " + id + " has been deleted!");
        } catch (StateNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageF", e.getMessage());
        }
        return "redirect:/figures";
    }

    @GetMapping("/figures/findByName")
    public String showFiguresByName(Model model, @ModelAttribute("myFigure") Figure myFigure, BindingResult result){
        List<Figure> listFigures = this.figureRepository.findByFullNameContaining(myFigure.getFullName());
        model.addAttribute("listFigures", listFigures);
        return "figures";
    }

    @GetMapping("/figures/findFiguresAndGovernors")
    public String showFiguresAndGovernors(Model model){
        List <Person> list = perform();
        model.addAttribute("list", list);
        return "persons";
    }

    @GetMapping("/persons/export")
    public void exportPersonsToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateFormat = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=states_" + currentDateFormat + ".pdf";
        response.setHeader(headerKey, headerValue);

        List <Person> list = perform();

        PersonPDFExporter exporter = new PersonPDFExporter(list);
        exporter.export(response);
    }

    private List <Person> perform() {
        List<Object[]> listPersons1 = figureRepository.findFiguresAndGovernors();
        List <Person> list = new ArrayList<>();
        for(Object[] row: listPersons1){
            list.add(new Person((String)row[0], (String)row[1], (String)row[2]));
        }
        return list;
    }
}
