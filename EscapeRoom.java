import java.util.Scanner;

public class EscapeRoom {
    public static void main(String[] args) {
        // Welcome message
        System.out.println("Welcome to EscapeRoom!");
        System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
        System.out.println("pick up all the prizes.\n");

        // Create the game board
        GameGUI game = new GameGUI();  // Use the GameGUI class from the other file
        game.createBoard();

        // Size of move and initial player position
        int m = 60;
        int score = 0;

        // Scanner for user input
        Scanner in = new Scanner(System.in);

        // Set up game loop
        boolean play = true;
        while (play) {
            System.out.print("Enter a command: ");
            String command = in.nextLine().toLowerCase();

            // Handle movement commands
            switch (command) {
                case "right":
                case "r":
                    score += game.movePlayer(m, 0); // Move right
                    break;
                case "left":
                case "l":
                    score += game.movePlayer(-m, 0); // Move left
                    break;
                case "up":
                case "u":
                    score += game.movePlayer(0, -m); // Move up
                    break;
                case "down":
                case "d":
                    score += game.movePlayer(0, m); // Move down
                    break;

                // Handle jump commands
                case "jr":
                case "jumpright":
                    score += game.movePlayer(2 * m, 0); // Jump right
                    break;
                case "jl":
                case "jumpleft":
                    score += game.movePlayer(-2 * m, 0); // Jump left
                    break;
                case "ju":
                case "jumpup":
                    score += game.movePlayer(0, -2 * m); // Jump up
                    break;
                case "jd":
                case "jumpdown":
                    score += game.movePlayer(0, 2 * m); // Jump down
                    break;

                // Handle prize pickup
                case "pickup":
                case "p":
                    score += game.pickupPrize(); // Pick up prize
                    break;

                // Handle trap springing
                case "springtrap":
                    System.out.println("Specify direction (r, l, u, d): ");
                    String trapDir = in.nextLine().toLowerCase();
                    if (trapDir.equals("r")) {
                        score += game.springTrap(m, 0); // Spring trap on right
                    } else if (trapDir.equals("l")) {
                        score += game.springTrap(-m, 0); // Spring trap on left
                    } else if (trapDir.equals("u")) {
                        score += game.springTrap(0, -m); // Spring trap above
                    } else if (trapDir.equals("d")) {
                        score += game.springTrap(0, m); // Spring trap below
                    }
                    break;

                // End the game
                case "quit":
                case "q":
                    play = false;
                    break;

                // Replay the game
                case "replay":
                    score += game.replay(); // Reset the board
                    break;

                // Display help
                case "help":
                case "?":
                    System.out.println("Commands: r (right), l (left), u (up), d (down)");
                    System.out.println("Jump: jr (right), jl (left), ju (up), jd (down)");
                    System.out.println("pickup (p), springtrap");
                    System.out.println("replay, quit");
                    break;

                // Invalid command
                default:
                    System.out.println("Invalid command. Type 'help' for a list of commands.");
                    break;
            }

            // Display score
            System.out.println("Score: " + score);
        }

        // Close the scanner
        in.close();
        System.out.println("Thanks for playing!");
    }
}
