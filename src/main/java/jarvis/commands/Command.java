package jarvis.commands;

import jarvis.Duke;
import jarvis.exception.EmptyListException;
import jarvis.exception.InvalidTaskException;
import jarvis.parser.Parser;
import jarvis.task.Deadline;
import jarvis.task.Task;
import jarvis.tasklist.TaskList;

import java.util.ArrayList;

/**
 * Contains methods to run the commands
 */
public class Command {

    /** Prints a message when a task is added to the list */
    private static void addSuccessMessage(Task task) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + TaskList.getSize() + " tasks in the list.");
        Duke.jarvis.printDivider();
    }

    /** Terminates JARVIS program */
    public static void exitJarvis() {
        System.out.println("\tGoodbye, sir.");
        Duke.jarvis.printDivider();
        System.exit(1);
    }

    /** Adds a todo task to the list */
    public static void runTodo(String userInput) {
        Task todo = Parser.parseStringToTodo(userInput);
        TaskList.addToTasks(todo);
        addSuccessMessage(todo);
    }

    /** Adds a deadline task to the list */
    public static void runDeadline(String userInput) {
        Task deadline = Parser.parseStringToDeadline(userInput);
        TaskList.addToTasks(deadline);
        addSuccessMessage(deadline);
    }

    /** Adds an event task to the list */
    public static void runEvent(String userInput) {
        Task event = Parser.parseStringToEvent(userInput);
        TaskList.addToTasks(event);
        addSuccessMessage(event);
    }

    /**
     * Prints out all the tasks in the list
     *
     * @throws EmptyListException if there is no task in the list
     */
    public static void runList() throws EmptyListException {
        if (TaskList.getSize() != 0) {
            System.out.println("\tHere are the tasks in your list, sir:");
            for (int i = 0; i < TaskList.getSize(); i++) {
                Task task = TaskList.getTaskWithIndex(i);
                System.out.println(String.format("\t\t%d. ", i + 1) + task.toString());
            }
            Duke.jarvis.printDivider();
        } else {
            throw new EmptyListException();
        }
    }

    /**
     * Marks a task as done if it exist
     *
     * @param command command entered by the user
     * @throws InvalidTaskException if the task is not in the list
     */
    public static void runDone(String command) throws InvalidTaskException {
        String description = command.replaceFirst("done ", "");
        int taskNumber = Integer.parseInt(description.substring(0, 1));
        if (taskNumber <= TaskList.getSize()) {
            Task task = TaskList.getTaskWithIndex(taskNumber - 1);
            task.setTaskStatus(true);
            System.out.println("\tWell done, sir! I've marked this task as done:");
            System.out.println("\t\t" + task.toString());
            Duke.jarvis.printDivider();
        } else {
            throw new InvalidTaskException();
        }
    }

    /**
     * Removes a task from the list if it exist
     *
     * @param command command entered by the user
     * @throws InvalidTaskException if the task is not in the list
     */
    public static void runDelete(String command) throws InvalidTaskException {
        String description = command.replaceFirst("delete ", "");
        int taskNumber = Integer.parseInt(description.substring(0, 1));
        if (taskNumber <= TaskList.getSize()) {
            Task task = TaskList.getTaskWithIndex(taskNumber - 1);
            TaskList.removeFromTasks(task);
            System.out.println("\tNoted, sir! I've removed this task:");
            System.out.println("\t\t" + task.toString());
            System.out.println("\tNow you have " + TaskList.getSize() + " tasks in the list.");
            Duke.jarvis.printDivider();
        } else {
            throw new InvalidTaskException();
        }
    }

    /**
     * Searches for the tasks in the list that contain the keyword in the description
     *
     * @param command command entered by the user
     */
    public static void runFind(String command) {
        String keyword = Parser.parseFindCommand(command);
        ArrayList<Task> matchingTasks = TaskList.getTasksWithKeyword(keyword);
        if (matchingTasks.size() != 0) {
            System.out.println("\tHere are the matching tasks in your list sir:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task task = TaskList.getTaskWithIndex(i);
                System.out.println(String.format("\t\t%d. ", i + 1) + task.toString());
            }
        } else {
            System.out.println("\tUnfortunately, I could not find any tasks containing " + "\""+keyword+"\".");
        }
        Duke.jarvis.printDivider();
    }
}
