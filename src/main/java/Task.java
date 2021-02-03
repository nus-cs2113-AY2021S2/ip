public class Task {
    private String taskName;
    private boolean taskDone;
    private static final String TASK_INFO_FORMAT = "[%s]%s";

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

    public String getTaskInfoFormat(){
        if(taskDone){
            return String.format(TASK_INFO_FORMAT,"X",taskName);
        } else {
            return String.format(TASK_INFO_FORMAT," ",taskName);
        }
    }
}
