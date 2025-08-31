package jibjab;

import java.util.Scanner;

/**
 * Handles all console input and output interactions for the JibJab application.
 * Provides helper methods to read user commands and print common UI messages.
 */
public class Ui {
    private Scanner input;

    /**
     * Creates a Ui instance backed by System.in for reading user input.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Prints the welcome banner shown when the application starts.
     */
    public void showWelcome() {
        System.out.println("Hello from JibJab");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the goodbye message shown when the application exits.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Notifies the user that loading tasks from storage failed and a new list will be created.
     */
    public void showLoadingError() {
        System.out.println("Failed to load tasks from file...creating new task list");
    }

    /**
     * Reads the next line command from the input stream, or returns an empty string if none is available.
     *
     * @return the next command line entered by the user, or an empty string when input is exhausted
     */
    public String readCommand() {
        if (input.hasNextLine()) {
            return input.nextLine();
        }
        return "";
    }

    /**
     * Prints an error message to the error stream.
     *
     * @param message the error details to display
     */
    public void showError(String message) {
        System.err.println(message);
    }

    /**
     * Prints a horizontal divider line used to separate sections of output.
     */
    public void showLine() {
        System.out.println("--------------------------------------");
    }
}
