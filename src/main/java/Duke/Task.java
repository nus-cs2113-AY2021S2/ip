package Duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description, String taskType) {
        if (taskType.equalsIgnoreCase("event")) {
            String[] listOfInputs = description.split("/at",2);
            description = listOfInputs[0] + " (at:" + listOfInputs[1] + ")";
            this.isDone = false;
            this.taskType = taskType;
        }
        else if (taskType.equalsIgnoreCase("deadline")) {
            String[] listOfInputs = description.split("/by",2);
            description = listOfInputs[0] + " (by:" + listOfInputs[1] + ")";
            this.isDone = false;
            this.taskType = taskType;
        }
        else {
            String removeDone = taskType.substring(0, taskType.length() - 4);
            if (taskType.equalsIgnoreCase("deadlinedone")) {
                String[] listOfInputs = description.split("/by",2);
                description = listOfInputs[0] + " (by:" + listOfInputs[1] + ")";
                this.isDone = true;
                this.taskType = removeDone;
            }
            else if (taskType.equalsIgnoreCase("eventdone")) {
                String[] listOfInputs = description.split("/by",2);
                description = listOfInputs[0] + " (by:" + listOfInputs[1] + ")";
                this.isDone = true;
                this.taskType = removeDone;
            }
            else if (taskType.equalsIgnoreCase("tododone")) {
                String[] listOfInputs = description.split("/by",2);
                //description = listOfInputs[0] + " (by:" + listOfInputs[1] + ")";
                description = listOfInputs[0];
                this.isDone = true;
                this.taskType = removeDone;
            } else {
                this.isDone = false;
                this.taskType = taskType;
            }
        }
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public Boolean getStatusInWords() { return this.isDone;}

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


    public String getDescriptionWithoutBrackets() {
        if (this.taskType.equalsIgnoreCase("deadline")) {
            String descriptionCleaned = this.description.replaceAll("\\(", "/");
            String descriptionCleaned2 = descriptionCleaned.replaceAll(":", "");
            String descriptionCleaned3 = descriptionCleaned2.replaceAll("  ", "");
            return descriptionCleaned3.substring(0, descriptionCleaned3.length() - 1);
        } else if (this.taskType.equalsIgnoreCase("event")) {
            String descriptionCleaned = this.description.replaceAll("\\(", "/");
            String descriptionCleaned2 = descriptionCleaned.replaceAll(":", "");
            String descriptionCleaned3 = descriptionCleaned2.replaceAll("  ", "");
            return descriptionCleaned3.substring(0, descriptionCleaned3.length() - 1);
        } else {
            return this.description;
        }
    }
}
