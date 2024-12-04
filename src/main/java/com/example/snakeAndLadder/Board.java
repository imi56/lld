package com.example.snakeAndLadder;

import java.util.HashMap;
import java.util.Map;

public class Board {
  private int size;
  private Map<Integer, Integer> snakes;
  private Map<Integer, Integer> ladders;


  public Board(int size) {
    this.size = size;
    snakes = new HashMap<>();
    ladders = new HashMap<>();
  }

  public void addSnake(int start, int end) {
    if (start < 0 || end > size || start < end) {
      System.out.println("Invalid snake position");
      throw new IllegalArgumentException("Invalid snake position");
    }
    snakes.put(start, end);
  }

  public void addLadder(int start, int end) {
    if (start < 0 || end > size || end < start) {
      System.out.println("Invalid ladder position");
      throw new IllegalArgumentException("Invalid ladder position");
    }
    ladders.put(start, end);
  }

  public int getSize() {
    return size;
  }

  public int getUpdatedPosition(int currPosition) {
    int newPosition = currPosition;
    if (snakes.containsKey(currPosition)) {
      newPosition = snakes.get(currPosition);
      System.out.println("Snake bite from: " + currPosition + "to: " + snakes.get(currPosition));
    } else if (ladders.containsKey(currPosition)) {
      newPosition = ladders.get(currPosition);
      System.out.println("Ladder from: " + currPosition + "to: " + ladders.get(currPosition));

    }
    return newPosition;
  }
}
