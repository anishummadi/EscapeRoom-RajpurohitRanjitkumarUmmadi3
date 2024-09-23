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

        // Scanner for user input
        Scanner userInput = new Scanner(System.in);

        // Set up game loop
        boolean isPlaying = true;

        while (isPlaying) {
            System.out.print("Enter a command: ");
            String userCommand = userInput.nextLine().toLowerCase();

            // Handle movement commands
            switch (userCommand) {
                
                case "left":
                case "l":
                    playerScore += gameGUI.movePlayer(-moveDistance, 0); // Move left
                    break;
                case "up":
                case "u":
                    playerScore += gameGUI.movePlayer(0, -moveDistance); // Move up
                    break;
                case "down":
                case "d":
                    playerScore += gameGUI.movePlayer(0, moveDistance); // Move down
                    break;
                case "right":
                case "r":
                    playerScore += gameGUI.movePlayer(moveDistance, 0); // Move right
                    break;

                // Handle jump commands
                
                case "jump-left":
                case "jl":
                    playerScore += gameGUI.movePlayer(-2 * moveDistance, 0); // Jump left
                    break;
                case "jump-up":
                case "ju":
                    playerScore += gameGUI.movePlayer(0, -2 * moveDistance); // Jump up
                    break;
                case "jump-down":
                case "jd":
                    playerScore += gameGUI.movePlayer(0, 2 * moveDistance); // Jump down
                    break;
                case "jump-right":
                case "jr":
                    playerScore += gameGUI.movePlayer(2 * moveDistance, 0); // Jump right
                    break;

                // Handle prize pickup
                case "pick-up":
                case "p":
                    playerScore += gameGUI.pickupPrize(); // Pick up prize
                    if (gameGUI.allPrizesCollected()) {
                        System.out.println("You Won the Game!");
                        isPlaying = false; // End the game
                    }
                    break;

                // Handle trap springing
                case "spring-trap":
                    System.out.println("Specify direction (r, l, u, d): ");
                    String trapDirection = userInput.nextLine().toLowerCase();
                    if (trapDirection.equals("r")) {
                        playerScore += gameGUI.springTrap(moveDistance, 0); // Spring trap on right
                    } else if (trapDirection.equals("l")) {
                        playerScore += gameGUI.springTrap(-moveDistance, 0); // Spring trap on left
                    } else if (trapDirection.equals("u")) {
                        playerScore += gameGUI.springTrap(0, -moveDistance); // Spring trap above
                    } else if (trapDirection.equals("d")) {
                        playerScore += gameGUI.springTrap(0, moveDistance); // Spring trap below
                    }
                    break;

                // End the game
                case "quit":
                case "q":
                    isPlaying = false;
                    break;

                // Replay the game
                case "replay":
                    playerScore += gameGUI.replay(); // Reset the board
                    break;

                // Display help
                case "help":
                case "?":
                    System.out.println("Commands: r (right), l (left), u (up), d (down)");
                    System.out.println("Jump: jr (right), jl (left), ju (up), jd (down)");
                    System.out.println("pick-up (p), spring-trap");
                    System.out.println("replay, quit");
                    break;

                // Invalid command
                default:
                    System.out.println("Invalid command. Type 'help' for a list of commands.");
                    break;
            }

            // Display score
            System.out.println("Score: " + playerScore);
        }

        // Close the scanner
        userInput.close();
        System.out.println("Thanks for playing!");
    }
}
