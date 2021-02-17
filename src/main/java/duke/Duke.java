package duke;

import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidCommandException;

import java.util.Scanner;

import static duke.Storage.loadFile;
import static duke.Storage.saveFile;
import static duke.TaskList.getCommand;


public class Duke {
    private static final TaskList taskList = new TaskList();
    private static final Scanner in = initialiseInput();
    private static Command command;
    private static String input;

    /**
     * Main entry point of the application.
     * Initializes the application and starts the interaction with the user.
     */
    public static void main(String[] args) {
        printInitialMsg();
        loadFile(taskList);
        do {
            scanInput();
            printReaction();
        } while (canContinue());
        saveFile(taskList);
    }

    private static void printInitialMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void scanInput() {
        input = takeInput(in);
        processInput(taskList, input);
    }

    private static void printReaction() {
        switch (command) {
        case ADD:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            // Fallthrough
        case TODO:
            System.out.println("Added: " + taskList.getLastTask());
            break;
        case BYE:
            System.out.println("Bye. Hope to see you again soon!");
            break;
        case LIST:
            System.out.println(taskList);
            break;
        case DONE:
            int taskNum = getTaskNum(input, Command.DONE);
            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() +
                    taskList.getTask(taskNum - 1));
            break;
        case DELETE:
            taskNum = getTaskNum(input, Command.DELETE);
            System.out.println("Noted. I've removed this task:"+ System.lineSeparator() +
                    taskList.getDeletedTask(taskNum - 1));
        case ERROR:
            break;
        }
    }

    private static boolean canContinue() {
        return command != Command.BYE;
    }

    private static Scanner initialiseInput() {
        return new Scanner(System.in);
    }

    private static String takeInput(Scanner in) {
        return in.nextLine();
    }

    private static void processInput(TaskList taskList, String input) {
        try {

            command =  getCommand(input);
            switch (command) {
            case ADD:
                command = taskList.addTask(input, Command.ADD);
                break;
            case TODO:
                command = taskList.addTask(input.replaceFirst("todo ", ""), Command.TODO);
                break;
            case DEADLINE:
                command = taskList.addTask(input.replaceFirst("deadline ", ""), Command.DEADLINE);
                break;
            case EVENT:
                command = taskList.addTask(input.replaceFirst("event ", ""), Command.EVENT);
                break;
            case DONE:
                try {
                    taskList.validateDescription(input, Command.DONE);
                } catch (EmptyDescriptionException e) {
                    System.out.println("Done command needs task number!");
                    ;
                    command = Command.ERROR;
                    break;
                }
                int taskNum = getTaskNum(input, Command.DONE);
                taskList.finishTask(taskNum - 1);
                break;
            case DELETE:
                try {
                    taskList.validateDescription(input, Command.DELETE);
                } catch (EmptyDescriptionException e) {
                    System.out.println("Delete command needs task number!");
                    command = Command.ERROR;
                    break;
                }
                break;
            default:
                break;
            }
        } catch (InvalidCommandException e) {
            System.out.println("Wrong command entered!: " + input);
            command = Command.ERROR;
        }
    }

    private static int getTaskNum(String input, Command command) {
        switch (command) {
        case DONE:
            return Integer.parseInt(input.replaceFirst("done ", ""));
        case DELETE:
            return Integer.parseInt(input.replaceFirst("delete ", ""));
        default:
            return -1;
        }
    }
}