package com.example.ticTacToe;

public class Board {
  private static int SIZE = 3;
  private Piece[][] grid = new Piece[SIZE][SIZE];

  Board() {
    for(int i = 0; i < SIZE; i++) {
      for(int j = 0; j < SIZE; j++) {
        grid[i][j] = Piece.EMPTY;
      }
    }
  }

  public boolean makeMove(int i, int j, Piece piece) {
    if (isValidMove(i, j)) {
      grid[i][j] = piece;
      return true;
    } 
    return false;
  }

  public boolean isValidMove(int i, int j) {
    return i >=0 && i < SIZE && j >= 0 && j < SIZE && grid[i][j] == Piece.EMPTY;
  }

  public Piece checkWinner() {
    // check rows and columns
    for(int i = 0; i < SIZE; i++) {
      if(grid[i][0] != Piece.EMPTY && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
        return grid[i][0];
      } 
      if(grid[0][i] != Piece.EMPTY && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
        return grid[0][i];
      }

      if(grid[0][0] != Piece.EMPTY && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
        return grid[0][0];
      }

      if(grid[0][2] != Piece.EMPTY && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
        return grid[0][2];
      }
    }
    return Piece.EMPTY;
  }

  public boolean isFull() {
    for(Piece[] row : grid) {
      for(Piece cell : row) {
        if(cell == Piece.EMPTY) {
          return false;
        }
      }
    }
    return true;
  }

  public void display() {
    for (Piece[] row : grid) {
      for (Piece cell : row) {
          System.out.print(cell + " ");
      }
      System.out.println();
  }
  }
}

