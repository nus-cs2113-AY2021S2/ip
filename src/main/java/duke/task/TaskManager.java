package duke.task;

import duke.accessfile.FileManager;
import duke.command.CommandNotFoundException;
import duke.ui.MainUI;

import java.util.ArrayList;

import java.io.IOException;

import static duke.ui.MainUI.taskArrayList;

/**
 * Represents a manager that does operations for tasks (eg: add/delete tasks)
 */
public class TaskManager {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    public static final String DONE_COMMAND = "done";
    public static final String DELETE_COMMAND = "delete";
    public static final String FIND_COMMAND = "find";

    private static final String ADDED_TASK_MESSAGE = "Got it. I have added this task: ";
    private static final String MARKED_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done: ";

    private FileManager fileManager;
    private StringParser stringParser;

    /**
     * Constructor for Task Manager
     */
    public TaskManager() {
        fileManager = new FileManager();
        stringParser = new StringParser();
    }

    /**
     * Handles incoming commands to complete task, add tasks, delete tasks and find tasks that matches a keyword.
     * @param input input from user
     * @throws CommandNotFoundException Exception will be thrown when command is not recognisable
     */
    public void handleTask(String input) throws CommandNotFoundException {
        String firstWord = stringParser.getFirstWord(input);
        switch (firstWord) {
        case DONE_COMMAND:
            try {
                int taskNumberDone = stringParser.getTaskNumberDone(input);
                markTaskAsDone(taskNumberDone);
                fileManager.updateDataFile();
            } catch (IOException e) {
                System.out.println("IOError at Done command");
            }
            break;
        case TODO_COMMAND:
            try {
                String taskDescription = stringParser.getIndexOfStringAfterWhiteSpace(input);
                Todo t = new Todo(taskDescription);
                t.checkIfToDoDescriptionExists(taskDescription);
                taskArrayList.add(t);
                printMessageAfterTaskIsAdded(t);
                fileManager.updateDataFile();
            } catch (TaskDescriptionMissingException e) {
                System.out.println(e.getMessage());
                MainUI.printDivider();
            } catch (IOException e) {
                System.out.println("IOError at TODO Command");
            }
            break;
        case DEADLINE_COMMAND:
            try {
                String dueDate = stringParser.getStringAfterSlash(input);
                String taskDescription = stringParser.getStringAfterWhiteSpaceAndBeforeSlash(input);
                Deadline d = new Deadline(taskDescription, dueDate);
                d.checkIfDeadlineDescriptionExists(taskDescription);
                taskArrayList.add(d);
                printMessageAfterTaskIsAdded(d);
                fileManager.updateDataFile();
            } catch (TaskDescriptionMissingException e) {
                System.out.println(e.getMessage());
                MainUI.printDivider();
            } catch (IOException e) {
                System.out.println("IOError at DEADLINE Command");
            }
            break;
        case EVENT_COMMAND:
            try {
                String eventPeriod = stringParser.getStringAfterSlash(input);
                String taskDescription = stringParser.getStringAfterWhiteSpaceAndBeforeSlash(input);
                Event e = new Event(taskDescription, eventPeriod);
                taskArrayList.add(e);
                printMessageAfterTaskIsAdded(e);
                fileManager.updateDataFile();
            } catch (IOException e) {
                System.out.println("IOError at EVENT Command");
            }
            break;
        case DELETE_COMMAND:
            try {
                int taskNumberDeleted = stringParser.getTaskNumberDeleted(input);
                System.out.println("Noted. I've removed this task:\n" + taskArrayList.get(taskNumberDeleted - 1));
                taskArrayList.remove(taskNumberDeleted - 1);
                Task.decreaseTaskCount();
                System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
                fileManager.updateDataFile();
            } catch (IOException e) {
                System.out.println("IOError at DELETE Command");
            }
            break;
        case FIND_COMMAND:
            String keyword = stringParser.getKeyword(input);
            ArrayList<Task> matches = find(keyword);
            printMatchingTasks(matches);
            break;
        default:
            throw new CommandNotFoundException();
        }

    }

    /**
     * Returns an arraylist containing all the tasks that matches the keyword
     * @param keyword user's input for keyword
     * @return arraylist of all the matching tasks
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : taskArrayList) {
            if (task.getDescription().contains(keyword)) {
                matches.add(task);
            }
        }
        return matches;
    }

    /**
     * Prints the message when task has been added to Task List
     * @param task Task object that has been added
     */
    public void printMessageAfterTaskIsAdded(Task task) {
        MainUI.printDivider();
        System.out.println(ADDED_TASK_MESSAGE);
        System.out.println("\t" + task);
        printTaskCount();
        MainUI.printDivider();
    }

    /**
     * Prints the number of tasks in the task list
     */
    public void printTaskCount() {
        System.out.println("Now you have " + Task.getTaskCount() + " task(s) in the list.");
    }

    /**
     * Marks the task as done in the task list
     * @param taskNumber task number of a task that is done
     */
    public void markTaskAsDone(int taskNumber) {
        System.out.println(MARKED_TASK_AS_DONE_MESSAGE);
        taskArrayList.get(taskNumber - 1).markAsDone();
        System.out.println(taskArrayList.get(taskNumber - 1));
        MainUI.printDivider();

    }

    /**
     * Prints all the tasks in task array list
     * @param taskArrayList task array list containing all the tasks
     */
    public void printAllTasks(ArrayList<Task> taskArrayList) {
        int TaskCount = 0;
        MainUI.printDivider();
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskArrayList) {
            TaskCount += 1;
            System.out.println(TaskCount + "." + task);
        }
        MainUI.printDivider();
    }

    /**
     * Prints all the task that matches user's keyword in task array list
     * @param taskArrayList task array list containing all the tasks
     */
    public void printMatchingTasks(ArrayList<Task> taskArrayList) {
        int TaskCount = 0;
        MainUI.printDivider();
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : taskArrayList) {
            TaskCount += 1;
            System.out.println(TaskCount + "." + task);
        }
        MainUI.printDivider();
    }


}