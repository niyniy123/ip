package jibjab;

public class Parser {
    public static String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    public static String parseToDo(String[] input) throws JibJabException {
        try {
            return input[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JibJabException("You need to enter a task description");
        }
    }

    public static String[] parseDeadline(String input) {
        return input.split(" /by ");
    }

    public static String[] parseEvent(String input) {
        String[] eventTask = input.split(" /from ");
        String[] fromTo = eventTask[1].split(" /to ");
        return new String[] {eventTask[0], fromTo[0], fromTo[1]};
    }

    public static int parseIndex(String input) {
        return Integer.parseInt(input) - 1;
    }
}
