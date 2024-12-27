package com.practice.cab_booking;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MultiFactorMatchingStrategy implements DriverMatchingStrategy {
  private final Map<DriverScoringStrategy, Double> scoringWeights;

    public MultiFactorMatchingStrategy(Map<DriverScoringStrategy, Double> scoringWeights) {
        this.scoringWeights = scoringWeights;
    }

    @Override
    public PriorityQueue<Driver> matchDrivers(List<Driver> drivers, Passenger passenger) {
			// Create a PriorityQueue directly to store drivers ordered by their score
			PriorityQueue<Driver> sortedDrivers = new PriorityQueue<>(
				(d1, d2) -> {
					double score1 = calculateTotalScore(d1, passenger);
					double score2 = calculateTotalScore(d2, passenger);
					return Double.compare(score2, score1); // Descending order by score
				}
			);

			// Add only AVAILABLE drivers to the PriorityQueue
			for (Driver driver : drivers) {
				if (driver.getCab().getStatus() == CabStatus.AVAILABLE) {
						sortedDrivers.offer(driver);
				}
			}

      return sortedDrivers;
    }

    private double calculateTotalScore(Driver driver, Passenger passenger) {
			double totalScore = 0.0;
			for (Map.Entry<DriverScoringStrategy, Double> entry : scoringWeights.entrySet()) {
				DriverScoringStrategy strategy = entry.getKey();
				double weight = entry.getValue();
				totalScore += strategy.calculateScore(driver, passenger, passenger.getCurrentLocation()) * weight;
			}
			return totalScore;
    }

}
