package com.practice.cab_booking;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
    PriorityQueue<Driver> matchedDrivers = matchingStrategy.matchDrivers(drivers, passengers.get(0));

    while (!matchedDrivers.isEmpty()) {
        Driver driver = matchedDrivers.poll(); // Get the highest-priority driver
        if (driver.getCab().canAccommodate(passengers.size())) {
            System.out.println("Requesting ride from driver: " + driver);

            if (driver.acceptRide()) {
                driver.getCab().setStatus(CabStatus.ON_TRIP);
                driver.getCab().addPassengers(passengers.size());

                // Calculate fare
                PricingStrategy pricingStrategy = PricingStrategyFactory.getPricingStrategy(
                    driver.getCab().getType(),
                    DemandHourCalculator.getCurrentDemandHour()
                );

                for (Passenger passenger : passengers) {
                    double fare = pricingStrategy.calculateFare(
                        passenger.getCurrentLocation(),
                        passenger.getDestination()
                    );
                    passenger.setIndividualFare(fare);
                }

                Trip trip = new Trip(
                    passengers, 
                    driver, 
                    passengers.get(0).getCurrentLocation(), 
                    passengers.get(0).getDestination()
                );
                trip.startTrip();
                return trip;
            } else {
                System.out.println("Driver " + driver.getName() + " declined the ride.");
                driver.declineRide();
            }
        }
    }

    throw new RuntimeException("No available drivers found for the requested trip.");
}

}
