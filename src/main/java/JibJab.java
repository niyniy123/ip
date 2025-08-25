import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class JibJab {
    private Scanner sc;


    public JibJab() {
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage("data/jibjab.txt");
        TaskList tl = storage.loadTasks();

        System.out.println("Hello from JibJab");
        System.out.println("What can I do for you?");

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] split = userInput.split(" ", 2);
            String firstWord = split[0];

            try {
                if (firstWord.equals("bye")) {
                    storage.saveTasks(tl);
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                } else if (firstWord.equals("todo")) {
                    try {
                        tl.addTask(new ToDo(split[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new JibJabException("You need to enter a task description");
                    }
                } else if (firstWord.equals("deadline")) {
                    String[] deadlineTask = split[1].split(" /by ");
                    tl.addTask(new Deadline(deadlineTask[0], deadlineTask[1]));
                } else if (firstWord.equals("event")) {
                    String[] eventTask = split[1].split(" /from ");
                    String[] fromTo = eventTask[1].split(" /to ");
                    tl.addTask(new Event(eventTask[0], fromTo[0], fromTo[1]));
                } else if (firstWord.equals("list")) {
                    System.out.println(tl);
                } else if (firstWord.equals("mark")) {
                    int idx = Integer.parseInt(split[1]) - 1;
                    tl.markTaskAsDone(idx);
                } else if (firstWord.equals("unmark")) {
                    int idx = Integer.parseInt(split[1]) - 1;
                    tl.markTaskAsNotDone(idx);
                } else if (firstWord.equals("delete")) {
                    int idx =  Integer.parseInt(split[1]) - 1;
                    tl.deleteTask(idx);
                } else {
                    throw new JibJabException("I don't understand this command");
                }
            } catch (JibJabException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
