/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
import java.util.Scanner;

/**
 * Create an escape room game where the player must navigate
 * to the other side of the screen in the fewest steps, while
 * avoiding obstacles and collecting prizes.
 */
public class EscapeRoom
{

      // describe the game with brief welcome message
      // determine the size (length and width) a player must move to stay within the grid markings
      // Allow game commands:
      //    right, left, up, down: if you try to go off grid or bump into wall, score decreases
      //    jump over 1 space: you cannot jump over walls
      //    if you land on a trap, spring a trap to increase score: you must first check if there is a trap, if none exists, penalty
      //    pick up prize: score increases, if there is no prize, penalty
      //    help: display all possible commands
      //    end: reach the far right wall, score increase, game ends, if game ended without reaching far right wall, penalty
      //    replay: shows number of player steps and resets the board, you or another player can play the same board
      // Note that you must adjust the score with any method that returns a score
      // Optional: create a custom image for your player use the file player.png on disk
    
      /**** provided code:
      // set up the game
      boolean play = true;
      while (play)
      {
        // get user input and call game methods to play 
        play = false;
      }
      */

      public static void main(String[] args) 
      {      
          // welcome message
          System.out.println("Welcome to EscapeRoom!");
          System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
          System.out.println("pick up all the prizes.\n");
          
          GameGUI game = new GameGUI();
          game.createBoard();
      
          // size of move
          int m = 60; 
          // individual player moves
          int px = 0; // player's x position
          int py = 0; // player's y position
          
          int score = 0;
      
          Scanner in = new Scanner(System.in);
          String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d",
          "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
          "pickup", "p", "quit", "q", "replay", "help", "?"};
        
          // set up game
          boolean play = true;
          while (play)
          {
            // get user input
            System.out.print("Enter a command (right, left, up, down): ");
            String command = UserInput.getValidInput(validCommands);
      
            // handle movement commands
            switch(command) {
              case "right":
              case "r":
                if (px + m < game.getWidth()) { // prevent going off the grid
                  px += m;
                } else {
                  System.out.println("You can't move further right, you'll go off the grid.");
                  score--; // penalize for invalid move
                }
                break;
              case "left":
              case "l":
                if (px - m >= 0) { // prevent going off the grid
                  px -= m;
                } else {
                  System.out.println("You can't move further left, you'll go off the grid.");
                  score--; // penalize for invalid move
                }
                break;
              case "up":
              case "u":
                if (py - m >= 0) { // prevent going off the grid
                  py -= m;
                } else {
                  System.out.println("You can't move further up, you'll go off the grid.");
                  score--; // penalize for invalid move
                }
                break;
              case "down":
              case "d":
                if (py + m < game.getHeight()) { // prevent going off the grid
                  py += m;
                } else {
                  System.out.println("You can't move further down, you'll go off the grid.");
                  score--; // penalize for invalid move
                }
                break;
              case "quit":
              case "q":
                play = false; // end the game
                break;
              default:
                System.out.println("Unknown command.");
            }
            
            // update player's position on the board (if using GUI)
            game.updatePlayerPosition(px, py);
            
            // optional: display current position and score
            System.out.println("Player position: (" + px + ", " + py + ")");
            System.out.println("Current score: " + score);
          }
      
          score += game.endGame();
      
          System.out.println("Final score: " + score);
          System.out.println("Total steps: " + game.getSteps());
      }
      
}

        