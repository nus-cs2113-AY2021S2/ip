public class Task {
    protected String description;
    protected boolean isDone;
    protected String typeIcon;
    protected String deadline = "";

    public Task(String description) {
        if(description.contains("todo")||description.contains("deadline")||description.contains("event")) {
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
                deadline = content.substring(content.indexOf('/')+4);
                break;
            case "event":
                typeIcon = "E";
                deadline = content.substring(content.indexOf('/')+4);
                break;
            default:
                typeIcon = " ";
            }
        }else{
            this.description = description;
            this.isDone = false;
        }
    }

    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        return (isDone ? "X" : " "); //in the example, X means completed and space means incomplete
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setStatus(boolean isDone){
        this.isDone = isDone;
    }

    public String getDescription() {
        String content;
        switch (typeIcon) {
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
