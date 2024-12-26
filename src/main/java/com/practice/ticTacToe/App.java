package com.practice.ticTacToe;

public class App {
  public static void main(String[] args) {
    Player player1 = new Player("Player 1",  Piece.X);
    Player player2 = new Player("Player 2",  Piece.O);
    GameEngine ge = new GameEngine(player1, player2);
    ge.start();
  }
}