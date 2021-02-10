public class Task {
    public String taskName;
    public boolean taskDone;
    public static final String TASK_INFO_FORMAT = "[ ][%s]%s";
    public static final String LINE_SEPERATOR = "    ____________________________________________________________";

    public Task(String name, boolean done) {
        taskName = name;
        taskDone = done;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean getTaskDone() {
        return taskDone;
    }

    public void setTaskName(String name) {
        taskName = name;
    }

    public void setTaskDone(boolean done){
        taskDone = done;
    }

    public void printTaskInfo(){};

    public void getDoneOutput(){};

    public void newTaskOutput(){};
}
