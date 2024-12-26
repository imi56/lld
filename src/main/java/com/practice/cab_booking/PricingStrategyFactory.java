package com.practice.cab_booking;

public class PricingStrategyFactory {
  public static PricingStrategy getPricingStrategy(CabType cabType) {
    switch (cabType) {
        case MINI:
            return new SurgePricingStrategy(new MiniCabPricingStrategy()); // Wrapping MiniCabPricingStrategy inside SurgePricingStrategy
        case SEDAN:
            return new SurgePricingStrategy(new MiniCabPricingStrategy()); // Wrapping SedanCabPricingStrategy inside SurgePricingStrategy
        case XL:
            return new SurgePricingStrategy(new MiniCabPricingStrategy()); // Wrapping XLCabPricingStrategy inside SurgePricingStrategy
        default:
            throw new IllegalArgumentException("Invalid cab type: " + cabType);
    }
}
}
