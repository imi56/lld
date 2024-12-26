package com.practice.cab_booking;

import java.util.ArrayList;
import java.util.List;

public class CabBookingService {
  private final List<Driver> drivers = new ArrayList<>();
    private DriverMatchingStrategy matchingStrategy;

    public CabBookingService(DriverMatchingStrategy matchingStrategy) {
        this.matchingStrategy = matchingStrategy;
    }

    public void setMatchingStrategy(DriverMatchingStrategy matchingStrategy) {
        this.matchingStrategy = matchingStrategy;
    }

    public void registerDriver(Driver driver) {
        drivers.add(driver);
    }

    public Trip bookCab(List<Passenger> passengers) {
        List<Driver> matchedDrivers = matchingStrategy.matchDrivers(drivers, passengers.get(0));

        for (Driver driver : matchedDrivers) {
            if (driver.getCab().getStatus() == CabStatus.AVAILABLE && 
                driver.getCab().canAccommodate(passengers.size())) {
                System.out.println("Requesting ride from driver: " + driver);
                if (driver.acceptRide()) {
                    driver.getCab().setStatus(CabStatus.ON_TRIP);
                    driver.getCab().addPassengers(passengers.size());

                    // Get the Surge Pricing Strategy with the appropriate base fare strategy
                    PricingStrategy surgePricingStrategy = PricingStrategyFactory.getPricingStrategy(driver.getCab().getType());

                    // Create a FareCalculator with the selected pricing strategy
                    FareCalculator fareCalculator = new FareCalculator(surgePricingStrategy);

                    // Calculate the fare for each passenger
                    for (Passenger passenger : passengers) {
                        double fare = fareCalculator.calculateTotalFare(
                            passenger.getCurrentLocation(),
                            passenger.getDestination()
                        );
                        passenger.setIndividualFare(fare);
                    }

                    // Create and start the trip
                    Trip trip = new Trip(passengers, driver, fareCalculator);
                    trip.startTrip();
                    return trip;
                } else {
                    driver.declineRide();
                }
            }
        }
        throw new RuntimeException("No available drivers found for the requested trip.");
    }
}
