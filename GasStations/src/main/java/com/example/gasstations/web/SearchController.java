package com.example.gasstations.web;

import com.example.gasstations.domain.dtos.model.StationDTO;
import com.example.gasstations.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class SearchController {

    private final StationService stationService;

    @Autowired
    public SearchController(StationService stationService) {
        this.stationService = stationService;
    }


    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<StationDTO> stations = this.stationService.searchStation(query);

        model.addAttribute("stations", stations);

        if (stations.isEmpty()) {
            model.addAttribute("errorMessage", "No stations found with the given name.");
        }

        return "search";
    }


}
