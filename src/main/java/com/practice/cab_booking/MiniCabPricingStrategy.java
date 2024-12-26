package com.practice.cab_booking;

public class MiniCabPricingStrategy implements PricingStrategy {
  private static final double BASE_FARE = 50;
    private static final double PER_KM_RATE = 10;

    @Override
    public double calculateFare(Location start, Location end) {
        double distance = start.distanceTo(end);
        return BASE_FARE + (distance * PER_KM_RATE);
    }
}
