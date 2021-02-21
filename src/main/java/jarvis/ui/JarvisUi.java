package jarvis.ui;

import jarvis.commands.Command;
import jarvis.exception.EmptyListException;
import jarvis.exception.HandleException;
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

    private final Scanner in = new Scanner(System.in);    // create Scanner object

    public JarvisUi() {}

    public void printDivider() {
        String DIVIDER = "--------------------------------------------------------------------------------------------";
        System.out.println(DIVIDER);
    }

    // initialisation of JARVIS
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

    public ArrayList<Task> performTask() throws InvalidCommandException {
        String userInput = in.nextLine();
        if (userInput.startsWith("bye")) {    // bye
            Command.exitJarvis();
        } else if (userInput.startsWith("todo")) {     // todo
            Command.runTodo(userInput);
        } else if (userInput.startsWith("deadline")) {    // deadline
            Command.runDeadline(userInput);
        } else if (userInput.startsWith("event")) {    // event
            Command.runEvent(userInput);
        } else if (userInput.startsWith("list")) {    // list
            try {
                Command.runList();
            } catch (EmptyListException exception) {
                HandleException.handleEmptyListException();
            }
        } else if (userInput.startsWith("done")) {    // done
            try {
                Command.runDone(userInput);
            } catch (InvalidTaskException exception) {
                HandleException.handleInvalidTaskException();
            }
        } else if (userInput.startsWith("delete")) {  // delete
            try {
                Command.runDelete(userInput);
            } catch (InvalidTaskException exception) {
                HandleException.handleInvalidTaskException();
            }
        } else {    // if invalid command, throw InvalidCommandException
            throw new InvalidCommandException();
        }
        return TaskList.getTaskList();
    }
}
