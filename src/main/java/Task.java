public class Task implements java.io.Serializable{
    protected String description;
    protected boolean isDone;
    protected String typeIcon;
    protected String deadline = "";

    /**
     * Assigns typeIcon, which differs based on the description.
     * @param description
     */
    public Task(String description) {
        if(description.equalsIgnoreCase("todo")) {
            typeIcon = "ErrorT";
        } else if(description.equalsIgnoreCase("event")) {
            typeIcon = "ErrorE";
        } else if(description.equalsIgnoreCase("deadline")) {
            typeIcon = "ErrorD";
        } else {
            if (description.contains("todo") || description.contains("deadline") || description.contains("event")) {
                String command = description.substring(0, description.indexOf(' '));
                String content = description.substring(description.indexOf(' ') + 1);

                this.description = content;
                this.isDone = false;
                switch (command) {
                case "todo":
                    typeIcon = "T";
                    break;
                case "deadline":
                    typeIcon = "D";
                    deadline = content.substring(content.indexOf('/') + 4);
                    break;
                case "event":
                    typeIcon = "E";
                    deadline = content.substring(content.indexOf('/') + 4);
                    break;
                default:
                    typeIcon = " ";
                }
            } else {
                this.description = description;
                this.isDone = false;
            }
        }
    }

    /**
     * Returns the status icon of the task.
     * @return
     */
    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "X" : " "); //in the example, X means completed and space means incomplete
    }

    /**
     * Returns typeIcon
     * @return
     */
    public String getTypeIcon() {
        return typeIcon;
    }

    /**
     * Sets isDone to specified boolean value
     * @param isDone
     */
    public void setStatus(boolean isDone){
        this.isDone = isDone;
    }

    /**
     * Sets description to specified string value
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets typeIcon to specified string value
     * @param typeIcon
     */
    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }

    /**
     * Sets deadline to specified string value
     * @param deadline
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Returns a description based on the typeIcon assigned in the constructor.
     * @return
     */
    public String getDescription() {
        String content;
        switch (typeIcon) {
        case "ErrorT":
            return "☹ OOPS!!! The description of a todo cannot be empty.";
        case "ErrorD":
            return "☹ OOPS!!! The description of a deadline cannot be empty.";
        case "ErrorE":
            return "☹ OOPS!!! The description of an event cannot be empty.";
        case "D":
            return description.substring(0,description.indexOf('/')) + " (by: " + deadline + ")";
        case "E":
            return description.substring(0,description.indexOf('/')) + " (at: " + deadline + ")";
        case "T":
        default:
            return description;
        }
    }
}
