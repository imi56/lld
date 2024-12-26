package com.practice.cab_booking;

public class FareCalculator {
  private PricingStrategy pricingStrategy;

    public FareCalculator(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double calculateTotalFare(Location start, Location end) {
        // Calculate the total fare using the selected pricing strategy (includes base fare and surge)
        return pricingStrategy.calculateFare(start, end);
    }
}
