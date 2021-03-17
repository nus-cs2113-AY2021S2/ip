package duke;

import duke.parser.Parser;
import duke.storage.FileHandler;
import duke.taskList.TaskList;
import duke.taskList.TaskListOperation;
import duke.ui.UI;

import java.util.Scanner;

/**
 * <h1>Hi, this is Friday, your personal assistant!</h1>
 * This application is designed for managing tasks. The
 * user can add, delete, search, and mark tasks as done.
 * It is developed using the basic framework known as
 * Duke.
 *
 * @author Song Yu
 * @version 1.0
 * @since 2021-02-24
 */
public class Duke {

    /**
     * The list that stores all tasks of the user.
     */
    private static final TaskList taskList = new TaskList();

    /**
     * Gets user input and execute corresponding command until the loop is exit.
     */
    private static void operateMainLoop() {
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();
            String userCommand = Parser.getCommand(userInput);
            switch (userCommand) {
            case "help":
                UI.printHelp();
                break;
            case "list":
                taskList.printCurrentList();
                break;
            case "todo":
                TaskListOperation.addTodoTaskToList(taskList, userInput);
                FileHandler.writeTaskList(taskList);
                break;
            case "deadline":
                TaskListOperation.addDeadlineTaskToList(taskList, userInput);
                FileHandler.writeTaskList(taskList);
                break;
            case "event":
                TaskListOperation.addEventTaskToList(taskList, userInput);
                FileHandler.writeTaskList(taskList);
                break;
            case "done":
                TaskListOperation.handleDoneTask(taskList, userInput);
                FileHandler.writeTaskList(taskList);
                break;
            case "delete":
                TaskListOperation.deleteTask(taskList, userInput);
                FileHandler.writeTaskList(taskList);
                break;
            case "find":
                TaskListOperation.searchTaskByString(taskList, userInput);
                break;
            case "exit":
                //FALL-THROUGH
            case "bye":
                FileHandler.writeTaskList(taskList);
                return;
            default:
                UI.printNotCommand();
                break;
            }
        }
    }

    /**
     * The main method that drives the application.
     *
     * @param args Commandline arguments.
     */
    public static void main(String[] args) {
        TaskListOperation.initializeTaskList(taskList);
        UI.printGreetings();
        operateMainLoop();
        UI.printExitGreetings();
    }
}
