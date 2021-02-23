package task;

import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    public ArrayList<Task> taskList;
    private Ui ui;
    private Storage storage;

    public TaskManager() {
        storage = new Storage();
        try {
            taskList = storage.loadFile();
        } catch (FileNotFoundException e) {
            taskList = new ArrayList<>();
        }
        ui = new Ui();

    }

    public void addTask(Task task) {
        taskList.add(task);
        try {
            storage.saveTaskListToFile(taskList);
        }catch (IOException e){
                ui.printMessage(e.getMessage());
        }
        ui.printMessage("Got it I have added this task",
                task.toString(),
                ui.DIVIDER,
                "Enter next command: ");
    }

    public void deleteTask(int taskNoDelete){
        Task task = taskList.get(taskNoDelete-1);
        taskList.remove(taskNoDelete-1);
        ui.printMessage("Got it I have deleted this task");
        ui.print(taskNoDelete + ":" +  task.toString());
        ui.printMessage(ui.DIVIDER,
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
