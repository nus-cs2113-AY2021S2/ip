package duke.command;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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


    public static void displayUI(Scanner in) {
        runProgram(in);
    }

    public static void loadData() throws FileNotFoundException {
        System.out.println("Loading data from your file, 'duke.txt'...");
        File data = new File("data/duke.txt");
        taskArrayList = new ArrayList<>();
        Scanner sc = new Scanner(data);
        while (sc.hasNext()) {
            String taskSentence = sc.nextLine();
            char taskCategory = taskSentence.charAt(1);
            boolean isDone = taskSentence.substring(4, 5).compareTo("\u2713") == 0;
            switch (taskCategory) {
            case 'T':
                String toDoDescription = taskSentence.substring(7);
                Todo t = new Todo(toDoDescription);
                if (isDone) {
                    t.markAsDone();
                }
                taskArrayList.add(t);
                break;
            case 'D':
                int dueDateStartingIndex = taskSentence.indexOf("(by:");
                String dueDate = taskSentence.substring(dueDateStartingIndex + 5, taskSentence.length() - 1);
                String deadlineDescription = taskSentence.substring(7, dueDateStartingIndex);
                Deadline d = new Deadline(deadlineDescription, dueDate);
                if (isDone) {
                    d.markAsDone();
                }
                taskArrayList.add(d);
                break;
            case 'E':
                int durationStartingIndex = taskSentence.indexOf("(at:");
                String duration = taskSentence.substring(durationStartingIndex + 5, taskSentence.length() - 1);
                String eventDescription = taskSentence.substring(7, durationStartingIndex);
                Event e = new Event(eventDescription, duration);
                if (isDone) {
                    e.markAsDone();
                }
                taskArrayList.add(e);
                break;
            default:
                break;
            }
        }
        System.out.println("Done loading the data!");
        printDivider();

    }

    private static void runProgram(Scanner in) {
        try {
            File data = new File("data/duke.txt");
            if (data.createNewFile()) {
                printMessageForCreatingNewFile(data);
            } else {
                loadData();
            }
        } catch (IOException e) {
            createDirectory();
        }
        printWelcomeMessage();
        while (!isEnding) {
            String input = in.nextLine();
            switch (input) {
            case LIST_COMMAND:
                TaskManager.printAllTasks(taskArrayList);
                break;
            case BYE_COMMAND:
                stopProgram();
                break;
            default:
                try {
                    TaskManager.handleTask(input);
                } catch (CommandNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    private static void createDirectory() {
        System.out.println("We couldn't find the 'data' folder in your directory. This data folder will store your tasks.");
        System.out.println("Creating 'data' folder now...");
        File relativePathFileName = new File(DIRECTORY_NAME);
        String fullPath = relativePathFileName.getAbsolutePath();
        File absolutePathFileName = new File(fullPath);
        boolean bool = absolutePathFileName.mkdirs();
        if (bool) {
            System.out.println("'data' folder created successfully");
        } else {
            System.out.println("Sorry, couldn't create 'data' folder in your directory");
        }
    }

    private static void printMessageForCreatingNewFile(File data) {
        System.out.println("Your file that stores all your tasks is not found! (data/duke.txt) :(");
        System.out.println("Creating new file now to store your future tasks! :)");
        System.out.println("File created: " + data.getName());
        printDivider();
    }

    public static void printWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO_STRING);
        printDivider();
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
        printDivider();
    }

    public static void stopProgram() {
        isEnding = true;
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
        System.exit(0);
    }

    public static void printDivider() {
        System.out.println("________________________________");
    }


}
