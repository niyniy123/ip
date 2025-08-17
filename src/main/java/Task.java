public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}