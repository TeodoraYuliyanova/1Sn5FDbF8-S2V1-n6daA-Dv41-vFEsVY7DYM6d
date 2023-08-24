package com.example.gasstations.services;

import com.example.gasstations.domain.dtos.model.StationDTO;
import com.example.gasstations.domain.entities.FuelPriceStatistics;
import com.example.gasstations.domain.entities.StationEntity;
import com.example.gasstations.repositories.StationRepository;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StationService {

    private final StationRepository stationRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public StationService(StationRepository stationRepository, ModelMapper modelMapper) {
        this.stationRepository = stationRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        fetchDataAndStoreInDatabase();
    }

    public void fetchDataAndStoreInDatabase() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            JsonNode response = restTemplate.getForObject(
                    "https://wejago.de/assets/data/gas-stations-data.json",
                    JsonNode.class
            );

            List<StationEntity> openStations = new ArrayList<>();
            JsonNode stations = response.get("stations");
            for (JsonNode stationNode : stations) {
                if (stationNode.get("isOpen").asBoolean()) {
                    openStations.add(convertToStationEntity(stationNode));
                }
            }

            stationRepository.saveAll(openStations);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private StationEntity convertToStationEntity(JsonNode jsonData) {
        StationEntity stationEntity = new StationEntity();

        stationEntity.setName(jsonData.get("name").asText());
        stationEntity.setBrand(jsonData.get("brand").asText());
        stationEntity.setStreet(jsonData.get("street").asText());
        stationEntity.setPlace(jsonData.get("place").asText());
        stationEntity.setLat(jsonData.get("lat").asDouble());
        stationEntity.setLng(jsonData.get("lng").asDouble());

        JsonNode distNode = jsonData.get("dist");
        if (distNode != null) {
            stationEntity.setDist(distNode.asDouble());
        }

        JsonNode dieselNode = jsonData.get("diesel");
        if (dieselNode != null) {
            stationEntity.setDiesel(dieselNode.asDouble());
        }

        JsonNode e5Node = jsonData.get("e5");
        if (e5Node != null) {
            stationEntity.setE5(e5Node.asDouble());
        }

        JsonNode e10Node = jsonData.get("e10");
        if (e10Node != null) {
            stationEntity.setE10(e10Node.asDouble());
        }

        JsonNode isOpenNode = jsonData.get("isOpen");
        if (isOpenNode != null) {
            stationEntity.setOpen(isOpenNode.asBoolean());
        }

        JsonNode houseNumberNode = jsonData.get("houseNumber");
        if (houseNumberNode != null) {
            stationEntity.setHouseNumber(houseNumberNode.asText());
        }

        JsonNode postCodeNode = jsonData.get("postCode");
        if (postCodeNode != null) {
            stationEntity.setPostCode(postCodeNode.asInt());
        }

        return stationEntity;
    }

    public List<StationDTO> searchStation(String name) {
        return this.stationRepository.findByName(name)
                .stream()
                .map(stationEntity -> this.modelMapper.map(stationEntity,StationDTO.class))
                .collect(Collectors.toList());
    }

    public FuelPriceStatistics calculateStatisticsForFuelType(String fuelType) {
        List<StationEntity> stations = this.stationRepository.findAll();

        List<Double> pricesForFuelType = stations.stream()
                .map(station -> {
                    Double price = null;
                    if (fuelType.equalsIgnoreCase("e5")) {
                        price = station.getE5();
                    } else if (fuelType.equalsIgnoreCase("e10")) {
                        price = station.getE10();
                    } else if (fuelType.equalsIgnoreCase("diesel")) {
                        price = station.getDiesel();
                    }
                    return price;
                })
                .filter(Objects::nonNull)
                .filter(price -> price > 0.0)
                .collect(Collectors.toList());

        FuelPriceStatistics statistics = new FuelPriceStatistics();
        if (!pricesForFuelType.isEmpty()) {
            double median = calculateMedian(pricesForFuelType);
            double max = pricesForFuelType.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
            double min = pricesForFuelType.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);

            statistics.setMedian(median);
            statistics.setMax(max);
            statistics.setMin(min);
        }

        return statistics;
    }


    private double calculateMedian(List<Double> values) {
        int size = values.size();
        if (size == 0) {
            return 0.0;
        }

        Collections.sort(values);

        if (size % 2 == 1) {
            return values.get(size / 2);
        } else {
            double mid1 = values.get(size / 2 - 1);
            double mid2 = values.get(size / 2);
            return (mid1 + mid2) / 2;
        }
    }

}
