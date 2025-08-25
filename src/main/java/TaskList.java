import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }
    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list");
    }

    public void deleteTask(int idx) {
        Task task =  this.tasks.get(idx);
        this.tasks.remove(task);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list");
    }

    public void markTaskAsDone(int idx) {
        Task task = this.tasks.get(idx);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void markTaskAsNotDone(int idx) {
        Task task = this.tasks.get(idx);
        task.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    public String toString() {
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
