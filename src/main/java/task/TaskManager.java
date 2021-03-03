package task;

import exception.DukeException;
import storage.Storage;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    public ArrayList<Task> taskList;
    private final Ui ui;
    private final Storage storage;
    private Task task;

    /**
     * Constructor for TaskManager
     *
     * @throws FileNotFoundException
     * @throws DukeException
     */
    public TaskManager() throws FileNotFoundException, DukeException {
        storage = new Storage();
        taskList = storage.loadFile();
        ui = new Ui();

    }

    /**
     * Adds a task to the ArrayList
     *
     * @param task Task object to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
        try {
            storage.saveTaskListToFile(taskList);
        } catch (IOException e) {
            ui.printMessage(e.getMessage());
        }
        ui.printMessage("Got it I have added this task",
                task.toString(),
                ui.DIVIDER,
                "Enter next command: ");
    }

    /**
     * Deletes the task from the arrayList based on the index given
     *
     * @param taskNoDelete task number to be deleted
     */
    public void deleteTask(int taskNoDelete) throws DukeException {
        try {

            Task task = taskList.get(taskNoDelete - 1);
            taskList.remove(taskNoDelete - 1);
            ui.printMessage("Got it I have deleted this task");
            ui.print(taskNoDelete + ":" + task.toString());
            ui.printMessage(ui.DIVIDER,
                    "Enter next command: ");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The index is out of bounds!");
        }
    }

    /**
     * Marks the task as done base on the index given
     *
     * @param taskNoDone task number to be marked done
     */
    public void markDone(int taskNoDone) throws DukeException {
        try {
            taskList.get(taskNoDone - 1).setDone();
            ui.printMessage("I have marked this task as done: " +
                            taskList.get(taskNoDone - 1).toString(),
                    ui.DIVIDER,
                    "Enter next command: ");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The index number is out of range");
        }
    }

    /**
     * Returns the size of the arrayList
     *
     * @return number of tasks in arrayOfTasks
     */
    public int getNoOfTasks() {
        return taskList.size();
    }

    /**
     * Print all items inside the arrayList
     */
    public void printTaskItems() {
        System.out.println("You have " + getNoOfTasks() + " task(s) in the list.");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ": " + taskList.get(i));
        }
    }

    /**
     * Search and print tasks with descriptions that contains the keyword(s)
     *
     * @param keywordToFind keyword to find among the tasks
     */
    public void findTask(String keywordToFind) {
        System.out.println("Here are the matching tasks found:");
        ui.printMessage(ui.DIVIDER, ui.FOUND_DIVIDER, ui.DIVIDER, "");
        for (Task task : taskList) {
            String taskDescription = task.getDescription();
            if (taskDescription.contains(keywordToFind)) {
                System.out.println(task);
            }
        }
        ui.printMessage("", ui.DIVIDER, ui.FOUND_DIVIDER, ui.DIVIDER);
        ui.printMessage("\nEnter next command: ");
    }
}
