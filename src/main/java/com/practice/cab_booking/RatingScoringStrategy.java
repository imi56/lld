package com.practice.cab_booking;

public class RatingScoringStrategy implements DriverScoringStrategy {
  @Override
  public double calculateScore(Driver driver, Passenger passenger, Location destination) {
      return driver.getRating() / 5.0;
  }
}
