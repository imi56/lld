package com.practice.cab_booking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabBookingDemo {
  public static void main(String[] args) {
    // Create the scoring weights for the driver matching strategy
    Map<DriverScoringStrategy, Double> scoringWeights = new HashMap<>();
    scoringWeights.put(new DistanceScoringStrategy(), 0.5);
    scoringWeights.put(new RatingScoringStrategy(), 0.3);
    // scoringWeights.put(new CompletedTripsScoringStrategy(), 0.2);

    // Create the matching strategy with the scoring weights
    DriverMatchingStrategy matchingStrategy = new MultiFactorMatchingStrategy(scoringWeights);

    // Create the cab booking service with the matching strategy
    CabBookingService bookingService = new CabBookingService(matchingStrategy);

    // Register drivers with different cab types and locations
    Location driverLoc1 = new Location(0, 0);
    Location driverLoc2 = new Location(2, 2);
    Location driverLoc3 = new Location(1, 1);

    Cab miniCab = new Cab("CAB001", CabType.MINI, driverLoc1, 4);
    Cab sedanCab = new Cab("CAB002", CabType.SEDAN, driverLoc2, 4);
    Cab xlCab = new Cab("CAB003", CabType.XL, driverLoc3, 6);

    Driver driver1 = new Driver("D1", "John", miniCab, 4.5);
    Driver driver2 = new Driver("D2", "Alice", sedanCab, 4.8);
    Driver driver3 = new Driver("D3", "Bob", xlCab, 4.2);

    // Register the drivers in the booking service
    bookingService.registerDriver(driver1);
    bookingService.registerDriver(driver2);
    bookingService.registerDriver(driver3);

    // Create passengers with their current location and destination
    List<Passenger> passengers = Arrays.asList(
        new Passenger("P1", "Tom", new Location(0.5, 0.5), new Location(5.0, 5.0)),
        new Passenger("P2", "Jerry", new Location(0.5, 0.5), new Location(3.0, 3.0)),
        new Passenger("P3", "Mike", new Location(0.8, 0.8), new Location(4.0, 2.0))
    );

    // Print the booking information
    System.out.println("Booking cab for " + passengers.size() + " passengers...");

    try {
        // Book the cab
        Trip trip = bookingService.bookCab(passengers);

        // Wait for the trip to complete (simulate a 2-second trip)
        Thread.sleep(2000);

        // End the trip and process payment
        trip.endTrip();
    } catch (Exception e) {
        System.out.println("Failed to book cab: " + e.getMessage());
    }
}
}
