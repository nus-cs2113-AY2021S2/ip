public class Event extends Task{
    public static final String TASK_INFO_FORMAT = "[E][%s]%s (at: %s)";
    private String duration;

    public Event(String name, boolean isDone, String duration){
        super(name, isDone);
        this.duration = duration;
    }

    @Override
    public String getTaskInfoFormat() {
        if(super.getTaskDone()){
            return String.format(TASK_INFO_FORMAT,"X",taskName,duration);
        } else {
            return String.format(TASK_INFO_FORMAT," ",taskName,duration);
        }
    }
}
