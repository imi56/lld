package com.practice.designPattern.strategy;

public class PricingStrategyFactory {
  private static PricingStrategy cabPricingStrategy;

  public static PricingStrategy getStrategy(CabType cabType, DemandType demandType) {

    switch(cabType) {
      case MINI:
        cabPricingStrategy = new MiniCabPricingStrategyImpl();
        break;
      case SEDAN:
        cabPricingStrategy = new SedanCabPricingStrategyImpl();
        break;
      default:
        throw new IllegalArgumentException("Invalid cab type");
    }

    switch(demandType) {
      case NIGHT:
        return new NightPricingStrategyImpl(cabPricingStrategy);
      case SURCHAGE:
        return new SurchargePricingStrategyImpl(cabPricingStrategy);
      default:
        return new SedanCabPricingStrategyImpl();
    }
  }

}
