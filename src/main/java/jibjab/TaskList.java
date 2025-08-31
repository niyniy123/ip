package jibjab;

import java.util.ArrayList;

/**
 * Manages a collection of tasks in the JibJab application.
 * This class provides functionality to store, add, delete, and modify tasks,
 * as well as track their completion status.
 *
 * @author niyniy123
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     * Initializes the internal task list with no tasks.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with an existing collection of tasks.
     * This constructor can be used to initialize the TaskList with
     * pre-existing tasks, such as when loading from storage.
     *
     * @param tasks an ArrayList of Task objects to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if there are no tasks in the list, false otherwise
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Adds a new task to the end of the task list.
     *
     * @param task the Task object to be added to the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list");
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param idx the zero-based index of the task to be deleted
     */
    public void deleteTask(int idx) {
        Task task = this.tasks.get(idx);
        this.tasks.remove(task);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list");
    }

    /**
     * Marks a task at the specified index as completed.
     *
     * @param idx the zero-based index of the task to mark as done
     */
    public void markTaskAsDone(int idx) {
        Task task = this.tasks.get(idx);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Marks a task at the specified index as not completed.
     * This can be used to revert a previously completed task back to undone status.
     *
     * @param idx the zero-based index of the task to mark as not done
     */
    public void markTaskAsNotDone(int idx) {
        Task task = this.tasks.get(idx);
        task.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Finds tasks whose string representation contains the given keyword.
     * Prints a header and returns the matching tasks as a newline-separated list.
     *
     * @param keyword substring to search for within each task's toString()
     * @return newline-separated list of matching tasks; empty string if none match
     */
    public String findTasks(String keyword) {
        int counter = 1;
        StringBuilder sb = new StringBuilder();
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword)) {
                sb.append(counter).append(".").append(task).append("\n");
                counter++;
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * Returns a string representation of all tasks in the list.
     * If the list is empty, returns a message indicating no tasks.
     * Otherwise, returns all tasks formatted with one task per line.
     *
     * @return a string containing all tasks separated by newlines,
     *         or a message indicating an empty list
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "You have no tasks in the list";
        } else {
            int counter = 1;
            StringBuilder sb = new StringBuilder();
            //System.out.println("Here are the tasks in your list:");
            for (Task task : this.tasks) {
                sb.append(task).append("\n");
                counter++;
            }
            sb.setLength(sb.length() - 1);
            return sb.toString();
        }

    }

}
