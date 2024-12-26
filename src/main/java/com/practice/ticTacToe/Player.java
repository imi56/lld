package com.practice.ticTacToe;

public class Player {
  private String name;
  private Piece piece;

  Player(String name, Piece piece) {
    this.name = name;
    this.piece = piece;
  }

  public String getName() {
    return name;
  }

  public Piece getPiece() {
    return piece;
  }
}
