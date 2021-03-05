package task;

/**
 * Represent a To Do task
 */
public class ToDo extends Task {
    public static final String TASK_INFO_FORMAT = "[T][%s]%s";

    public ToDo(String name, boolean isDone){
        super(name, isDone);
    }

    /**
     * Get the information of a ToDo task in certain format for displaying.
     * @return
     */
    @Override
    public String getTaskInfo(){
        if(super.getDone()){
            return String.format(TASK_INFO_FORMAT,"X",taskName);
        } else {
            return String.format(TASK_INFO_FORMAT," ",taskName);
        }
    }

    /**
     * Get the information of a ToDo task in certain format for storing in file.
     * @return
     */
    @Override
    public String toFile(){
        if(taskDone) {
            return "T | 1 | " + taskName;
        } else {
            return "T | 0 | " +taskName;
        }
    }
}
