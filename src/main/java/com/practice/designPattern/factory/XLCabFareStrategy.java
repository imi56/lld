package com.practice.designPattern.factory;

public class XLCabFareStrategy implements FareCalculator {
  @Override
  public double calculate() {
    return 20.0;
  }
}
