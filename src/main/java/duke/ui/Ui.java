package duke.ui;

import duke.datamanager.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class Ui {
    /**
     * Prints the logo for Duke
     */
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static String divider = "-----------------------------------";

    /**
     * Combines the logo and dividers to generate a complete welcome screen
     */
    public static void showWelcomeScreen() {
        System.out.println("Hello from\n" + logo);
        System.out.println(divider);
        System.out.println("Hello there! I'm Duke");
        showLoadDataScreen();
        System.out.println("I have the high ground! How may I help you?");
        System.out.println(divider);
    }

    /**
     * This reads the saved data file and indicates success or failure
     */
    public static void showLoadDataScreen() {
        System.out.println("Searching for saved data...");
        try {
            Storage.readFile();
            System.out.println("Loading saved data...");
        } catch (IOException e) {
            System.out.println("No data found - please ensure that a directory " +
                    "called 'data' is in your project directory");
        }
    }

    /**
     * Displays feedback that data is being saved, while performing the data saving too
     */
    public static void showSaveDataScreen() {
        System.out.println("Saving data...");
        try {
            Storage.saveToFile();
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Unable to save - please create a directory called 'data' in the project directory!");
        }
    }

    /**
     * A user-friendly request for input
     */
    public static void requestInput() {
        System.out.println(divider);
        System.out.println("What is thy bidding, my master?");
        System.out.println(divider);
    }

    /**
     * Formats the printing of a list of all tasks
     */
    public static void showAllTasks() {
        System.out.println("This is your current list:");
        showTaskList();
        System.out.println("Of these tasks, you still have " + Task.getRemainingTasks() + " to complete!");
        Ui.requestInput();
    }

    /**
     * Generates a list of the tasks
     */
    private static void showTaskList() {
        for (int i = 0; i < Task.getTaskCounter(); i++){
            int displayedTask = i + 1;
            System.out.println(displayedTask + ". " + Task.getTaskList().get(i));
        }
    }

    /**
     * Informs user that task has been added to list successfully
     */
    public static void taskAddedSuccessfully() {
        System.out.println("Roger Roger. The following task has been added:");
        boolean getCompletedTask = false;
        System.out.println(Task.getRecentTask(getCompletedTask));
        System.out.println("You now have " + Task.getTaskCounter() + " tasks in your list!");
        showSaveDataScreen();
        Ui.requestInput();
    }

    /**
     * Informs user that task has been successfully marked as completed
     */
    public static void taskCompletedSuccessfully() {
        System.out.println("The Force is with you! The following task has been marked as done:");
        boolean getCompletedTask = true;
        System.out.println(Task.getRecentTask(getCompletedTask));
        System.out.println("You still have " + Task.getRemainingTasks() + " tasks to complete in your list!");
        showSaveDataScreen();
        Ui.requestInput();
    }

    /**
     * Informs user that task has been deleted successfully
     */
    public static void taskDeletedSuccessfully() {
        System.out.println("I am altering the deal. The following task has been deleted:");
        boolean getDeletedTask = true;
        System.out.println(Task.getRecentTask(getDeletedTask));
        System.out.println("You still have " + Task.getRemainingTasks() + " tasks to complete in your list!");
        showSaveDataScreen();
        Ui.requestInput();
    }

    /**
     * This method seeks to match a user query with matching task (and their corresponding descriptions)
     * @param query is the user input to be matched with the task description
     * @param tasks is the list of tasks which match the abovementioned query
     */
    public static void tasksFound(String query, ArrayList<Task> tasks) {
        System.out.println("We found these tasks matching the query: " + query);
        for (int i = 0; i < tasks.size(); i++) {
            int displayedNumber = i + 1;
            System.out.println(displayedNumber + ". " + tasks.get(i));
        }
        Ui.requestInput();

    }

    /**
     * Simple farewell screen before the application exits
     */
    public static void showFarewellScreen() {
        System.out.println(Ui.divider);
        System.out.println("Bye! Hope to hear from you again soon!");
        System.out.println(Ui.divider);
    }
}
