package TaskClass;

public class Task {
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

    public void printTaskInfo(){};

    public void getDoneOutput(){};

    public void newTaskOutput(){};

    public String toFile(){
        return "";
    }
}
