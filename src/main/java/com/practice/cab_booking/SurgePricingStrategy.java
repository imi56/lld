package com.practice.cab_booking;

public class SurgePricingStrategy implements PricingStrategy {
  private static final double SURGE_MULTIPLIER = 2.0;
    private PricingStrategy cabFareStrategy;

    public SurgePricingStrategy(PricingStrategy cabFareStrategy) {
        this.cabFareStrategy = cabFareStrategy;
    }

    @Override
    public double calculateFare(Location start, Location end) {
        double baseFare = cabFareStrategy.calculateFare(start, end);
        return baseFare * SURGE_MULTIPLIER;
    }
}
