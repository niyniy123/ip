import java.util.ArrayList;
import java.util.Scanner;

public class JibJab {
    private ArrayList<Task> tasks;

    public JibJab() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list");
    }

    public void markTaskAsDone(int idx) {
        Task task = this.tasks.get(idx);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:\n\t" + task);
    }

    public void markTaskAsNotDone(int idx) {
        Task task = this.tasks.get(idx);
        task.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n\t" + task);
    }

    public void listTasks() {
        int counter = 1;
        System.out.println("Here are the tasks in your list: ");
        for (Task task : this.tasks) {
            System.out.println(task);
            counter++;
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JibJab jj = new JibJab();

        System.out.println("Hello from JibJab");
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = sc.nextLine();
            String[] split = userInput.split(" ", 2);
            String firstWord = split[0];

            if (firstWord.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (firstWord.equals("todo")) {
                jj.addTask(new ToDo(split[1]));
            } else if (firstWord.equals("deadline")) {
                String[] deadlineTask = split[1].split(" /by ");
                jj.addTask(new Deadline(deadlineTask[0], deadlineTask[1]));
            } else if (firstWord.equals("event")) {
                String[] eventTask = split[1].split(" /from ");
                String[] fromTo =  eventTask[1].split(" /to ");
                jj.addTask(new Event(eventTask[0], fromTo[0], fromTo[1]));
            } else if (firstWord.equals("list")) {
                jj.listTasks();
            } else if (firstWord.equals("mark")) {
                int idx = Integer.parseInt(split[1]) - 1;
                jj.markTaskAsDone(idx);
            } else if (firstWord.equals("unmark")) {
                int idx = Integer.parseInt(split[1]) - 1;
                jj.markTaskAsNotDone(idx);
            }
        }
    }
}
