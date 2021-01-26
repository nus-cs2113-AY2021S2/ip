public class Task {
    private String taskName;
    private boolean taskDone;

    public Task(String name, boolean done){
        taskName = name;
        taskDone = done;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean getTaskDone(){
        return taskDone;
    }

    public void setTaskName(String name){
        taskName = name;
    }

    public void setTaskDone(boolean done){
        taskDone = done;
    }
}
