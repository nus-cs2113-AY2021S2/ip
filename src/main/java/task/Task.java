package task;

/**
 * Represents the parent class for different types of tasks
 */
public abstract class Task {
    protected String taskName;
    protected boolean taskDone;
    public static final String TASK_INFO_FORMAT = "[ ][%s]%s";
    public static final String LINE_SEPERATOR = "    ____________________________________________________________";

    public Task(String name, boolean done) {
        taskName = name;
        taskDone = done;
    }

    public String getName() {
        return taskName;
    }

    public boolean getDone() {
        return taskDone;
    }

    public void setName(String name) {
        taskName = name;
    }

    public void setDone(boolean done){
        taskDone = done;
    }


    public abstract String getTaskInfoForDisplay();

    public String getTaskInfoForStorage(){
        return "";
    }
}
