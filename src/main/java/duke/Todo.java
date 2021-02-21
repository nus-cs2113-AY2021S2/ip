package duke;

public class Todo extends Task{
    public Todo(String description, char taskType) {
        super(description);
        this.taskType = taskType;
    }

    /**
     * formulate the complete Todo task description in task list
     * @return the task type, status and description in the task list
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}