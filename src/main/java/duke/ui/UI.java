package duke.ui;

import duke.datamanager.FileManager;
import duke.task.Task;

import java.io.IOException;

public class UI {
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static String divider = "-----------------------------------";

    public static void showWelcomeScreen() {
        System.out.println("Hello from\n" + logo);
        System.out.println(divider);
        System.out.println("Hello there! I'm Duke");
        showLoadDataScreen();
        System.out.println("I have the high ground! How may I help you?");
        System.out.println(divider);
    }

    public static void showLoadDataScreen() {
        System.out.println("Searching for saved data...");
        try {
            FileManager.readFile();
            System.out.println("Loading saved data...");
        } catch (IOException e) {
            System.out.println("No data found - please ensure that a directory " +
                    "called 'data' is in your project directory");
        }
    }

    public static void showSaveDataScreen() {
        System.out.println("Saving data...");
        try {
            FileManager.saveToFile();
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Unable to save - please create a directory called 'data' in the project directory!");
        }
    }

    public static void requestInput() {
        System.out.println(divider);
        System.out.println("What is thy bidding, my master?");
        System.out.println(divider);
    }

    public static void showAllTasks() {
        System.out.println("This is your current list:");
        for (int i = 0; i < Task.getTaskCounter(); i++){
            int displayedTask = i + 1;
            System.out.println(displayedTask + ". " + Task.getTaskList().get(i));
        }
        System.out.println("Of these tasks, you still have " + Task.getRemainingTasks() + " to complete!");
        UI.requestInput();
    }

    public static void taskAddedSuccessfully() {
        System.out.println("Roger Roger. The following task has been added:");
        boolean getCompletedTask = false;
        System.out.println(Task.getRecentTask(getCompletedTask));
        System.out.println("You now have " + Task.getTaskCounter() + " tasks in your list!");
        showSaveDataScreen();
        UI.requestInput();
    }

    public static void taskCompletedSuccessfully() {
        System.out.println("The Force is with you! The following task has been marked as done:");
        boolean getCompletedTask = true;
        System.out.println(Task.getRecentTask(getCompletedTask));
        System.out.println("You still have " + Task.getRemainingTasks() + " tasks to complete in your list!");
        showSaveDataScreen();
        UI.requestInput();
    }

    public static void taskDeletedSuccessfully() {
        System.out.println("I am altering the deal. The following task has been deleted:");
        boolean getDeletedTask = true;
        System.out.println(Task.getRecentTask(getDeletedTask));
        System.out.println("You still have " + Task.getRemainingTasks() + " tasks to complete in your list!");
        showSaveDataScreen();
        UI.requestInput();
    }

    public static void showFarewellScreen() {
        System.out.println(UI.divider);
        System.out.println("Bye! Hope to hear from you again soon!");
        System.out.println(UI.divider);
    }
}
