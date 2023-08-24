package com.example.gasstations.domain.entities;

public class FuelPriceStatistics {

    private Double median;
    private Double max;
    private Double min;

    public FuelPriceStatistics() {
    }

    public Double getMedian() {
        return median;
    }

    public FuelPriceStatistics setMedian(Double median) {
        this.median = median;
        return this;
    }

    public Double getMax() {
        return max;
    }

    public FuelPriceStatistics setMax(Double max) {
        this.max = max;
        return this;
    }

    public Double getMin() {
        return min;
    }

    public FuelPriceStatistics setMin(Double min) {
        this.min = min;
        return this;
    }
}
