package task;

public class ToDo extends Task {
    public static final String TASK_INFO_FORMAT = "[T][%s]%s";

    public ToDo(String name, boolean isDone){
        super(name, isDone);
    }

    @Override
    public String getTaskInfo(){
        if(super.getDone()){
            return String.format(TASK_INFO_FORMAT,"X",taskName);
        } else {
            return String.format(TASK_INFO_FORMAT," ",taskName);
        }
    }

    @Override
    public String toFile(){
        if(taskDone) {
            return "T | 1 | " + taskName;
        } else {
            return "T | 0 | " +taskName;
        }
    }
}
