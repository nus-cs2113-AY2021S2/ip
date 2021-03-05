package task;

/**
 * Represents a task that happens at specified time
 */
public class Event extends Task {
    private static final String TASK_INFO_DISPLAY_FORMAT = "[E][%s]%s (at: %s)";
    private static final String TASK_INFO_STORAGE_FORMAT = "E | %s | %s | %s";
    private String duration;

    public Event(String name, boolean isDone, String duration){
        super(name, isDone);
        this.duration = duration;
    }

    /**
     * Gets the information of an event task in certain format for displaying.
     *
     * @return the string for displaying
     */
    @Override
    public String getTaskInfoForDisplay(){
        if(super.getDone()){
            return String.format(TASK_INFO_DISPLAY_FORMAT,"X",taskName,duration);
        } else {
            return String.format(TASK_INFO_DISPLAY_FORMAT," ",taskName,duration);
        }
    }

    /**
     * Gets the information of an event task in certain format for storing in file.
     *
     * @return the string for storage
     */
    @Override
    public String getTaskInfoForStorage() {
        if(taskDone) {
            return String.format(TASK_INFO_STORAGE_FORMAT,"1",taskName,duration);
        } else {
            return String.format(TASK_INFO_STORAGE_FORMAT,"0",taskName,duration);
        }
    }
}
