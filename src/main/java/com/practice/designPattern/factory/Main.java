package com.practice.designPattern.factory;

public class Main {
  public static void main(String[] args) {
    CabType cabType = CabType.MINI;
    try {
      double fare = calculator(cabType).calculate();
      System.out.println("Fare for " + cabType.name() + "is: " + fare);

      cabType = CabType.SEDAN;
      fare = calculator(cabType).calculate();
      System.out.println("Fare for " + cabType.name() + "is: " + fare);

    } catch(Exception e) {
      System.out.println("e");
    }

  }

  private static FareCalculator calculator(CabType cabType) {  
    switch (cabType) {
      case MINI:
        return new MiniCabFareStrategy();
      
      case SEDAN:
      return new SedanCabFareStrategy();
    
      default:
      throw new IllegalArgumentException("Invalid cab type: " + cabType);
    }
  }

}





