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
                if (tasks.contains(todo)) {
                    throw new JibJabException("This task already exists in your list!");
                }
                tasks.addTask(todo);
                out.append(ui.showTaskAdded(todo, tasks.getSize()));
                break;
            case "deadline":
                String[] deadlineDetails = Parser.parseDeadline(command[1]);
                Deadline deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                if (tasks.contains(deadline)) {
                    throw new JibJabException("This task already exists in your list!");
                }
                tasks.addTask(deadline);
                out.append(ui.showTaskAdded(deadline, tasks.getSize()));
                break;
            case "event":
                String[] eventDetails = Parser.parseEvent(command[1]);
                Event event = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                if (tasks.contains(event)) {
                    throw new JibJabException("This task already exists in your list!");
                }
                tasks.addTask(event);
                out.append(ui.showTaskAdded(event, tasks.getSize()));
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
                Task marked = tasks.markTaskAsDone(Parser.parseIndex(input, tasks.getSize()));
                out.append(ui.showTaskMarked(marked));
                break;
            case "unmark":
                Task unmarked = tasks.markTaskAsNotDone(Parser.parseIndex(input, tasks.getSize()));
                out.append(ui.showTaskUnmarked(unmarked));
                break;
            case "delete":
                Task removed = tasks.deleteTask(Parser.parseIndex(input, tasks.getSize()));
                out.append(ui.showTaskDeleted(removed, tasks.getSize()));
                break;
            default:
                throw new JibJabException("I don't understand this command");
            }
            return out.toString();
        } catch (JibJabException e) {
            return e.getMessage();
        }
    }
}
