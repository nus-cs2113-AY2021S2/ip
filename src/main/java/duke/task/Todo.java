package duke.task;

public class Todo extends Task {
    public Todo(String description){
        super(description);
    }

    @Override
    public String getType(){
        return "T";
    }

    public String getDate(){
        return "";
    }

    @Override
    public String outputData() {
        return "[" + getStatusIcon() + "] " + "todo " + getName() ;
    }
}

