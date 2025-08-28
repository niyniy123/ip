package jibjab;

/**
 * Utility class for parsing user input commands in the JibJab application.
 * Provides static methods to parse different types of commands and extract
 * relevant information from user input strings.
 *
 * @author niyniy123
 */
public class Parser {
    /**
     * Parses a command string by splitting it into command type and arguments.
     * The input is split at the first space, with the first part being the command
     * and the remainder being the arguments.
     *
     * @param input the raw input string containing a command and its arguments
     * @return a String array where index 0 contains the command and index 1 contains
     *         the remaining arguments (if any).
     */
    public static String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Extracts the task description from a parsed command array for a todo task.
     * Expects the description to be at index 1 of the input array.
     *
     * @param input the parsed command array where index 0 is the command and
     *              index 1 should contain the task description
     * @return the task description string
     * @throws JibJabException if no task description is provided (array has insufficient elements)
     */
    public static String parseToDo(String[] input) throws JibJabException {
        try {
            return input[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JibJabException("You need to enter a task description");
        }
    }

    /**
     * Parses a deadline task input string to separate the task description from the deadline.
     * The input format should be: "task description /by deadline"
     *
     * @param input the deadline task string containing description and deadline separated by " /by "
     * @return a String array where index 0 contains the task description and
     *         index 1 contains the deadline
     */
    public static String[] parseDeadline(String input) {
        return input.split(" /by ");
    }

    /**
     * Parses an event task input string to extract the task description, start datetime, and end datetime.
     * The input format should be: "task description /from start_datetime /to end_datetime"
     *
     * @param input the event task string containing description, start datetime, and end datetime
     * @return a String array with three elements:
     *         [0] - task description,
     *         [1] - start datetime,
     *         [2] - end datetime
     * @throws ArrayIndexOutOfBoundsException if the input format is incorrect or missing required parts
     */
    public static String[] parseEvent(String input) {
        String[] eventTask = input.split(" /from ");
        String[] fromTo = eventTask[1].split(" /to ");
        return new String[]{eventTask[0], fromTo[0], fromTo[1]};
    }

    /**
     * Parses a user-provided index (1-based) and converts it to a 0-based array index.
     * Used for tasklist operations
     *
     * @param input the string representation of a 1-based index
     * @return the corresponding 0-based index for tasklist access
     * @throws NumberFormatException if the input string cannot be parsed as an integer
     */
    public static int parseIndex(String input) {
        return Integer.parseInt(input) - 1;
    }
}
