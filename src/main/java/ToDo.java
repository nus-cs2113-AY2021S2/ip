public class ToDo extends Task{
    public static final String TASK_INFO_FORMAT = "[T][%s]%s";

    public ToDo(String name, boolean isDone){
        super(name, isDone);
    }

    @Override
    public String getTaskInfoFormat() {
        if(super.getTaskDone()){
                return String.format(TASK_INFO_FORMAT,"X",taskName);
        } else {
                return String.format(TASK_INFO_FORMAT," ",taskName);
        }
    }
}
