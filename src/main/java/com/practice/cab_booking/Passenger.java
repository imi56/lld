package com.practice.cab_booking;

public class Passenger {
  private String id;
    private String name;
    private Location currentLocation;
    private Location destination;
    private double individualFare;

    public Passenger(String id, String name, Location currentLocation, Location destination) {
        this.id = id;
        this.name = name;
        this.currentLocation = currentLocation;
        this.destination = destination;
        this.individualFare = 0.0;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Location getDestination() {
        return destination;
    }

    public void setIndividualFare(double fare) {
        this.individualFare = fare;
    }

    public double getIndividualFare() {
        return individualFare;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", fare=" + individualFare +
                '}';
    }
}
