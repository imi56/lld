package com.practice.cab_booking;

import java.util.ArrayList;
import java.util.List;

public class CabBookingService {
  private final List<Driver> drivers = new ArrayList<>();
    private DriverMatchingStrategy matchingStrategy;
    
    // private static CabBookingService instance;
    
    // public static synchronized CabBookingService getInstance(DriverMatchingStrategy matchingStrategy) {
    //     if (instance == null) {
    //         instance = new CabBookingService(matchingStrategy);
    //     }
    //     return instance;
    // }


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
            if (driver.getCab().canAccommodate(passengers.size())) {
                System.out.println("Requesting ride from driver: " + driver);
                if (driver.acceptRide()) {
                    driver.getCab().setStatus(CabStatus.ON_TRIP);
                    driver.getCab().addPassengers(passengers.size());

                    // Get the Surge Pricing Strategy with the appropriate base fare strategy
                    PricingStrategy pricingStrategy = PricingStrategyFactory.getPricingStrategy(
                        driver.getCab().getType(),
                        DemandHourCalculator.getCurrentDemandHour());

                    // Calculate the fare for each passenger
                    for (Passenger passenger : passengers) {
                        double fare = pricingStrategy.calculateFare(
                            passenger.getCurrentLocation(),
                            passenger.getDestination()
                        );
                        passenger.setIndividualFare(fare);
                    }

                    // Create and start the trip
                    Trip trip = new Trip(passengers, driver);
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
