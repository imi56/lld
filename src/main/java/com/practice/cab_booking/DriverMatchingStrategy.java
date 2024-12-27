package com.practice.cab_booking;

import java.util.List;
import java.util.PriorityQueue;

public interface DriverMatchingStrategy {
  PriorityQueue<Driver> matchDrivers(List<Driver> drivers, Passenger passenger);
}
