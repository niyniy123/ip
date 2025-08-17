import java.util.ArrayList;
import java.util.Scanner;

public class JibJab {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> userTasks= new ArrayList<>();

        System.out.println("Hello from JibJab");
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (userInput.equals("list")) {
                int counter = 1;

                for (Task task: userTasks) {
                    System.out.println(counter + ".[" + task.getStatusIcon() + "] " + task.getTaskName());
                    counter++;
                }
            } else {
                userTasks.add(new Task(userInput));
                System.out.println("Added: " + userInput);
            }
        }

    }
}
