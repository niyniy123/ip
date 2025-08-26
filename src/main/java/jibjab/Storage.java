package jibjab;

import java.io.File;
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

    public ArrayList<Task> loadTasks() throws JibJabException{
        ArrayList<Task> tasks = new ArrayList<Task>();
        File file = new File(this.filePath);
        if (file.exists()) {
            try {
                sc = new Scanner(file);
            } catch (IOException e) {
                throw new JibJabException("Failed to load from file!");
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
                    tasks.add(task);
                } else if (taskType.equals("D")) {

                    String[] split = taskDesc.split(" \\(by: ");
                    String taskBy = split[1].substring(0, split[1].indexOf(")"));
                    Deadline task = new Deadline(split[0], taskBy);
                    if (taskStatus.equals("X")) {
                        task.setDone();
                    }
                    tasks.add(task);
                } else if (taskType.equals("E")) {
                    String[] split = taskDesc.split(" \\(from: ");
                    String[] fromTo = split[1].split(" to: ");
                    String from = fromTo[0];
                    String to = fromTo[1].substring(0, fromTo[1].indexOf(")"));
                    Event task = new Event(split[0], from, to);
                    if (taskStatus.equals("X")) {
                        task.setDone();
                    }
                    tasks.add(task);
                } else {
                    System.err.println("Unknown task type: " + taskType);
                }
            }
        }
        return tasks;
    }
}
