package com.practice.cab_booking;

public interface PricingStrategy {
  double calculateFare(Location start, Location end);
}
