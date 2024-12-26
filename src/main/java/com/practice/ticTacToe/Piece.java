package com.practice.ticTacToe;

public class Piece {
  private final Symbol symbol;

  Piece(Symbol symbol) {
    this.symbol = symbol;
  }

  public Symbol getSymbol() {
    return symbol;
  }

  @Override
  public String toString() {
    return symbol == Symbol.EMPTY ? " " : symbol.name();
  }

  public static Piece EMPTY = new Piece(Symbol.EMPTY);
  public static Piece X = new Piece(Symbol.X);
  public static Piece O = new Piece(Symbol.O);
}
