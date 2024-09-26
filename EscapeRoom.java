import java.util.Scanner;

public class EscapeRoom {
    public static void main(String[] args) {
        // Welcome message
        System.out.println("Welcome to EscapeRoom!");
        System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
        System.out.println("and pick up all the prizes.\n");

        // Create the game board
        GameGUI gameGUI = new GameGUI();  // Use the GameGUI class from the other file
        gameGUI.createBoard();

        // Size of move and initial player position
        int moveDistance = 60;
        int playerScore = 0;
        int playerLives = 3;  // Initialize player lives
        int currentLevel = 1;  // Initialize current level

        // Scanner for user input
        Scanner userInput = new Scanner(System.in);

        // Set up game loop
        boolean isPlaying = true;

        while (isPlaying) {
            System.out.print("Enter a command: ");
            String userCommand = userInput.nextLine().toLowerCase();

            // Handle movement commands
            int moveResult;
            switch (userCommand) {
                
                case "left":
                case "l":
                    moveResult = gameGUI.movePlayer(-moveDistance, 0); // Move left
                    playerScore += moveResult;
                    if (moveResult < 0) playerLives--;  // Lose a life if hit trap
                    break;
                case "up":
                case "u":
                    moveResult = gameGUI.movePlayer(0, -moveDistance); // Move up
                    playerScore += moveResult;
                    if (moveResult < 0) playerLives--;  // Lose a life if hit trap
                    break;
                case "down":
                case "d":
                    moveResult = gameGUI.movePlayer(0, moveDistance); // Move down
                    playerScore += moveResult;
                    if (moveResult < 0) playerLives--;  // Lose a life if hit trap
                    break;
                case "right":
                case "r":
                    moveResult = gameGUI.movePlayer(moveDistance, 0); // Move right
                    playerScore += moveResult;
                    if (moveResult < 0) playerLives--;  // Lose a life if hit trap
                    break;

                // Handle jump commands
                
                case "jump-left":
                case "jl":
                    moveResult = gameGUI.movePlayer(-2 * moveDistance, 0); // Jump left
                    playerScore += moveResult;
                    if (moveResult < 0) playerLives--;  // Lose a life if hit trap
                    break;
                case "jump-up":
                case "ju":
                    moveResult = gameGUI.movePlayer(0, -2 * moveDistance); // Jump up
                    playerScore += moveResult;
                    if (moveResult < 0) playerLives--;  // Lose a life if hit trap
                    break;
                case "jump-down":
                case "jd":
                    moveResult = gameGUI.movePlayer(0, 2 * moveDistance); // Jump down
                    playerScore += moveResult;
                    if (moveResult < 0) playerLives--;  // Lose a life if hit trap
                    break;
                case "jump-right":
                case "jr":
                    moveResult = gameGUI.movePlayer(2 * moveDistance, 0); // Jump right
                    playerScore += moveResult;
                    if (moveResult < 0) playerLives--;  // Lose a life if hit trap
                    break;

                // Handle prize pickup
                case "pick-up":
                case "p":
                    playerScore += gameGUI.pickupPrize(); // Pick up prize
                    if (gameGUI.allPrizesCollected()) {
                        System.out.println("Congratulations! You've completed level " + currentLevel + "!");
                        currentLevel++;
                        System.out.println("Moving to level " + currentLevel + "...");
                        gameGUI.createBoard();  // Generate a new map
                        gameGUI.resetPlayerPosition();  // Reset player position for the new map
                    }
                    break;

                // End the game
                case "quit":
                case "q":
                    isPlaying = false;
                    break;

                // Replay the game
                case "replay":
                    gameGUI.createBoard(); // Reset the board
                    gameGUI.resetPlayerPosition();
                    currentLevel = 1;
                    playerLives = 3;  // Reset lives
                    playerScore = 0;  // Reset score
                    break;

                // Display help
                case "help":
                case "?":
                    System.out.println("Commands: r (right), l (left), u (up), d (down)");
                    System.out.println("Jump: jr (right), jl (left), ju (up), jd (down)");
                    System.out.println("pick-up (p)");
                    System.out.println("replay, quit");
                    break;

                // Invalid command
                default:
                    System.out.println("Invalid command. Type 'help' for a list of commands.");
                    break;
            }

            // Display score, lives, and current level
            System.out.println("Score: " + playerScore + " | Lives: " + playerLives + " | Level: " + currentLevel);

            // Check if player has run out of lives
            if (playerLives <= 0) {
                System.out.println("Game Over! You've run out of lives.");
                isPlaying = false;
            }
        }

        // Close the scanner
        userInput.close();
        System.out.println("Thanks for playing! You reached level " + currentLevel + " with a final score of " + playerScore + ".");
    }
}
