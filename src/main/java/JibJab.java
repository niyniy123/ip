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
            String[] split = userInput.split(" ");
            String firstWord = split[0];

            if (firstWord.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (firstWord.equals("list")) {
                int counter = 1;

                for (Task task: userTasks) {
                    System.out.println(counter + ". " + task.toString());
                    counter++;
                }
            } else if (firstWord.equals("mark")) {
                int idx = Integer.parseInt(split[1]) - 1;
                Task toMark = userTasks.get(idx);
                toMark.setDone();
                System.out.println("Nice! I've marked this task as done:\n\t" + toMark.toString());
            } else if (firstWord.equals("unmark")) {
                int idx = Integer.parseInt(split[1]) - 1;
                Task toUnmark = userTasks.get(idx);
                toUnmark.setNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n\t" + toUnmark.toString());
            } else {
                userTasks.add(new Task(userInput));
                System.out.println("Added: " + userInput);
            }
        }
    }
}
