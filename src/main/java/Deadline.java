public class Deadline extends Task{
    public static final String TASK_INFO_FORMAT = "[D][%s]%s (by: %s)";
    private String doBefore;

    public Deadline(String name, boolean isDone, String doBefore){
        super(name, isDone);
        this.doBefore = doBefore;
    }

    @Override
    public String getTaskInfoFormat() {
        if(super.getTaskDone()){
            return String.format(TASK_INFO_FORMAT,"X",taskName,doBefore);
        } else {
            return String.format(TASK_INFO_FORMAT," ",taskName,doBefore);
        }
    }
}
