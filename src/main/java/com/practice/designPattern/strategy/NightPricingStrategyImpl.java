package com.practice.designPattern.strategy;

public class NightPricingStrategyImpl implements PricingStrategy {
  private PricingStrategy cabPricing;
  int nightCharge = 50;

  NightPricingStrategyImpl(PricingStrategy cabPricing) {
    this.cabPricing = cabPricing;
  }

  @Override
  public double calculatePrice() {
    double cabPrice = cabPricing.calculatePrice();
    return nightCharge + cabPrice;
  }
}
