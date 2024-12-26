package com.practice.cab_booking;

public class Cab {
  private String id;
    private CabType type;
    private Location currentLocation;
    private CabStatus status;
    private int maxCapacity;
    private int currentOccupancy;

    public Cab(String id, CabType type, Location currentLocation, int maxCapacity) {
        this.id = id;
        this.type = type;
        this.currentLocation = currentLocation;
        this.status = CabStatus.AVAILABLE;
        this.maxCapacity = maxCapacity;
        this.currentOccupancy = 0;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public CabType getType() {
        return type;
    }

    public void setStatus(CabStatus status) {
        this.status = status;
    }

    public CabStatus getStatus() {
        return status;
    }

    public boolean canAccommodate(int additionalPassengers) {
        return currentOccupancy + additionalPassengers <= maxCapacity;
    }

    public void addPassengers(int count) {
        this.currentOccupancy += count;
    }

    public void resetOccupancy() {
        this.currentOccupancy = 0;
    }

    @Override
    public String toString() {
        return "Cab{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
