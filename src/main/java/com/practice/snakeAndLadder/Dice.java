package com.practice.snakeAndLadder;

import java.util.Random;

public class Dice {
  
  private Random random;
  private static Dice instance;

  private Dice() {
    random = new Random();
  }

  public static synchronized Dice getInstance() {
    if (instance == null) {
      instance = new Dice();
    }
    return instance;
  }

  public int roll() {
    return random.nextInt(6) + 1;
  }
}
