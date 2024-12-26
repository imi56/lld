package com.practice.cab_booking;

public interface FareCalculationStrategy {
  double calculateFare(Location start, Location end, TimePeriod timePeriod, double demandMultiplier);
}