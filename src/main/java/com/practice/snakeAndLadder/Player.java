package com.practice.snakeAndLadder;

public class Player {
  private String name;
  private int position;

  Player(String name) {
    this.name = name;
    position = 0;
  }

  public String getName() {
    return name;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }

}