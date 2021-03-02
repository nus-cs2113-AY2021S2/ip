package duke.command;

import duke.accessfile.FileManager;
import duke.task.Task;
import duke.task.TaskManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainUI {
    public static final String LIST_COMMAND = "list";
    public static final String BYE_COMMAND = "bye";
    public static final String FILE_PATH = "data/duke.txt";
    public static final String DIRECTORY_NAME = "data";
    public static boolean isEnding = false;
    public static ArrayList<Task> taskArrayList = new ArrayList<>();
    public static final String LOGO_STRING = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private FileManager fileManager;
    private TaskManager taskManager;

    public MainUI() {
        fileManager = new FileManager();
        taskManager = new TaskManager();
    }

    public void displayUI(Scanner in) {
        runProgram(in);
    }

    private void runProgram(Scanner in) {
        try {
            File data = new File(FILE_PATH);
            if (data.createNewFile()) {
                fileManager.printMessageForCreatingNewFile(data);
            } else {
                taskArrayList = fileManager.loadData();
            }
        } catch (IOException e) {
            System.out.println("We couldn't find the 'data' folder in your directory. This data folder will store your tasks.");
            System.out.println("Creating 'data' folder now...");
            fileManager.createDirectory(DIRECTORY_NAME);
        }
        printWelcomeMessage();
        while (!isEnding) {
            String input = in.nextLine();
            switch (input) {
            case LIST_COMMAND:
                taskManager.printAllTasks(taskArrayList);
                break;
            case BYE_COMMAND:
                stopProgram();
                break;
            default:
                try {
                    taskManager.handleTask(input);
                } catch (CommandNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }


    public void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO_STRING);
        printDivider();
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
        printDivider();
    }

    public void stopProgram() {
        isEnding = true;
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
        System.exit(0);
    }

    public static void printDivider() {
        System.out.println("________________________________");
    }


}
