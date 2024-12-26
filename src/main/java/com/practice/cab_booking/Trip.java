package com.practice.cab_booking;

import java.util.List;

public class Trip {
  private List<Passenger> passengers;
    private Driver driver;
    private boolean isCompleted;
    private FareCalculator fareCalculator;

    public Trip(List<Passenger> passengers, Driver driver, FareCalculator fareCalculator) {
        this.passengers = passengers;
        this.driver = driver;
        this.fareCalculator = fareCalculator;
        this.isCompleted = false;
    }

    public void startTrip() {
        System.out.println("Trip started: Passengers=" + passengers + " with " + driver);
    }

    public void endTrip() {
        isCompleted = true;
        driver.addCompletedTrip(this);
        System.out.println("Trip completed: Passengers=" + passengers + " with " + driver);
        processPayment();
        driver.getCab().resetOccupancy();
        driver.getCab().setStatus(CabStatus.AVAILABLE);
    }

    private void processPayment() {
        for (Passenger passenger : passengers) {
            // Select the correct pricing strategy (base fare + surge pricing)
            PricingStrategy surgePricingStrategy = PricingStrategyFactory.getPricingStrategy(driver.getCab().getType());

            // Create the FareCalculator with the selected pricing strategy
            FareCalculator fareCalculator = new FareCalculator(surgePricingStrategy);

            // Calculate the total fare including surge pricing
            double fare = fareCalculator.calculateTotalFare(
                passenger.getCurrentLocation(),
                passenger.getDestination()
            );
            passenger.setIndividualFare(fare);
            System.out.println("Processing payment for " + passenger + ". Total fare: " + fare);
        }
    }
}
