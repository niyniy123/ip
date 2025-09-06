package jibjab;

/**
 * Represents a task in the JibJab application.
 * This is the base class for all task types (ToDo, Deadline, Event) and provides
 * common functionality for task description and task completion.
 *
 * @author niyniy123
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done (incomplete).
     *
     * @param description the description of the task to be created.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as completed by setting its done status to true.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed by setting its done status to false.
     * This method can be used to revert a task back to incomplete status.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon representing the task's completion state.
     *
     * @return "X" if the task is completed, a space " " if the task is not completed.
     *         This icon is typically used in text-based displays of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task.
     * The format includes the task's status icon in square brackets followed by
     * the task description.
     *
     * @return a formatted string containing the task's status icon and description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
