package jibjab;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a specific duration defined by start and end times.
 *
 * @author niyniy123
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event with the given task description and time period.
     *
     * @param description the description of the event task
     * @param from the starting date and time as a string. Accepted formats:
     *             "dd/MM/yyyy HH:mm" or "MMM dd yyyy HH:mm"
     * @param to the ending date and time as a string. Accepted formats:
     *           "dd/MM/yyyy HH:mm" or "MMM dd yyyy HH:mm"
     * @throws java.time.format.DateTimeParseException if the from or to strings
     *         cannot be parsed using the specified formats
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy HH:mm][MMM dd yyyy HH:mm]");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    /**
     * Returns a string representation of the Event task.
     * The format includes the event type marker [E], the task status and description
     * and the formatted from/to times.
     *
     * Example output: "[E][X] Team meeting (from: Dec 25 2024 14:30 to: Dec 25 2024 16:00)"
     *
     * @return a formatted string representation of the event including its type marker,
     *         status, description, and time period in "MMM dd yyyy HH:mm" format
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
