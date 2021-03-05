package Baggie.Task;

import static Baggie.UI.TEXT.TODO_ICON;

public class ToDoTask extends Task {

    public ToDoTask(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return getTaskType() + super.toString();
    }

    @Override
    public String getTaskType() {
        return TODO_ICON;
    }
}