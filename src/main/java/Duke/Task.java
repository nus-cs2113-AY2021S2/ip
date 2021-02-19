package Duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description, String taskType) {
        if (taskType.equalsIgnoreCase("event")) {
            String[] listOfInputs = description.split("/at",2);
            description = listOfInputs[0] + " (at:" + listOfInputs[1] + ")";
        }
        if (taskType.equalsIgnoreCase("deadline")) {
            String[] listOfInputs = description.split("/by",2);
            description = listOfInputs[0] + " (by:" + listOfInputs[1] + ")";
        }
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskType() {
        if (this.taskType.equalsIgnoreCase("todo")) {
            return "[T]";
        } else if (this.taskType.equalsIgnoreCase("deadline")) {
            return "[D]";
        } else {
            return "[E]";
        }
    }

    public String getDescription() {
        return this.description;
    }
}
