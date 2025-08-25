import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private Scanner sc;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(TaskList tasks) {
        try {
            PrintWriter pw = new PrintWriter(this.filePath);
            pw.print(tasks);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(this.filePath);
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
                    tasks.addTask(task);
                } else if (taskType.equals("D")) {

                    String[] split = taskDesc.split(" \\(by: ");
                    String taskBy = split[1].substring(0, split[1].indexOf(")"));
                    Deadline task = new Deadline(split[0], taskBy);
                    if (taskStatus.equals("X")) {
                        task.setDone();
                    }
                    tasks.addTask(task);
                } else if (taskType.equals("E")) {
                    String[] split = taskDesc.split(" \\(from: ");
                    String[] fromTo = split[1].split(" to: ");
                    String from = fromTo[0];
                    String to = fromTo[1].substring(0, fromTo[1].indexOf(")"));
                    Event task = new Event(split[0], from, to);
                    if (taskStatus.equals("X")) {
                        task.setDone();
                    }
                    tasks.addTask(task);
                } else {
                    System.err.println("Unknown task type: " + taskType);
                }
            }
        }
        return tasks;
    }
}
