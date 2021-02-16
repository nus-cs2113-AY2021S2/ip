package duke.command;

import duke.task.TaskManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static duke.task.TaskManager.taskArrayList;

public class MainUI {
    public static final String LIST_COMMAND = "list";
    public static final String BYE_COMMAND = "bye";
    public static final String FILE_PATH = "data/duke.txt";
    public static boolean isEnding = false;

    public static void displayUI(Scanner in){
        printWelcomeMessage();
        runProgram(in);
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }


    private static void runProgram(Scanner in) {

        try {
            FileWriter firstLineWriter = new FileWriter(FILE_PATH);
            firstLineWriter.write("Duke's Task List\n");
            firstLineWriter.close();
        } catch (IOException e1) {
            System.out.println("IP/data folder not found - please ensure that the directory " +
                    "called 'data' is in your project directory under 'IP'");
        }

        while (!isEnding){
            String input = in.nextLine();
            switch (input){
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

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDivider();
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
        printDivider();
    }

    public static void stopProgram(){
        isEnding = true;
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
        System.exit(0);
    }

    public static void printDivider(){
        System.out.println("________________________________");
    }


}
