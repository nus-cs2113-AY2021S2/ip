package jarvis;

import jarvis.exception.EmptyListException;
import jarvis.exception.InvalidCommandException;
import jarvis.exception.InvalidTaskException;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.Todo;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Jarvis {
    private final Scanner in = new Scanner(System.in);    // create Scanner object
    private final ArrayList<Task> tasks = new ArrayList<>();   // create ArrayList to store all the tasks

    public Jarvis() {}

    // adds a task into the ArrayList when reading text file
    public void addToTasks(Task task) {
        tasks.add(task);
    }

    public void printDivider() {
        String DIVIDER = "--------------------------------------------------------------------------------------------";
        System.out.println(DIVIDER);
    }

    // initialisation of JARVIS (just for fun)
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

    // print these if task is successfully added in the ArrayList
    public void addSuccess(Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        printDivider();
    }

    // JARVIS stops running
    public void exitJarvis() {
        System.out.println("\tGoodbye, sir.");
        printDivider();
        System.exit(1);
    }

    // adds Todo task to the ArrayList
    public void runTodo(String command) {
        String description = command.replaceFirst("todo ", "");
        Task todo = new Todo(description);
        tasks.add(todo);
        addSuccess(todo);
    }

    // adds Deadline task to the ArrayList
    public void runDeadline(String command) {
        String task = command.replaceFirst("deadline ", "");
        String[] details = task.split("/by", 2);
        String description = details[0];
        String by = details[1];
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        addSuccess(deadline);
    }

    // adds Event task to the ArrayList
    public void runEvent(String command) {
        String task = command.replaceFirst("event ", "");
        String[] details = task.split("/at", 2);
        String description = details[0];
        String at = details[1];
        Task event = new Event(description, at);
        tasks.add(event);
        addSuccess(event);
    }

    // prints out the entire list of tasks
    public void runList() throws EmptyListException {
        if (tasks.size() != 0) {
            System.out.println("\tHere are the tasks in your list, sir:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println(String.format("\t\t%d. ", i + 1) + task.toString());
            }
            printDivider();
        } else {    // if there is no task in the ArrayList, throw EmptyListException
            throw new EmptyListException();
        }
    }

    // marks the task as done if the task exist
    public void runDone(String command) throws InvalidTaskException {
        String description = command.replaceFirst("done ", "");
        int taskNumber = Integer.parseInt(description.substring(0, 1));
        if (taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.setTaskStatus(true);
            System.out.println("\tWell done, sir! I've marked this task as done:");
            System.out.println("\t\t" + task.toString());
            printDivider();
        } else {    // if the task is not in the ArrayList, throw InvalidTaskException
            throw new InvalidTaskException();
        }
    }

    // handles InvalidTaskException
    public void handleInvalidTaskException() {
        System.out.println("\tSorry, sir.");
        System.out.println("\tNo such task was found in the list.");
        System.out.println("\tWould you like to add the task instead?");
        printDivider();
    }

    // handles EmptyListException
    public void handleEmptyListException() {
        System.out.println("\tYou do not have any pending task, sir.");
        printDivider();
    }

    // removes the task from the list if exist
    public void runDelete(String command) throws InvalidTaskException {
        String description = command.replaceFirst("delete ", "");
        int taskNumber = Integer.parseInt(description.substring(0, 1));
        if (taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            tasks.remove(task);
            System.out.println("\tNoted, sir! I've removed this task:");
            System.out.println("\t\t" + task.toString());
            System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
            printDivider();
        } else {    // if the task is not in the ArrayList, throw InvalidTaskException
            throw new InvalidTaskException();
        }
    }

    // JARVIS main task manager
    public ArrayList<Task> performTask() throws InvalidCommandException {
        String command = in.nextLine();
        if (command.startsWith("bye")) {    // bye
            exitJarvis();
        } else if (command.startsWith("todo")) {     // todo
            runTodo(command);
        } else if (command.startsWith("deadline")) {    // deadline
            runDeadline(command);
        } else if (command.startsWith("event")) {    // event
            runEvent(command);
        } else if (command.startsWith("list")) {    // list
            try {
                runList();
            } catch (EmptyListException exception) {
                handleEmptyListException();
            }
        } else if (command.startsWith("done")) {    // done
            try {
                runDone(command);
            } catch (InvalidTaskException exception) {
                handleInvalidTaskException();
            }
        } else if (command.startsWith("delete")) {
            try {
                runDelete(command);
            } catch (InvalidTaskException exception) {
                handleInvalidTaskException();
            }
        } else {    // if invalid command, throw InvalidCommandException
            throw new InvalidCommandException();
        }
        return tasks;
    }
}
