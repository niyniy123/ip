public class Task {
    private String taskName;
    private boolean isDone;

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
}