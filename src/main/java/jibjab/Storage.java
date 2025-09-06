package jibjab;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles persistence of tasks to and from a file on disk.
 * The file format matches the output of TaskList#toString() and is parsed back into Task objects.
 */
public class Storage {
    private Scanner sc;
    private String filePath;

    /**
     * Creates a Storage bound to the given file path.
     *
     * @param filePath absolute or relative path to the tasks data file
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isBlank() : "Storage filePath must not be null/blank";
        this.filePath = filePath;
    }

    /**
     * Saves all tasks to the configured file. If the list is empty, nothing is written.
     *
     * @param tasks the TaskList to persist
     * @throws JibJabException if writing fails, typically due to missing folders or IO errors
     */
    public void saveTasks(TaskList tasks) throws JibJabException {
        assert tasks != null : "TaskList to save must not be null";
        if (tasks.isEmpty()) {
            return;
        }
        try {
            PrintWriter pw = new PrintWriter(this.filePath);
            pw.print(tasks);
            pw.close();
        } catch (IOException e) {
            throw new JibJabException(e.getMessage() + "\nEnsure the data folder is present");
        }
    }

    /**
     * Loads tasks from the configured file, if it exists.
     * Parses each line to reconstruct Task instances of the appropriate type.
     *
     * @return a list of tasks loaded from storage; empty if no file exists
     * @throws JibJabException if the file exists but cannot be read
     */
    public ArrayList<Task> loadTasks() throws JibJabException {
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
                assert line != null && line.length() >= 7 : "Stored line must be at least 7 chars";
                String taskType = Character.toString(line.charAt(1));
                String taskStatus = Character.toString(line.charAt(4));
                String taskDesc = line.substring(7);

                if (taskType.equals("T")) {
                    ToDo task = new ToDo(taskDesc);
                    if (taskStatus.equals("X")) {
                        task.setDone();
                    }
                    tasks.add(task);
                } else if (taskType.equals("D")) {

                    String[] split = taskDesc.split(" \\(by: ");
                    assert split.length == 2 : "Stored deadline must contain ' (by: '";
                    String taskBy = split[1].substring(0, split[1].indexOf(")"));
                    Deadline task = new Deadline(split[0], taskBy);
                    if (taskStatus.equals("X")) {
                        task.setDone();
                    }
                    tasks.add(task);
                } else if (taskType.equals("E")) {
                    String[] split = taskDesc.split(" \\(from: ");
                    assert split.length == 2 : "Stored event must contain ' (from: '";
                    String[] fromTo = split[1].split(" to: ");
                    assert fromTo.length == 2 : "Stored event must contain ' to: '";
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
