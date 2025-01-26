package com.practice.designPattern.strategy;

public class SedanCabPricingStrategyImpl implements PricingStrategy {
  @Override
  public double calculatePrice() {
    return 15;
  }
}
