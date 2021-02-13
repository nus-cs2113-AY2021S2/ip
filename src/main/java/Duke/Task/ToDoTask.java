package Duke.Task;

public class ToDoTask extends Task {

    public ToDoTask(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getTaskType() {
        return "T";
    }
}