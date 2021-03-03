package task;

public class Deadline extends Task {
    public static final String TASK_INFO_FORMAT = "[D][%s]%s (by: %s)";
    private String doBefore;

    public Deadline(String name, boolean isDone, String doBefore){
        super(name, isDone);
        this.doBefore = doBefore;
    }

    @Override
    public String getTaskInfo(){
        if(super.getDone()){
            return String.format(TASK_INFO_FORMAT,"X",taskName,doBefore);
        } else {
            return String.format(TASK_INFO_FORMAT," ",taskName,doBefore);
        }
    }

    @Override
    public String toFile(){
        if(taskDone) {
            return "D | 1 | " + taskName + " | " + doBefore;
        } else {
            return "D | 0 | " + taskName + " | " + doBefore;
        }
    }
}
