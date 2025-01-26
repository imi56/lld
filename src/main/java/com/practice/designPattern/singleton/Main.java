package com.practice.designPattern.singleton;

public class Main {
  public static void main(String[] args) {
    Dice dice = Dice.getInstance();
    System.out.println(dice.roll());

    Dice dice2 = Dice.getInstance();
    System.out.println(dice2.roll());
  }
}
