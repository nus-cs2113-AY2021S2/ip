package actioner;

import parser.Parser;
import constant.Constant;
import printer.Printer;
import task.Deadline;
import task.Event;
import task.TaskHandler;
import task.Todo;

public class ActionHandler {
    private static final Constant constant = new Constant();
    private static final Printer printer = new Printer();
    private static final TaskHandler taskHandler = new TaskHandler();
    private static final Parser parser = new Parser();

    // Perform the action give the command
    public static void performAction(int commandCode, String userInput) {
        if (commandCode == constant.INPUT_CODE_EXIT) {
            printer.printExitMessage();
        } else if (commandCode == constant.INPUT_CODE_LIST) {
            performList();
        } else if (commandCode == constant.INPUT_CODE_DONE) {
            performDone(userInput);
        } else if (commandCode == constant.INPUT_CODE_TODO) {
            performTodo(userInput);
        } else if (commandCode == constant.INPUT_CODE_DEADLINE) {
            performDeadline(userInput);
        } else if (commandCode == constant.INPUT_CODE_EVENT) {
            performEvent(userInput);
        } else if (commandCode == constant.INPUT_CODE_DELETE) {
            performDelete(userInput);
        } else if (commandCode == constant.INPUT_CODE_INVALID) {
            performInvalid();
        } else if (commandCode == constant.INPUT_CODE_DEFAULT_INVALID) {
            performDefault();
        } else {
            System.out.println("Unknown Operation!");
        }
    }

    private static void performList() {
        System.out.println(constant.DIVIDER_LINE);
        printer.printEntireCollection();
        System.out.println(constant.DIVIDER_LINE);
    }

    private static void performDone(String userInput) {
        System.out.println(constant.DIVIDER_LINE);
        int indexFromUserInput = parser.getIndexFromUserInput(userInput);
        if (indexFromUserInput > taskHandler.getTaskCount()) {
            System.out.println("There is no task number " + indexFromUserInput + " to mark done.");
            System.out.println("Please try again!");
        } else {
            if (taskHandler.checkStatus(indexFromUserInput - 1)) {
                System.out.println("You have already completed this task.");
            } else {
                taskHandler.markDone(indexFromUserInput - 1);
                System.out.println("Nice! I've marked the task as done:");
                printer.printTaskDetails(indexFromUserInput - 1);
            }
        }
        System.out.println(constant.DIVIDER_LINE);
    }

    private static void performTodo(String userInput) {
        System.out.println(constant.DIVIDER_LINE);
        String taskDescription = parser.extractTaskDescription(userInput);
        taskHandler.addTask(new Todo(taskDescription));
        printer.printAddedTask(taskHandler.getTaskCount());
        taskHandler.increaseTaskCount();
        System.out.println(constant.DIVIDER_LINE);
    }

    private static void performDeadline(String userInput) {
        String taskTiming;
        String taskDescription;
        System.out.println(constant.DIVIDER_LINE);
        taskDescription = parser.extractTaskDescription(userInput);
        taskTiming = parser.extractTaskTiming(userInput);
        taskHandler.addTask(new Deadline(taskDescription, taskTiming));
        printer.printAddedTask(taskHandler.getTaskCount());
        taskHandler.increaseTaskCount();
        System.out.println(constant.DIVIDER_LINE);
    }

    private static void performEvent(String userInput) {
        String taskTiming;
        String taskDescription;
        System.out.println(constant.DIVIDER_LINE);
        taskDescription = parser.extractTaskDescription(userInput);
        taskTiming = parser.extractTaskTiming(userInput);
        taskHandler.addTask(new Event(taskDescription, taskTiming));
        printer.printAddedTask(taskHandler.getTaskCount());
        taskHandler.increaseTaskCount();
        System.out.println(constant.DIVIDER_LINE);
    }

    private static void performDelete(String userInput) {
        System.out.println(constant.DIVIDER_LINE);
        int indexFromUserInput = parser.getIndexFromUserInput(userInput);
        if (indexFromUserInput > taskHandler.getTaskCount()) {
            System.out.println("There is no task number " + indexFromUserInput + " to delete.");
            System.out.println("Please try again!");
        } else {
            System.out.println("Noted. I will remove this task:");
            printer.printTaskDetails(indexFromUserInput - 1);
            taskHandler.deleteTask(indexFromUserInput - 1);
            taskHandler.decreaseTaskCount();
            System.out.println("Now you have " + taskHandler.getTaskCount() + " tasks in the list.");
        }
        System.out.println(constant.DIVIDER_LINE);
    }

    private static void performInvalid() {
        System.out.println("Please try again!");
        System.out.println(constant.DIVIDER_LINE);
    }

    private static void performDefault() {
        System.out.println(constant.DIVIDER_LINE);
        System.out.println("Mushroom head could not recognize your command code!");
        System.out.println("Please try again!");
        System.out.println(constant.DIVIDER_LINE);
    }

}
