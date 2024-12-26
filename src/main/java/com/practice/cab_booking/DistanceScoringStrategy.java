package com.practice.cab_booking;

public class DistanceScoringStrategy implements DriverScoringStrategy{
  @Override
  public double calculateScore(Driver driver, Passenger passenger, Location destination) {
      double distance = driver.getCab().getCurrentLocation().distanceTo(passenger.getCurrentLocation());
      return 1 / (1 + distance);
  }
}
