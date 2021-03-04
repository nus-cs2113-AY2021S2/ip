package duke.exception;

public class TaskNotFoundException extends Exception{

    @Override
    public String getMessage(){
        String m = "Task is not in the current task list.\n";
        return m;
    }
}
