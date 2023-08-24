package com.example.gasstations.web;

import com.example.gasstations.domain.entities.FuelPriceStatistics;
import com.example.gasstations.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FuelPricesController {

    private final StationService stationService;

    @Autowired
    public FuelPricesController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/fuel-prices")
    public String getFuelPriceStatisticsPage(Model model, @RequestParam(required = false) String fuelType) {
        if (fuelType != null) {
            FuelPriceStatistics statistics = this.stationService.calculateStatisticsForFuelType(fuelType);
            model.addAttribute("fuelPriceStatistics", statistics);
        }

        return "fuel-prices";
    }


}
