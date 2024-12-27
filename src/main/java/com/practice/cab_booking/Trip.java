package com.practice.cab_booking;

import java.util.List;

public class Trip {
  private List<Passenger> passengers;
    private Driver driver;
    private Location starLocation;
    private Location endLocation;

    public Trip(List<Passenger> passengers, Driver driver, Location starLocation, Location endLocation) {
        this.passengers = passengers;
        this.driver = driver;
    }

    public void startTrip() {
        System.out.println("Trip started: Passengers=" + passengers + " with " + driver);
    }

    public Driver getDriver() {
        return driver;
    }

    public void endTrip() {
        driver.addCompletedTrip(this);
        System.out.println("Trip completed: Passengers=" + passengers + " with " + driver);
        processPayment();
        driver.getCab().resetOccupancy();
        driver.getCab().setStatus(CabStatus.AVAILABLE);
    }

    private void processPayment() {
        for (Passenger passenger : passengers) {
            System.out.println("Processing payment for " + passenger + ". Total fare: " + passenger.getIndividualFare());
        }
    }
}
