package com.practice.designPattern.factory;

public class SedanCabFareStrategy implements FareCalculator {
  @Override
  public double calculate() {
    return 15.0;
  }
}
