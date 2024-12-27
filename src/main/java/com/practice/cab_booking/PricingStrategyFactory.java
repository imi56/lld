package com.practice.cab_booking;

public class PricingStrategyFactory {
  public static PricingStrategy getPricingStrategy(CabType cabType, DemandHour demandHour) {
    PricingStrategy baseStrategy;

    switch (cabType) {
        case MINI:
            baseStrategy = new MiniCabPricingStrategy();
            break;
        case SEDAN:
            baseStrategy = new MiniCabPricingStrategy();
            break;
        case XL:
            baseStrategy = new MiniCabPricingStrategy();
            break;
        default:
            throw new IllegalArgumentException("Invalid cab type: " + cabType);
    }

    switch (demandHour) {
        case PEAK:
            return new SurgePricingStrategy(baseStrategy);
        case NIGHT:
            return new SurgePricingStrategy(baseStrategy);
        default:
            return baseStrategy;
    }
  }
}
