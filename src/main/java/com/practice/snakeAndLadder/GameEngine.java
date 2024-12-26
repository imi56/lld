package com.practice.snakeAndLadder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameEngine {
  private Board board;
  private List<Player> players;
  private Queue<Player> playerTurns;
  private Dice dice = Dice.getInstance();

  GameEngine(Board board, List<Player> players) {
    this.board = board;
    this.players = players;
    this.playerTurns = new LinkedList<>(players);
  }

  public void startGame() {
    System.out.println("Starting Snake and Ladder Game!");
    while (true) {
      Player player = playerTurns.poll();
      int diceNumber = dice.roll();
      System.out.println(player.getName() + " is rolled and got number: " + diceNumber);
      int newPosition = player.getPosition() + diceNumber;
      int updatedPosition = board.getUpdatedPosition(newPosition);
      System.out.println(player.getName() + "'s updated position " + updatedPosition);
      if (updatedPosition > board.getSize()) {
        System.out.println("new position is beyond board, continue playing");
      } else if (updatedPosition == board.getSize()) {
        System.out.println(player.getName() + " won");
        break;
      } else {
        player.setPosition(updatedPosition);
      }

      playerTurns.offer(player);
    }
  }

  public static void main(String[] args) {
    Board gameBoard = new Board(100);
        gameBoard.addSnake(16, 6);
        gameBoard.addSnake(47, 26);
        gameBoard.addSnake(49, 11);
        gameBoard.addSnake(56, 53);
        gameBoard.addSnake(62, 19);
        gameBoard.addLadder(2, 38);
        gameBoard.addLadder(15, 44);
        gameBoard.addLadder(22, 58);
        gameBoard.addLadder(36, 75);
        gameBoard.addLadder(78, 97);

        // Initialize players
        List<Player> players = Arrays.asList(new Player("Player 1"), new Player("Player 2"));

        // Start the game
        GameEngine engine = new GameEngine(gameBoard, players);
        engine.startGame();
  }
}
