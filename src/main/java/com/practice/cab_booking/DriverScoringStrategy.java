package com.practice.cab_booking;

public interface DriverScoringStrategy {
  double calculateScore(Driver driver, Passenger passenger, Location destination);
}
