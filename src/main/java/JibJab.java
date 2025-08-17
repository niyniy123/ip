import java.util.Scanner;

public class JibJab {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello from JibJab");
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else {
                userInputs.add(userInput);
                System.out.println("Added: " + userInput);
            }
        }

    }
}
