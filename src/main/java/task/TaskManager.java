package task;

import task.Task;
import ui.Ui;

import java.util.ArrayList;

public class TaskManager {
    public final ArrayList<Task> taskList;
    private Ui ui;

    public TaskManager() {
        taskList = new ArrayList<>();
        ui = new Ui();
    }

    public void addTask(Task task) {
        taskList.add(task);
        ui.printMessage("Got it I have added this task",
                task.toString(),
                ui.DIVIDER,
                "Enter next command: ");
    }

    public void markDone(int taskNoDone){
        taskList.get(taskNoDone-1).setDone();
        ui.printMessage("I have marked this task as done: " +
                taskList.get(taskNoDone-1).toString(),
                ui.DIVIDER,
                "Enter next command: ");
    }



    public int getNoOfTasks() {
        return taskList.size();
    }

    public void printTaskItems(){
            System.out.println("You have " + getNoOfTasks() + " task(s) in the list." );
            for(int i = 0; i < taskList.size(); i++) {
                System.out.println( (i + 1) + ": " + taskList.get(i));
            }
    }
}
