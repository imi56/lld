package com.practice.ticTacToe;

import java.util.Scanner;

public class GameEngine {
  
  private Board board;
  private Player player1;
  private Player player2;

  GameEngine(Player player1, Player player2) {
    this.board = new Board();
    this.player1 = player1;
    this.player2 = player2;
  }

  public void start() {
    Player currentPlayer = player1;
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("\nCurrent Board:");
      board.display();
      System.out.println(currentPlayer.getName() + "'s turn " + currentPlayer.getPiece());
      System.out.println("Enter row num");
      int row = scanner.nextInt();
      System.out.println("Enter col num");
      int col = scanner.nextInt();

      if(!board.makeMove(row, col, currentPlayer.getPiece())){
        System.out.println("Invalid move");
        continue;
      }
      
      Piece winningPiece = board.checkWinner();
      
      if(winningPiece != Piece.EMPTY) {
        System.out.println("\nFinal Board:");
        board.display();
        System.out.println("Congratulations " + currentPlayer.getName() + "! You win!");
        break;
      }
      if (board.isFull()) {
        System.out.println("\nFinal Board:");
        board.display();
        System.out.println("It's a draw!");
        break;
      }
      currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
    scanner.close();
  }
}
