package com.practice.designPattern.strategy;

public class Main {
  public static void main(String[] args) {
    PricingStrategy pricingStrategy = PricingStrategyFactory.getStrategy(CabType.MINI, DemandType.SURCHAGE);
    System.out.println("Price is: " + pricingStrategy.calculatePrice());
  }
}
