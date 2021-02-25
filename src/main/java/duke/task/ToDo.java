package duke.task;

/**
 * Represents a task type named Todo
 */
public class ToDo extends Task {

    public ToDo(String taskName, String taskType) {
        super(taskName, taskType);
    }

    /**
     * Prints task in a readable format
     */
    @Override
    public void printTask() {
        String output;
        if (isCompleted) {
            output = "[T][X] " + taskName;
        } else {
            output = "[T][ ] " + taskName;
        }
        System.out.println(output);
    }

    /**
     * Formats the task for storage
     * @return The task formatted for storage
     */
    @Override
    public String formatTaskToWrite() {
        String formattedTask;
        formattedTask = String.join(
                "<separator>",
                taskType,
                taskName,
                String.valueOf(isCompleted)
        );
        return formattedTask;
    }
}

