package com.practice.designPattern.singleton;

import java.util.Random;

public class Dice {

  private static Dice instance;

  private Dice() {}

  public static synchronized Dice getInstance() {
    if(instance == null) {
      System.out.println("New instance created");
      instance = new Dice();
      return instance;
    } else {
      System.out.println("Existing instance returned");
      return instance;
    }
  }
  
  public int roll() {
    return new Random().nextInt(6) + 1;
  }
}
