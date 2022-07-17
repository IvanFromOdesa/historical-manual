package com.historicalreferencebook.historicalreferencebook.web;

import com.historicalreferencebook.historicalreferencebook.domain.State;
import com.historicalreferencebook.historicalreferencebook.domain.Statistics;
import com.historicalreferencebook.historicalreferencebook.jpql.StatesMoreAvgPopulation;
import com.historicalreferencebook.historicalreferencebook.service.StateService;
import com.historicalreferencebook.historicalreferencebook.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Controller
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    private final StateService stateService;

    @GetMapping("/statistics")
    public String showAllStats(Model model){
        Set<Statistics> listStats = statisticsService.findAllStats();
        model.addAttribute("listStats", listStats);
        return "statistics";
    }

    @GetMapping("/statistics/new")
    public String showNewStatForm(Model model){
        Set<State> listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("stat", new Statistics());
        model.addAttribute("pageTitleS", "Add New Statistics");
        return "statistic_form";
    }

    @PostMapping("/statistics/save")
    public String saveStat (Statistics requestStat, RedirectAttributes redirectAttributes) {
        statisticsService.saveStatistics(requestStat);
        redirectAttributes.addFlashAttribute("messageS", "The statistics has been saved successfully!");
        return "redirect:/statistics";
    }

    @GetMapping("/statistics/edit/{id}")
    public String showEditStatForm(@PathVariable("id") Integer id, Model model) {
        Statistics statistics = statisticsService.findStatById(id);
        Set<State> listStates = stateService.findAll();
        model.addAttribute("listStates", listStates);
        model.addAttribute("stat", statistics);
        model.addAttribute("pageTitleS", "Edit Statistics (ID: " + id + ")");
        return "statistic_form";
    }

    @GetMapping("/statistics/delete/{id}")
    public String deleteStat(@PathVariable("id") Integer id, Model model,
                             RedirectAttributes redirectAttributes) {
        statisticsService.deleteStat(id);
        redirectAttributes.addFlashAttribute("messageS", "The statistics ID " + id + " has been deleted!");
        return "redirect:/statistics";
    }

    @GetMapping("/statistics/moreThanAvgPopulation")
    public String showStatesWithPopulationDensityMoreAvg(Model model) {
        Set<StatesMoreAvgPopulation> listStates1 = statisticsService.findStatesWithHigherPopDensity();
        model.addAttribute("listStates1", listStates1);
        return "statesMoreAvgPop";
    }

    @GetMapping("/statistics/export")
    public void exportDensityToPDF(HttpServletResponse response) throws IOException {
        statisticsService.exportToPdfStatesDensity(response);
    }
}
