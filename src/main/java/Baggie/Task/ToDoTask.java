package Baggie.Task;

import static Baggie.UI.TEXT.TODO_ICON;

public class ToDoTask extends Task {

    /**
     * Initializes a to-do task with task description.
     *
     * @param taskDescription Task description of the to-do task.
     */
    public ToDoTask(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Returns a task summary.
     *
     * @return a task summary.
     */
    @Override
    public String toString() {
        return getTaskType() + super.toString();
    }

    /**
     * Returns task type.
     *
     * @return to-do task type icon.
     */
    @Override
    public String getTaskType() {
        return TODO_ICON;
    }
}