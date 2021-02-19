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

    public String getStatusInWords() { return String.valueOf(this.isDone);}

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

    public String getTaskTypeInWords() {return this.taskType;}

    public String getDescription() {
        return this.description;
    }

    public String getSeperator() {
        if (this.taskType.equalsIgnoreCase("event")) {
            return "/at";
        } else if (this.taskType.equals("deadline")) {
            return "/by";
        } else {
            return null;
        }
    }

    public String getDescriptionWithoutBrackets() {
        if (this.taskType.equalsIgnoreCase("deadline")) {
            String descriptionCleaned = this.description.replaceAll("\\(", "/");
            String descriptionCleaned2 = descriptionCleaned.replaceAll(":", "");
            return descriptionCleaned2.substring(0, descriptionCleaned2.length() - 1);
        } else if (this.taskType.equalsIgnoreCase("event")) {
            String descriptionCleaned = this.description.replaceAll("\\(", "/");
            String descriptionCleaned2 = descriptionCleaned.replaceAll(":", "");
            return descriptionCleaned2.substring(0, descriptionCleaned2.length() - 1);
        } else {
            return this.description;
        }
    }
}
