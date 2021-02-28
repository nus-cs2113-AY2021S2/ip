package duke;

import duke.Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final Scanner SCANNER = new Scanner(System.in);

    public Ui() {
    }

    public String readCommand() {
        return SCANNER.nextLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showLine() {
        final String HORIZONTAL_LINE = "____________________________________________________________";
        System.out.println(HORIZONTAL_LINE);
    }

    public void showLoadingError() {
        System.out.println("Sorry, there was an error in loading the data.");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void showMissingIndexInput() {
        System.out.println("Please enter the index of the task");
    }

    public static void showIncorrectIndexType() {
        System.out.println("taskIndex must be an integer");
    }

    public static void showFileNotFound() {
        System.out.println("Data file does not exist");
    }

    public static void showLoadingData() {
        System.out.println("Loading saved data");
    }

    public static void showCreateNewFile(String filePath) {
        System.out.println("File created: " + filePath);
    }

    public void showSaveError() {
        System.out.println("Oops something went wrong with saving the file");
    }

    public void showNotInRange(int taskIndex, int taskListSize) {
        System.out.println("Task " + (taskIndex + 1) + " does not exist.");
        System.out.println("There are " + taskListSize + " tasks in the list.");
    }

    public void showAddTask(Task task, int taskListSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskListSize + " tasks in the list.");
    }

    public void showDeleteTask(Task task, int taskListSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskListSize + " tasks in the list.");
    }

    public void showAlreadyCompleted(Task task) {
        System.out.println("This task has already been completed:");
        System.out.println("  " + task);
    }

    public void showMarkedDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showNoTasks() {
        System.out.println("There are no tasks in your list");
    }

    public void showTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            Task currentTask = taskList.getTaskAtIndex(i);
            System.out.println(i + 1 + "." + currentTask);
        }
    }

    public void showFindTasks(ArrayList<Task> taskList, String keyword) {
        if (taskList.size() == 0) {
            System.out.println("There are no tasks matching the keyword: " + keyword);
            return;
        }
        System.out.println("Here are the tasks matching the keyword: " + keyword);
        for (Task task : taskList) {
            System.out.println(task);
        }
    }
}
