package com.practice.designPattern.strategy;

public class MiniCabPricingStrategyImpl implements PricingStrategy {
  @Override
  public double calculatePrice() {
    return 10;
  }
}
