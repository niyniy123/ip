import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class JibJab {
    private ArrayList<Task> tasks;
    private Scanner sc;


    public JibJab() {
        this.tasks = new ArrayList<>();
        this.loadTasks();
    }

    public void saveTasks() {
        try {
            PrintWriter pw = new PrintWriter("data/jibjab.txt");
            for (Task task : this.tasks) {
                pw.println(task.toString());
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTasks() {
        String path = "data/jibjab.txt";
        File file = new File(path);
        if (file.exists()) {
            try {
                sc = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String taskType = Character.toString(line.charAt(1));
                String taskStatus =  Character.toString(line.charAt(4));
                String taskDesc = line.substring(7);

                if (taskType.equals("T")) {
                    ToDo task = new ToDo(taskDesc);
                    if (taskStatus.equals("X")) {
                        task.setDone();
                    }
                    this.tasks.add(task);
                } else if (taskType.equals("D")) {
                    String[] split = taskDesc.split("by: ");
                    String taskBy = split[1].substring(0, split[1].indexOf(")"));
                    Deadline task = new Deadline(taskDesc, taskBy);
                    if (taskStatus.equals("X")) {
                        task.setDone();
                    }
                    this.tasks.add(task);
                } else if (taskType.equals("E")) {
                    String[] split = taskDesc.split("from: ");
                    String[] fromTo = split[1].split(" to: ");
                    Event task = new Event(taskDesc, fromTo[0], fromTo[1]);
                    if (taskStatus.equals("X")) {
                        task.setDone();
                    }
                    this.tasks.add(task);
                } else {
                    System.err.println("Unknown task type: " + taskType);
                }
            }
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list");
        this.saveTasks();
    }

    public void deleteTask(int idx) {
        Task task =  this.tasks.get(idx);
        this.tasks.remove(task);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list");
        this.saveTasks();
    }

    public void markTaskAsDone(int idx) {
        Task task = this.tasks.get(idx);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        this.saveTasks();
    }

    public void markTaskAsNotDone(int idx) {
        Task task = this.tasks.get(idx);
        task.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
        this.saveTasks();
    }

    public void listTasks() {
        int counter = 1;
        System.out.println("Here are the tasks in your list:");
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

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] split = userInput.split(" ", 2);
            String firstWord = split[0];

            try {
                if (firstWord.equals("bye")) {
                    jj.saveTasks();
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                } else if (firstWord.equals("todo")) {
                    try {
                        jj.addTask(new ToDo(split[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new JibJabException("You need to enter a task description");
                    }
                } else if (firstWord.equals("deadline")) {
                    String[] deadlineTask = split[1].split(" /by ");
                    jj.addTask(new Deadline(deadlineTask[0], deadlineTask[1]));
                } else if (firstWord.equals("event")) {
                    String[] eventTask = split[1].split(" /from ");
                    String[] fromTo = eventTask[1].split(" /to ");
                    jj.addTask(new Event(eventTask[0], fromTo[0], fromTo[1]));
                } else if (firstWord.equals("list")) {
                    jj.listTasks();
                } else if (firstWord.equals("mark")) {
                    int idx = Integer.parseInt(split[1]) - 1;
                    jj.markTaskAsDone(idx);
                } else if (firstWord.equals("unmark")) {
                    int idx = Integer.parseInt(split[1]) - 1;
                    jj.markTaskAsNotDone(idx);
                } else if (firstWord.equals("delete")) {
                    int idx =  Integer.parseInt(split[1]) - 1;
                    jj.deleteTask(idx);
                } else {
                    throw new JibJabException("I don't understand this command");
                }
            } catch (JibJabException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
