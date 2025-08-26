package jibjab;

import java.util.Scanner;

public class Ui {
    private Scanner input;
    public Ui() {
        this.input = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello from JibJab");
        System.out.println("What can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("Failed to load tasks from file...creating new task list");
    }

    public String readCommand() {
        if  (input.hasNextLine()) {
            return input.nextLine();
        }
        return "";
    }

    public void showError(String message) {
        System.err.println(message);
    }

    public void showLine() {
        System.out.println("--------------------------------------");
    }
}
