package com.practice.cab_booking;

import java.util.ArrayList;
import java.util.List;

public class Driver {
  private String id;
    private String name;
    private Cab cab;
    private double rating;
    private List<Trip> completedTrips;

    public Driver(String id, String name, Cab cab, double rating) {
        this.id = id;
        this.name = name;
        this.cab = cab;
        this.rating = rating;
        this.completedTrips = new ArrayList<>();
    }

    public Cab getCab() {
        return cab;
    }

    public double getRating() {
        return rating;
    }

    public int getCompletedTripsCount() {
        return completedTrips.size();
    }

    public boolean acceptRide() {
        return Math.random() > 0.2; // Simulates acceptance with 80% probability
    }

    public void declineRide() {
        System.out.println("Driver " + name + " declined the ride.");
    }

    public void addCompletedTrip(Trip trip) {
        completedTrips.add(trip);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
