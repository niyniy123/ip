package jibjab;

/**
 * Represents a simple task without any associated date/time.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description details of the task to be done
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string form prefixed with the ToDo marker [T].
     *
     * @return formatted ToDo string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
