package com.practice.cab_booking;

public class SurgePricingStrategy implements PricingStrategy {
  private static final double SURGE_MULTIPLIER = 2.0; // Surge multiplier (e.g., 2x)
    private PricingStrategy baseFareStrategy; // To hold the actual fare calculation strategy (MiniCab, Sedan, etc.)

    public SurgePricingStrategy(PricingStrategy baseFareStrategy) {
        this.baseFareStrategy = baseFareStrategy; // Inject the base fare strategy
    }

    @Override
    public double calculateFare(Location start, Location end) {
        double baseFare = baseFareStrategy.calculateFare(start, end); // Get base fare from the actual strategy
        return baseFare * SURGE_MULTIPLIER; // Apply surge pricing multiplier
    }
}
