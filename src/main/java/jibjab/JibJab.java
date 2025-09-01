package jibjab;

/**
 * Entry point and main controller for the JibJab application.
 * Wires together UI, storage and task list, and runs the command loop.
 */
public class JibJab {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a JibJab instance using the given file path for persistence.
     * Attempts to load existing tasks; if loading fails, starts with an empty list.
     *
     * @param filePath path to the data file used for saving/loading tasks
     */
    public JibJab(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (JibJabException e) {
            System.out.println(ui.showLoadingError());
            tasks = new TaskList();
        }

    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            StringBuilder out = new StringBuilder();
            String[] command = Parser.parseCommand(input);
            switch (command[0]) {
            case "bye":
                storage.saveTasks(tasks);
                out.append(ui.showGoodbye());
                javafx.application.Platform.exit();
                break;
            case "todo":
                String taskDesc = Parser.parseToDo(command);
                ToDo todo = new ToDo(taskDesc);
                tasks.addTask(todo);
                out.append(ui.showTaskAdded(todo, tasks.size()));
                break;
            case "deadline":
                String[] deadlineDetails = Parser.parseDeadline(command[1]);
                Deadline deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                tasks.addTask(deadline);
                out.append(ui.showTaskAdded(deadline, tasks.size()));
                break;
            case "event":
                String[] eventDetails = Parser.parseEvent(command[1]);
                Event event = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                tasks.addTask(event);
                out.append(ui.showTaskAdded(event, tasks.size()));
                break;
            case "list":
                out.append(tasks.toString());
                break;
            case "find":
                String keyword = command[1];
                out.append("Here are the matching tasks in your list:\n");
                out.append(tasks.findTasks(keyword));
                break;
            case "mark":
                Task marked = tasks.markTaskAsDone(Parser.parseIndex(command[1]));
                out.append(ui.showTaskMarked(marked));
                break;
            case "unmark":
                Task unmarked = tasks.markTaskAsNotDone(Parser.parseIndex(command[1]));
                out.append(ui.showTaskUnmarked(unmarked));
                break;
            case "delete":
                Task removed = tasks.deleteTask(Parser.parseIndex(command[1]));
                out.append(ui.showTaskDeleted(removed, tasks.size()));
                break;
            default:
                throw new JibJabException("I don't understand this command");
            }
            return out.toString();
        } catch (JibJabException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Launches the JibJab application.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        new JibJab("data/jibjab.txt").run();
    }

    /**
     * Runs the main interactive command loop until the user exits.
     * Reads commands, calls parser, and performs task operations and persistence.
     */
    public void run() {
        System.out.println(ui.showWelcome());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(ui.showLine());
                String[] command = Parser.parseCommand(fullCommand);
                switch (command[0]) {
                case "bye":
                    storage.saveTasks(tasks);
                    System.out.println(ui.showGoodbye());
                    isExit = true;
                    break;
                case "todo":
                    String taskDesc = Parser.parseToDo(command);
                    ToDo todo2 = new ToDo(taskDesc);
                    tasks.addTask(todo2);
                    System.out.println(ui.showTaskAdded(todo2, tasks.size()));
                    break;
                case "deadline":
                    String[] deadlineDetails = Parser.parseDeadline(command[1]);
                    Deadline deadline2 = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                    tasks.addTask(deadline2);
                    System.out.println(ui.showTaskAdded(deadline2, tasks.size()));
                    break;
                case "event":
                    String[] eventDetails = Parser.parseEvent(command[1]);
                    Event event2 = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                    tasks.addTask(event2);
                    System.out.println(ui.showTaskAdded(event2, tasks.size()));
                    break;
                case "list":
                    System.out.println(tasks);
                    break;
                case "find":
                    String keyword = command[1];
                    System.out.println(tasks.findTasks(keyword));
                    break;
                case "mark":
                    Task marked2 = tasks.markTaskAsDone(Parser.parseIndex(command[1]));
                    System.out.println(ui.showTaskMarked(marked2));
                    break;
                case "unmark":
                    Task unmarked2 = tasks.markTaskAsNotDone(Parser.parseIndex(command[1]));
                    System.out.println(ui.showTaskUnmarked(unmarked2));
                    break;
                case "delete":
                    Task removed2 = tasks.deleteTask(Parser.parseIndex(command[1]));
                    System.out.println(ui.showTaskDeleted(removed2, tasks.size()));
                    break;
                default:
                    throw new JibJabException("I don't understand this command");
                }
            } catch (JibJabException e) {
                System.err.println(ui.showError(e.getMessage()));
            } finally {
                System.out.println(ui.showLine());
            }
        }
    }
}
