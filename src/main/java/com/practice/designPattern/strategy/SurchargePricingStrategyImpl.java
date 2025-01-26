package com.practice.designPattern.strategy;

public class SurchargePricingStrategyImpl implements PricingStrategy {

  double SURCHAGE_FACTOR = 1.5;
  private PricingStrategy cabPricingStrategy;
  SurchargePricingStrategyImpl(PricingStrategy cabPricingStrategy) {
    this.cabPricingStrategy = cabPricingStrategy;
  }

  @Override
  public double calculatePrice() {
    double cabPrice = cabPricingStrategy.calculatePrice();
    return cabPrice * SURCHAGE_FACTOR;
  }
}
