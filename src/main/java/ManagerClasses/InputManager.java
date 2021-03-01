package ManagerClasses;

import CommandClasses.Command;
import ExceptionClasses.EmptyByOrAtWhenException;
import ExceptionClasses.EmptyTaskDescriptionException;

import java.util.Scanner;

public class InputManager {
    private final TaskManager taskManager;
    private final StorageManager storageManager;

    public InputManager(){
        taskManager = new TaskManager();
        storageManager = new StorageManager(taskManager);
    }

    public void manageInput() {
        storageManager.importTasksFromTxtFile();
        inputLoop();
    }

    private void inputLoop() {
        while(true) {
            String input = getUserInput();
            Command command = new Command(input);

            switch (command.getCommandType()) {
            case BYE:
                PrintManager.printByeMessage();
                return;
            case LIST:
                taskManager.showTasks();
                break;
            case DONE:
                taskManager.markDone(command.getTaskNum());
                storageManager.saveTasksInTxtFile();
                break;
            case DEADLINE:
                try {
                    taskManager.addDeadlineToList(command.getDescription(), command.getWhen());
                    storageManager.saveTasksInTxtFile();
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println("Error -> Empty task description");
                } catch (EmptyByOrAtWhenException | NullPointerException e) {
                    System.out.println("Error -> Deadline by when is not stated");
                }
                break;
            case EVENT:
                try {
                    taskManager.addEventToList(command.getDescription(), command.getWhen());
                    storageManager.saveTasksInTxtFile();
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println("Error -> Empty task description");
                } catch (EmptyByOrAtWhenException | NullPointerException e) {
                    System.out.println("Error -> Event at when is not stated");
                }
                break;
            case TODO:
                try {
                    taskManager.addTodoToList(command.getDescription());
                    storageManager.saveTasksInTxtFile();
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println("Error -> Empty task description");
                }
                break;
            case DELETE:
                taskManager.deleteTask(command.getTaskNum());
                storageManager.saveTasksInTxtFile();
                break;
            case FIND:
                try {
                    taskManager.findTasks(command.getDescription());
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println("Error -> Please enter a keyword to search");
                }
                break;
            default:
                PrintManager.printHelpMessage();
            }
            PrintManager.printBreakLine();
        }
    }

    private String getUserInput() {
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        PrintManager.printBreakLine();
        return input;
    }
}
