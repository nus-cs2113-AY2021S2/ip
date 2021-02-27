package command;

import parser.Parser;
import constant.Constant;
import ui.Ui;
import task.Deadline;
import task.Event;
import task.TaskHandler;
import task.Todo;

/**
 * CommandHandler class for handling the related command operation.
 */
public class CommandHandler {
    private static final TaskHandler taskHandler = new TaskHandler();

    /**
     * Identify the operations and proceed to perform the required action.
     *
     * @param commandCode is the verified operation/command code extracted from calling method.
     * @param userInput is the input from the console terminal.
     */
    public static void performAction(int commandCode, String userInput) {
        if (commandCode == Constant.INPUT_CODE_EXIT) {
            Ui.printExitMessage();
        } else if (commandCode == Constant.INPUT_CODE_LIST) {
            performList();
        } else if (commandCode == Constant.INPUT_CODE_DONE) {
            performDone(userInput);
        } else if (commandCode == Constant.INPUT_CODE_TODO) {
            performTodo(userInput);
        } else if (commandCode == Constant.INPUT_CODE_DEADLINE) {
            performDeadline(userInput);
        } else if (commandCode == Constant.INPUT_CODE_EVENT) {
            performEvent(userInput);
        } else if (commandCode == Constant.INPUT_CODE_DELETE) {
            performDelete(userInput);
        } else if (commandCode == Constant.INPUT_CODE_FIND) {
            performFind(userInput);
        } else if (commandCode == Constant.INPUT_CODE_INVALID) {
            performInvalid();
        } else if (commandCode == Constant.INPUT_CODE_DEFAULT_INVALID) {
            performDefault();
        } else {
            Ui.printUnknownOperationMessage();
        }
    }

    /**
     * Perform the list all task in task list operation.
     */
    private static void performList() {
        System.out.println(Constant.DIVIDER_LINE);
        Ui.printEntireCollection();
        System.out.println(Constant.DIVIDER_LINE);
    }

    /**
     * Perform the mark as done operation.
     *
     * @param userInput is the input from the console terminal.
     */
    private static void performDone(String userInput) {
        System.out.println(Constant.DIVIDER_LINE);
        int indexFromUserInput = Parser.getIndexFromUserInput(userInput);
        if (indexFromUserInput > taskHandler.getTaskCount()) {
            System.out.println("There is no task number " + indexFromUserInput + " to mark done.");
            System.out.println("Please try again!");
            System.out.println(Constant.DIVIDER_LINE);
            return;
        }
        if (taskHandler.checkStatus(indexFromUserInput - 1)) {
            System.out.println("You have already completed this task.");
            System.out.println(Constant.DIVIDER_LINE);
            return;
        }
        taskHandler.markDone(indexFromUserInput - 1);
        System.out.println("Nice! I've marked the task as done:");
        Ui.printTaskDetails(indexFromUserInput - 1);
        System.out.println(Constant.DIVIDER_LINE);
    }

    /**
     * Perform the todo operation by adding a todo task to the task list.
     *
     * @param userInput is the input from the console terminal.
     */
    private static void performTodo(String userInput) {
        String taskDescription = Parser.extractTaskDescription(userInput);
        System.out.println(Constant.DIVIDER_LINE);
        taskHandler.addTask(new Todo(taskDescription));
        Ui.printAddedTask(taskHandler.getTaskCount());
        taskHandler.increaseTaskCount();
        System.out.println(Constant.DIVIDER_LINE);
    }

    /**
     * Perform the deadline operation by adding a deadline task to the task list.
     *
     * @param userInput is the input from the console terminal.
     */
    private static void performDeadline(String userInput) {
        String taskDescription = Parser.extractTaskDescription(userInput);
        String taskTiming = Parser.extractTaskTiming(userInput);
        System.out.println(Constant.DIVIDER_LINE);
        taskHandler.addTask(new Deadline(taskDescription, taskTiming));
        Ui.printAddedTask(taskHandler.getTaskCount());
        taskHandler.increaseTaskCount();
        System.out.println(Constant.DIVIDER_LINE);
    }

    /**
     * Perform the event operation by adding a event task to the task list.
     *
     * @param userInput is the input from the console terminal.
     */
    private static void performEvent(String userInput) {
        String taskDescription = Parser.extractTaskDescription(userInput);
        String taskTiming = Parser.extractTaskTiming(userInput);
        System.out.println(Constant.DIVIDER_LINE);
        taskHandler.addTask(new Event(taskDescription, taskTiming));
        Ui.printAddedTask(taskHandler.getTaskCount());
        taskHandler.increaseTaskCount();
        System.out.println(Constant.DIVIDER_LINE);
    }

    /**
     * Perform the delete operation by deleting a task from the task list.
     *
     * @param userInput is the input from the console terminal.
     */
    private static void performDelete(String userInput) {
        System.out.println(Constant.DIVIDER_LINE);
        int indexFromUserInput = Parser.getIndexFromUserInput(userInput);
        if (indexFromUserInput > taskHandler.getTaskCount()) {
            System.out.println("There is no task number " + indexFromUserInput + " to delete.");
            System.out.println("Please try again!");
        } else {
            System.out.println("Noted. I will remove this task:");
            Ui.printTaskDetails(indexFromUserInput - 1);
            taskHandler.deleteTask(indexFromUserInput - 1);
            taskHandler.decreaseTaskCount();
            System.out.println("Now you have " + taskHandler.getTaskCount() + " task(s) in the list.");
        }
        System.out.println(Constant.DIVIDER_LINE);
    }

    /**
     * Perform the find operation by using the keyword from user input.
     *
     * @param userInput is the input from the console terminal.
     */
    private static void performFind(String userInput) {
        System.out.println(Constant.DIVIDER_LINE);
        String keyword = Parser.extractTaskDescription(userInput);
        taskHandler.findByWord(keyword);
        System.out.println(Constant.DIVIDER_LINE);
    }

    /**
     * Perform the invalid operation.
     * Prints the try again message to inform the user of wrong format.
     */
    private static void performInvalid() {
        Ui.printDefaultMessage();
    }

    /**
     * Perform the default invalid operation.
     * Prints the unrecognizable message to inform the user of invalid command code.
     */
    private static void performDefault() {
        Ui.printDefaultInvalidMessage();
    }
}
