package task;

/**
 * Represent a To Do task
 */
public class ToDo extends Task {
    private static final String TASK_INFO_DISPLAY_FORMAT = "[T][%s]%s";
    public static final String TASK_INFO_STORAGE_FORMAT = "T | %s | %s";
    public ToDo(String name, boolean isDone){
        super(name, isDone);
    }

    /**
     * Get the information of a ToDo task in certain format for displaying.
     * @return the string for displaying
     */
    @Override
    public String getTaskInfoForDisplay(){
        if(super.getDone()){
            return String.format(TASK_INFO_DISPLAY_FORMAT,"X",taskName);
        } else {
            return String.format(TASK_INFO_DISPLAY_FORMAT," ",taskName);
        }
    }

    /**
     * Get the information of a ToDo task in certain format for storing in file.
     * @return the string for storage
     */
    @Override
    public String getTaskInfoForStorage(){
        if(taskDone) {
            return String.format(TASK_INFO_STORAGE_FORMAT,"1",taskName);
        } else {
            return String.format(TASK_INFO_STORAGE_FORMAT,"0",taskName);
        }
    }
}
