package jarvis.ui;

import jarvis.commands.*;
import jarvis.exception.*;
import jarvis.task.Task;
import jarvis.task.TaskList;

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
     * Executes the command depending on user input
     *
     * @return the list of tasks
     * @throws InvalidCommandException if the command provided by the user is invalid
     * @throws EmptyListException if the list is empty
     * @throws InvalidTaskException if the task is not in the list
     * @throws EmptyDescriptionException if the task description is not given by the user
     * @throws EmptyTaskIdException if the task number is not given by the user
     * @throws NoMatchException if no tasks in the list matches the keyword provided by the user
     * @throws EmptyKeywordException if the keyword is not provided by the user
     */
    public ArrayList<Task> performTask() throws InvalidCommandException, EmptyListException, InvalidTaskException,
            EmptyDescriptionException, EmptyTaskIdException, NoMatchException, EmptyKeywordException {
        String userInput = in.nextLine();
        if (userInput.startsWith("bye")) {
            ExitJarvis.execute();
        } else if (userInput.startsWith("todo")) {
            AddTodo.execute(userInput);
        } else if (userInput.startsWith("deadline")) {
            AddDeadline.execute(userInput);
        } else if (userInput.startsWith("event")) {
            AddEvent.execute(userInput);
        } else if (userInput.startsWith("list")) {
            ListTasks.execute();
        } else if (userInput.startsWith("done")) {
            Done.execute(userInput);
        } else if (userInput.startsWith("delete")) {
            Delete.execute(userInput);
        } else if (userInput.startsWith("find")) {
            Find.execute(userInput);
        } else {
            throw new InvalidCommandException();
        }
        return TaskList.getTaskList();
    }
}
