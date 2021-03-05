package task;

/**
 * Represent a task which has a deadline.
 */
public class Deadline extends Task {
    private static final String TASK_INFO_DISPLAY_FORMAT = "[D][%s]%s (by: %s)";
    private static final String TASK_INFO_STORAGE_FORMAT = "D | %s | %s | %s";
    private String doBefore;

    public Deadline(String name, boolean isDone, String doBefore){
        super(name, isDone);
        this.doBefore = doBefore;
    }

    /**
     * Get the information of a deadline task in certain format for displaying.
     * @return the string for displaying
     */
    @Override
    public String getTaskInfoForDisplay(){
        if(super.getDone()){
            return String.format(TASK_INFO_DISPLAY_FORMAT,"X",taskName,doBefore);
        } else {
            return String.format(TASK_INFO_DISPLAY_FORMAT," ",taskName,doBefore);
        }
    }

    /**
     * Get the information of a deadline task in certain format for storing in file.
     * @return the string for storage
     */
    @Override
    public String getTaskInfoForStorage(){
        if(taskDone) {
            return String.format(TASK_INFO_STORAGE_FORMAT,"1",taskName,doBefore);
        } else {
            return String.format(TASK_INFO_STORAGE_FORMAT,"0",taskName,doBefore);
        }
    }
}
