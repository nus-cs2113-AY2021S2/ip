package task;

/**
 * Represent a task that happens at specified time
 */
public class Event extends Task {
    public static final String TASK_INFO_FORMAT = "[E][%s]%s (at: %s)";
    private String duration;

    public Event(String name, boolean isDone, String duration){
        super(name, isDone);
        this.duration = duration;
    }

    /**
     * Get the information of an event task in certain format for displaying.
     * @return
     */
    @Override
    public String getTaskInfo(){
        if(super.getDone()){
            return String.format(TASK_INFO_FORMAT,"X",taskName,duration);
        } else {
            return String.format(TASK_INFO_FORMAT," ",taskName,duration);
        }
    }

    /**
     * Get the information of an event task in certain format for storing in file.
     * @return
     */
    @Override
    public String toFile() {
        if(taskDone) {
            return "E | 1 | " + taskName + " | " + duration;
        } else {
            return "E | 0 | " + taskName + " | " + duration;
        }
    }
}
