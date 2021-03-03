package duke;

import java.util.Scanner;
import java.io.IOException;

public class Duke {

    public static void main(String[] args) throws IOException {
        welcomeMessage();

        Storage.downloadTask();

        runProgram();

        Storage.uploadTask();

        exitMessage();
    }


    private static void runProgram() {
        Scanner in = new Scanner(System.in);
        String commandInput = in.nextLine();
        String parsedCommand = Parser.parse(commandInput);

        while (!parsedCommand.equals("bye")) {
            switch (parsedCommand) {
            case "list":
                TaskList.printTasks();
                break;
            case "done":
                processDoneCommand(commandInput);
                break;
            case "todo":
                addTodo(commandInput);
                break;
            case "event":
                addEvent(commandInput);
                break;
            case "deadline":
                addDeadline(commandInput);
                break;
            case "delete":
                processDeleteCommand(commandInput);
                break;
            case "find":
                processFindCommand(commandInput);
                break;
            default:
                System.out.println("Invalid command entered, please try again.");
                break;
            }
            commandInput = in.nextLine();
            parsedCommand = Parser.parse(commandInput);
        }
    }

    private static void processFindCommand(String commandInput) {
        String keyWord = commandInput.substring(5);
        TaskList.printKeywordTasks(keyWord);
    }

    private static void processDeleteCommand(String commandInput) {
        try {
            int taskNumber = Integer.parseInt(commandInput.substring(7, 8));
            TaskList.delete(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! No task number detected, please try again.");
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! Invalid number, please try again.");
        }
    }

    private static void addDeadline(String commandInput) {
        try {
            int timeIndex = commandInput.indexOf("/by");
            TaskList.add(new Deadline(commandInput.substring(9, timeIndex - 1), commandInput.substring(timeIndex + 4)));
            int taskNumber = TaskList.getTaskCount();
            TaskList.getTask(taskNumber).printDescription();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description is invalid, please try again.");
        }
    }

    private static void addEvent(String commandInput) {
        try {
            int timeIndex = commandInput.indexOf("/at");
            TaskList.add(new Event(commandInput.substring(6, timeIndex - 1), commandInput.substring(timeIndex + 4)));
            int taskNumber = TaskList.getTaskCount();
            TaskList.getTask(taskNumber).printDescription();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description is invalid, please try again.");
        }
    }

    private static void addTodo(String commandInput) {
        try {
            TaskList.add(new Todo(commandInput.substring(5)));
            int taskNumber = TaskList.getTaskCount();
            TaskList.getTask(taskNumber).printDescription();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description is empty, please try again.");
        }
    }

    private static void processDoneCommand(String commandInput) {
        try {
            int taskNumber = Integer.parseInt(commandInput.substring(5, 6));
            TaskList.getTask(taskNumber).setDone();
            TaskList.getTask(taskNumber).printDoneTask();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! No task number detected, please try again.");
        } catch (NullPointerException e) {
            System.out.println("☹ OOPS!!! Invalid number, please try again.");
        }
    }

    private static void exitMessage() {
        Ui.printExitMessage();
    }

    private static void welcomeMessage() {
        Ui.printLogo();
        Ui.printStartMessage();
    }

}
