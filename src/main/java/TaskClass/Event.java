package TaskClass;

public class Event extends Task {
    public static final String TASK_INFO_FORMAT = "[E][%s]%s (at: %s)";
    private String duration;

    public Event(String name, boolean isDone, String duration){
        super(name, isDone);
        this.duration = duration;
    }

    @Override
    public void printTaskInfo() {
        if(super.getDone()){
            System.out.println(String.format(TASK_INFO_FORMAT,"X",taskName,duration));
        } else {
            System.out.println(String.format(TASK_INFO_FORMAT," ",taskName,duration));
        }
    }

    @Override
    public void newTaskOutput(){
        System.out.print(Task.LINE_SEPERATOR + "\n    Event added:\n      ");
        this.printTaskInfo();
    }

    @Override
    public String toFile() {
        if(taskDone) {
            return "E | 1 | " + taskName + " | " + duration;
        } else {
            return "E | 0 | " + taskName + " | " + duration;
        }
    }
}
