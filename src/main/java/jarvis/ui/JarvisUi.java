package jarvis.ui;

import jarvis.commands.Command;
import jarvis.exception.EmptyListException;
import jarvis.exception.InvalidCommandException;
import jarvis.exception.InvalidTaskException;
import jarvis.task.Task;
import jarvis.tasklist.TaskList;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * UI of JARVIS application.
 */
public class JarvisUi {

    private final Scanner in = new Scanner(System.in);

    /** Constructor for JarvisUi */
    public JarvisUi() {}

    /** Prints a divider */
    public void printDivider() {
        String DIVIDER = "--------------------------------------------------------------------------------------------";
        System.out.println(DIVIDER);
    }

    /** Prints a welcome message */
    public void startJarvis() throws InterruptedException {
        int DELAY = 500;    // 0.5 seconds delay
        printDivider();
        System.out.println("Importing all preferences from home interface.");
        TimeUnit.MILLISECONDS.sleep(DELAY);
        System.out.println("Synchronising from the cloud.");
        TimeUnit.MILLISECONDS.sleep(DELAY);
        System.out.println("Systems are now fully operational.");
        TimeUnit.MILLISECONDS.sleep(DELAY);
        System.out.print("Initialising");
        TimeUnit.MILLISECONDS.sleep(DELAY);
        for (int i = 0; i <= 2; i++) {
            System.out.print(".");
            TimeUnit.MILLISECONDS.sleep(DELAY);
        }
        System.out.println();
        printDivider();
        System.out.println("\tHello, sir. J.A.R.V.I.S at your service.");
        printDivider();
    }

    /**
     * Executes the command depending on the user inputs
     *
     * @return the list of tasks
     * @throws InvalidCommandException if the command is invalid
     * @throws EmptyListException if the list is empty
     * @throws InvalidTaskException if the task is not in the list
     */
    public ArrayList<Task> performTask() throws InvalidCommandException, EmptyListException, InvalidTaskException {
        String userInput = in.nextLine();
        if (userInput.startsWith("bye")) {
            Command.exitJarvis();
        } else if (userInput.startsWith("todo")) {
            Command.runTodo(userInput);
        } else if (userInput.startsWith("deadline")) {
            Command.runDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            Command.runEvent(userInput);
        } else if (userInput.startsWith("list")) {
            Command.runList();
        } else if (userInput.startsWith("done")) {
            Command.runDone(userInput);
        } else if (userInput.startsWith("delete")) {
            Command.runDelete(userInput);
        } else if (userInput.startsWith("find")) {
            Command.runFind(userInput);
        } else {    // if invalid command, throw InvalidCommandException
            throw new InvalidCommandException();
        }
        return TaskList.getTaskList();
    }
}
