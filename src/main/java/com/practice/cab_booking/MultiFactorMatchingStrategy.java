package com.practice.cab_booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiFactorMatchingStrategy implements DriverMatchingStrategy {
  private final Map<DriverScoringStrategy, Double> scoringWeights;

    public MultiFactorMatchingStrategy(Map<DriverScoringStrategy, Double> scoringWeights) {
        this.scoringWeights = scoringWeights;
    }

    @Override
    public List<Driver> matchDrivers(List<Driver> drivers, Passenger passenger) {
        Map<Driver, Double> driverScores = new HashMap<>();

        for (Driver driver : drivers) {
            double totalScore = 0.0;

            for (Map.Entry<DriverScoringStrategy, Double> entry : scoringWeights.entrySet()) {
                DriverScoringStrategy strategy = entry.getKey();
                double weight = entry.getValue();
                totalScore += strategy.calculateScore(driver, passenger, passenger.getCurrentLocation()) * weight;
            }

            driverScores.put(driver, totalScore);
        }

        List<Driver> sortedDrivers = new ArrayList<>(drivers);
        sortedDrivers.sort((d1, d2) -> Double.compare(driverScores.get(d2), driverScores.get(d1)));

        return sortedDrivers;
    }
}
