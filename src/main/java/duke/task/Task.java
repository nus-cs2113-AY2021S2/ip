package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTaskType() {
        return null;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public static String getStatusIcon(int index) {
        return (TaskList.taskArray.get(index).isDone ? "X" : " ");
    }

    public String getTaskItem() {
        return this.description;
    }

    public String getFileInput() {
        if (this.getTaskType().equals("T")) {
            return this.getTaskType() + " , " + this.getStatusIcon() + " , " + this.description;
        } else {
            return this.getTaskType() + " , " + this.getStatusIcon() + " , " + this.getTaskItem();
        }
    }
}
