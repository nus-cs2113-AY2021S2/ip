package ManagerClasses;

import CommandClasses.CommandManager;
import ExceptionClasses.EmptyByOrAtWhenException;
import ExceptionClasses.EmptyTaskDescriptionException;

import java.util.Scanner;

/**
 * Manages the user input received from the command line.
 */
public class InputManager {
    private final TaskManager taskManager;
    private final StorageManager storageManager;

    /**
     * Constructor for InputManager class
     */
    public InputManager(){
        taskManager = new TaskManager();
        storageManager = new StorageManager(taskManager);
    }

    /**
     * Imports tasks from previously saved text file and contains the loop that receives user inputs.
     */
    public void manageInput() {
        storageManager.importTasksFromTxtFile();
        inputLoop();
    }

    /**
     * Continuous loop that gets user input and invokes the necessary command according to the input.
     * Exits when user enters 'bye' command.
     */
    private void inputLoop() {
        while(true) {
            String input = getUserInput();
            CommandManager commandManager = new CommandManager(input);

            switch (commandManager.getCommandType()) {
            case BYE:
                PrintManager.printByeMessage();
                return;
            case LIST:
                taskManager.showTasks();
                break;
            case DONE:
                executeDoneCommand(commandManager);
                break;
            case DEADLINE:
                executeDeadlineCommand(commandManager);
                break;
            case EVENT:
                executeEventCommand(commandManager);
                break;
            case TODO:
                executeTodoCommand(commandManager);
                break;
            case DELETE:
                executeDeleteCommand(commandManager);
                break;
            case FIND:
                executeFindCommand(commandManager);
                break;
            default:
                PrintManager.printHelpMessage();
            }
            PrintManager.printBreakLine();
        }
    }

    private void executeDoneCommand(CommandManager commandManager) {
        taskManager.markDone(commandManager.getTaskNum());
        storageManager.saveTasksInTxtFile();
    }

    private void executeFindCommand(CommandManager commandManager) {
        try {
            taskManager.findTasks(commandManager.getDescription());
        } catch (EmptyTaskDescriptionException e) {
            System.out.println("Error -> Please enter a keyword to search");
        }
    }

    private void executeDeleteCommand(CommandManager commandManager) {
        taskManager.deleteTask(commandManager.getTaskNum());
        storageManager.saveTasksInTxtFile();
    }

    private void executeTodoCommand(CommandManager commandManager) {
        try {
            taskManager.addTodoToList(commandManager.getDescription());
            storageManager.saveTasksInTxtFile();
        } catch (EmptyTaskDescriptionException | NullPointerException e) {
            System.out.println("Error -> Empty task description");
        }
    }

    private void executeEventCommand(CommandManager commandManager) {
        try {
            taskManager.addEventToList(commandManager.getDescription(), commandManager.getWhen());
            storageManager.saveTasksInTxtFile();
        } catch (EmptyTaskDescriptionException e) {
            System.out.println("Error -> Empty task description");
        } catch (EmptyByOrAtWhenException | NullPointerException e) {
            System.out.println("Error -> Event at when is not stated");
        }
    }

    private void executeDeadlineCommand(CommandManager commandManager) {
        try {
            taskManager.addDeadlineToList(commandManager.getDescription(), commandManager.getWhen());
            storageManager.saveTasksInTxtFile();
        } catch (EmptyTaskDescriptionException e) {
            System.out.println("Error -> Empty task description");
        } catch (EmptyByOrAtWhenException | NullPointerException e) {
            System.out.println("Error -> Deadline by when is not stated");
        }
    }

    /**
     * Returns the user input as a String which can then be parsed to execute commands.
     * @return the user input received
     */
    private String getUserInput() {
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        PrintManager.printBreakLine();
        return input;
    }
}
