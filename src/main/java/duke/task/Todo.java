package duke.task;

/**
 * Todo class - object corresponds to a todo task represented by name and check mark.
 */
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

