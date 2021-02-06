package task;

import task.Task;

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
}

