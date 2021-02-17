package TaskClass;

public class Deadline extends Task {
    public static final String TASK_INFO_FORMAT = "[D][%s]%s (by: %s)";
    private String doBefore;

    public Deadline(String name, boolean isDone, String doBefore){
        super(name, isDone);
        this.doBefore = doBefore;
    }

    @Override
    public void printTaskInfo() {
        if(super.getDone()){
            System.out.println(String.format(TASK_INFO_FORMAT,"X",taskName,doBefore));
        } else {
            System.out.println(String.format(TASK_INFO_FORMAT," ",taskName,doBefore));
        }
    }

    @Override
    public void newTaskOutput(){
        System.out.print(Task.LINE_SEPERATOR + "\n    Deadline added:\n      ");
        this.printTaskInfo();
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
