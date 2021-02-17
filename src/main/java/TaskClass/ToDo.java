package TaskClass;

import TaskClass.Task;

public class ToDo extends Task {
    public static final String TASK_INFO_FORMAT = "[T][%s]%s";

    public ToDo(String name, boolean isDone){
        super(name, isDone);
    }

    @Override
    public void printTaskInfo() {
        if(super.getDone()){
                System.out.println(String.format(TASK_INFO_FORMAT,"X",taskName));
        } else {
                System.out.println(String.format(TASK_INFO_FORMAT," ",taskName));
        }
    }

    @Override
    public void newTaskOutput(){
        System.out.print(LINE_SEPERATOR + "\n    ToDo added:\n      ");
        this.printTaskInfo();
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
