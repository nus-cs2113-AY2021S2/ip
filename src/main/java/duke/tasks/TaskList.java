package duke.tasks;

import java.util.ArrayList;

import duke.ui.TextUI;

import static duke.common.Messages.DIVIDER;

/**
 * Tasklist stores the tasks in an ArrayList. 
 */
public class TaskList {
    public ArrayList<Task> tasks = new ArrayList<>();
    public TextUI ui;

    public static final String ADD_SUCCESS = "Got it. I've added this task to your list. ";
    public static final String DELETE_SUCCESS = "Noted. I've removed this task from your list. ";
    public static final String DONE_SUCCESS = "Nice! I've marked this task as done. ";
    public static final String TASK_DNE = "!!! Item does not exist. !!!";
    public static final String TASK_ALREADY_DONE = "This task is already done";
    public static final String NO_TASKS = "You have no tasks in your list";
    public static final String LIST_TASKS = "Here are the tasks in your list:";

    /**
     * Constructor for the <code>TaskList</code> class. 
     * 
     * @param ui ui which determines the input and output streams
     */
    public TaskList(TextUI ui) {
        this.ui = ui;
    }

    /**
     * Adds a task to the tasklist. 
     * 
     * @param t task to be added
     */
    public void addTask(Task t) {
        tasks.add(t);
        ui.printToScreen(DIVIDER);
        ui.printToScreen(ADD_SUCCESS);
        ui.printToScreen(t.getDetails());
        ui.printToScreen(DIVIDER);
    }

    /**
     * Add a task silently without success acknowledgements. (to be used when loading data)
     * 
     * @param t task to be added
     */
    public void silentAdd(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a task from the tasklist
     * 
     * @param taskIndex index of the task to be deleted
     */
    public void deleteTask(int taskIndex) {
        ui.printToScreen(DIVIDER);
        if (taskIndex >= tasks.size() || taskIndex < 0) {
            ui.printToScreen(TASK_DNE);
            listTasks();
        } else {
            ui.printToScreen(DELETE_SUCCESS);
            Task currentTask = tasks.get(taskIndex);
            ui.printToScreen(currentTask.getDetails());
            tasks.remove(taskIndex);
            if (tasks.size()==1) {
                System.out.println("\tNow you have 1 task in the list.");
            } else {
                System.out.println("\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
            }
        }
        ui.printToScreen(DIVIDER);
    }

    /**
     * Marks a task from the tasklist as done. 
     * 
     * @param taskIndex index of the task to be marked as done
     */
    public void finishTask(int taskIndex) {
        ui.printToScreen(DIVIDER);
        if (taskIndex >= tasks.size() || taskIndex < 0) {
            ui.printToScreen(TASK_DNE);
            listTasks();
        } else if (tasks.get(taskIndex).isDone()) {
            ui.printToScreen(TASK_ALREADY_DONE);
            Task currentTask = tasks.get(taskIndex);
            ui.printToScreen(currentTask.getDetails());
        } else {
            tasks.get(taskIndex).markAsDone();
            ui.printToScreen(DONE_SUCCESS);
            Task currentTask = tasks.get(taskIndex);
            ui.printToScreen(currentTask.getDetails());
        }
        ui.printToScreen(DIVIDER);
    }

    /**
     * Lists all the tasks in the tasklist. 
     */
    public void listTasks() {
        if (tasks.size()==0) {
            ui.printToScreen(NO_TASKS);
        } else {
            ui.printToScreen(LIST_TASKS);
            for (int i=0; i<tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                ui.printToScreen(Integer.toString(i+1) + ". " + currentTask.getDetails());
            }
        }
    }

    /**
     * Finds a task in the tasklist. 
     * 
     * @param searchWord string to be searched for
     */
    public void findTask(String searchWord) {
        ui.printToScreen(DIVIDER);
        Task[] searchResults = new Task[tasks.size()];
        for (int i=0; i<tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDescription().contains(searchWord)) {
                searchResults[i] = t;
            }
        }
        ui.printToScreen("Here are the matching tasks in your list. ");
        boolean found = false;
        for (int i=0; i<searchResults.length; i++) {
            Task currentTask = searchResults[i];
            if (currentTask!=null) {
                found = true;
                ui.printToScreen(Integer.toString(i+1) + ". " + currentTask.getDetails());
            }
        }
        if (!found) {
            ui.printToScreen("No tasks found :(");
        }
        ui.printToScreen(DIVIDER);
    }

}