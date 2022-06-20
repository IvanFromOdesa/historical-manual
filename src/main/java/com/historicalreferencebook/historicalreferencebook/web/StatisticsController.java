package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.Governor;
import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.domain.Statistics;
import com.historicalreferencebook.historicalreferencebook.exceptions.StateNotFoundException;
import com.historicalreferencebook.historicalreferencebook.jpql.LanguageCount;
import com.historicalreferencebook.historicalreferencebook.jpql.StatesMoreAvgPopulation;
import com.historicalreferencebook.historicalreferencebook.repository.GovernorRepository;
import com.historicalreferencebook.historicalreferencebook.repository.StateRepository;
import com.historicalreferencebook.historicalreferencebook.repository.StatisticsRepository;
import com.historicalreferencebook.historicalreferencebook.service.GovernorService;
import com.historicalreferencebook.historicalreferencebook.service.StateService;
import com.historicalreferencebook.historicalreferencebook.service.StatisticsService;
import com.historicalreferencebook.historicalreferencebook.util.StateDensityPDFExporter;
import com.historicalreferencebook.historicalreferencebook.util.StatePDFExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    StatisticsRepository statisticsRepository;

    @Autowired
    StateRepository stateRepository;
    @Autowired
    StateService stateService;

    @GetMapping("/statistics")
    public String showAllStats(Model model){
        List<Statistics> listStats = statisticsService.findAllStats();
        model.addAttribute("listStats", listStats);
        return "statistics";
    }

    @GetMapping("/statistics/new")
    public String showNewStatForm(Model model){
        List <State>  listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("stat", new Statistics());
        model.addAttribute("pageTitleS", "Add New Statistics");
        return "statistic_form";
    }

    @PostMapping("/statistics/save")
    public String saveStat (Statistics requestStat, RedirectAttributes redirectAttributes){
        statisticsRepository.save(requestStat);
        redirectAttributes.addFlashAttribute("messageS", "The statistics has been saved successfully!");
        return "redirect:/statistics";
    }

    @GetMapping("/statistics/edit/{id}")
    public String showEditStatForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            Statistics statistics = statisticsService.findStatById(id);
            List <State>  listStates = stateService.findAll();
            model.addAttribute("listStates", listStates);
            model.addAttribute("stat", statistics);
            model.addAttribute("pageTitleS", "Edit Statistics (ID: " + id + ")");
            return "statistic_form";
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageS", e.getMessage());
            return "redirect:/statistics";
        }
    }

    @GetMapping("/statistics/delete/{id}")
    public String deleteStat(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            statisticsService.deleteStat(id);
            redirectAttributes.addFlashAttribute("messageS", "The statistics ID " + id + " has been deleted!");
        } catch (StateNotFoundException e) {
            redirectAttributes.addFlashAttribute("messageS", e.getMessage());
        }
        return "redirect:/statistics";
    }

    @GetMapping("/statistics/moreThanAvgPopulation")
    public String showStatesWithPopulationDensityMoreAvg(Model model){
        List <Object[]> listStates = statisticsRepository.findStatesWithPopulationDensityMoreAvG();
        List <StatesMoreAvgPopulation> listStates1 = new ArrayList<>();
        for(Object[] row : listStates){
            listStates1.add(new StatesMoreAvgPopulation((String)row[0], (BigDecimal)row[1]));
        }
        model.addAttribute("listStates1", listStates1);
        return "statesMoreAvgPop";
    }

    @GetMapping("/statistics/export")
    public void exportDensityToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateFormat = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=states_" + currentDateFormat + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Object[]> listStates = statisticsRepository.findStatesWithPopulationDensityMoreAvG();
        List <StatesMoreAvgPopulation> listStates1 = new ArrayList<>();
        for(Object[] row : listStates){
            listStates1.add(new StatesMoreAvgPopulation((String)row[0], (BigDecimal)row[1]));
        }

        StateDensityPDFExporter exporter = new StateDensityPDFExporter(listStates1);
        exporter.export(response);
    }
}
