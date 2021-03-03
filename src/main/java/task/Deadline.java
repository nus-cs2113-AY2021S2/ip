package task;

/**
 * Represent a task with a deadline
 */
public class Deadline extends Task {
    public static final String TASK_INFO_FORMAT = "[D][%s]%s (by: %s)";
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
    public String getTaskInfo(){
        if(super.getDone()){
            return String.format(TASK_INFO_FORMAT,"X",taskName,doBefore);
        } else {
            return String.format(TASK_INFO_FORMAT," ",taskName,doBefore);
        }
    }

    /**
     * Get the information of a deadline task in certain format for storing in file.
     * @return the string for storing
     */
    @Override
    public String toFile(){
        if(taskDone) {
            return "D | 1 | " + taskName + " | " + doBefore;
        } else {
            return "D | 0 | " + taskName + " | " + doBefore;
        }
    }
}
