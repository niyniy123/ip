package jibjab;

public class JibJab {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public JibJab(String filePath) {
       ui = new Ui();
       storage = new Storage(filePath);
       try {
           tasks = new TaskList(storage.loadTasks());
       } catch (JibJabException e) {
           ui.showLoadingError();
           tasks = new TaskList();
       }

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                String[] command = Parser.parseCommand(fullCommand);
                switch (command[0]) {
                case "bye":
                    storage.saveTasks(tasks);
                    ui.showGoodbye();
                    isExit = true;
                    break;
                case "todo":
                    String taskDesc = Parser.parseToDo(command);
                    tasks.addTask(new ToDo(taskDesc));
                    break;
                case "deadline":
                    String[] deadlineDetails = Parser.parseDeadline(command[1]);
                    tasks.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                    break;
                case "event":
                    String[] eventDetails = Parser.parseEvent(command[1]);
                    tasks.addTask(new Event(eventDetails[0], eventDetails[1], eventDetails[2]));
                    break;
                case "list":
                    System.out.println(tasks);
                    break;
                case "find":
                    String keyword = command[1];
                    System.out.println(tasks.findTasks(keyword));
                    break;
                case "mark":
                    tasks.markTaskAsDone(Parser.parseIndex(command[1]));
                    break;
                case "unmark":
                    tasks.markTaskAsNotDone(Parser.parseIndex(command[1]));
                    break;
                case "delete":
                    tasks.deleteTask(Parser.parseIndex(command[1]));
                    break;
                default:
                    throw new JibJabException("I don't understand this command");
                }
            } catch (JibJabException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new JibJab("data/jibjab.txt").run();
    }
}
