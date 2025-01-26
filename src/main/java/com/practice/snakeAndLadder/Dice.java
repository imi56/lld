package com.practice.snakeAndLadder;

import java.util.Random;

public class Dice {
  
  private Random random;
  private static Dice instance;

  private Dice() {
    random = new Random();
  }

  /* You used synchronized the getInstance() method itslef like public static synchronized Dice getInstance(), 
  which is good for making it thread-safe. However, 
  this implementation can lead to performance issues due to unnecessary synchronization once the instance is created.
  A better approach is to use double-checked locking or an eager initialization approach. */
  
  public static Dice getInstance() {
    if (instance == null) {
      synchronized (Dice.class) {
        if(instance == null) {
          instance = new Dice();
        }
      }
    }
    return instance;
  }

  public int roll() {
    return random.nextInt(6) + 1;
  }
}
