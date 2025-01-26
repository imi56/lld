package com.practice.designPattern.factory;

public class MiniCabFareStrategy implements FareCalculator {
  @Override
  public double calculate() {
    return 10.0;
  }
}
