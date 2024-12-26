package com.practice.cab_booking;

import java.util.List;

public interface DriverMatchingStrategy {
  List<Driver> matchDrivers(List<Driver> drivers, Passenger passenger);
}
