package task;
import ui.Ui;
import exceptions.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    public static final int COMMAND_TODO = 0;
    public static final int COMMAND_DEADLINE = 1;
    public static final int COMMAND_EVENT = 2;

    public static ArrayList<Task> tasks = new ArrayList<>();
    /**
     * Prints list of tasks
     * @throws DukeException when list is empty
     */
    public static void list() throws DukeException {
        System.out.println(Ui.DIVIDER_LINE);
        int taskNumber = 1;
        int taskCount = tasks.size();
        if (taskCount == 0) {
            throw new DukeException();
        }
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.print(taskNumber++ + ".");
            task.printTask();
        }
        System.out.println(Ui.DIVIDER_LINE);
    }
    /**
     * Adds tasks to list
     * @param userInput input given by user
     * @param command type of task
     * @param printTask flag on whether to print added task
     * @throws DukeException
     */
    public static void addTask(String userInput, int command, boolean printTask) throws DukeException {
        String description;
        String date;
        int indexOfSlash = userInput.indexOf("/");
        int stringLength = userInput.length();
        int indexOfSpace = userInput.indexOf(" ");
        int taskCount = tasks.size();

        if (taskCount == 100) {
            System.out.println(Ui.LIST_FULL);
            throw new DukeException();
        }
        switch (command) {
        case COMMAND_TODO:
            if (stringLength < 6 || indexOfSpace != 4) {
                System.out.println(Ui.INVALID_TODO);
                throw new DukeException();
            }
            description = userInput.substring(5);
            Todo todo = new Todo(description);
            tasks.add(todo);
            break;
        case COMMAND_DEADLINE:
            if (stringLength < 10 || indexOfSpace != 8 || indexOfSlash == -1) {
                System.out.println(Ui.INVALID_DEADLINE);
                throw new DukeException();
            }
            description = userInput.substring(9, indexOfSlash);
            date = userInput.substring(indexOfSlash + 3);
            Deadline deadline = new Deadline(description, date);
            tasks.add(deadline);
            break;
        case COMMAND_EVENT:
            if (stringLength < 7 || indexOfSpace != 5 || indexOfSlash == -1) {
                System.out.println(Ui.INVALID_EVENT);
                throw new DukeException();
            }
            description = userInput.substring(6, indexOfSlash);
            date = userInput.substring(indexOfSlash + 3);
            Event event = new Event(description, date);
            tasks.add(event);
            break;
        default:
        }

        if (printTask) {
            System.out.println(Ui.DIVIDER_LINE);
            taskCount = tasks.size();
            System.out.print("Got it. I've added this task:\n  ");
            tasks.get(taskCount - 1).printTask();
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            System.out.println(Ui.DIVIDER_LINE);
        }
    }
    /**
     * Deletes task from list
     * @param userInput input given by user
     * @throws DukeException when user gives an invalid task number
     */
    public static void delete(String userInput) throws DukeException {
        String number = userInput.substring(7);
        int taskCount = tasks.size();
        int taskIndex = Integer.parseInt(number) - 1;

        if (taskIndex < taskCount && taskIndex >= 0) {
            System.out.print("Noted. I've removed this task:\n  ");
            tasks.get(taskIndex).printTask();
            tasks.remove(taskIndex);
            System.out.println("Now you have " + --taskCount + " tasks in the list.");
            System.out.println(Ui.DIVIDER_LINE);
        } else {
            throw new DukeException();
        }
    }
    /**
     * Marks task as done
     * @param userInput input given by user
     * @throws DukeException when user gives an invalid task number
     */
    public static void mark(String userInput) throws DukeException {
        String number = userInput.substring(5);
        int taskCount = tasks.size();
        int taskIndex = Integer.parseInt(number) - 1;

        if (taskIndex < taskCount && taskIndex >= 0) {
            tasks.get(taskIndex).markAsDone();
            System.out.print("Nice! I've marked this task as done:\n  ");
            tasks.get(taskIndex).printTask();
            System.out.println(Ui.DIVIDER_LINE);
        }
        else {
            throw new DukeException();
        }
    }
    /**
     * Finds tasks using keyword
     * @param userInput input given by user
     */
    public static void find(String userInput) {
        int taskCount = 1;
        boolean keywordFound = false;

        String keyword = userInput.substring(5);
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                if (!keywordFound) {
                    System.out.println("The following tasks have been found:");
                }
                System.out.print(taskCount++ + ". ");
                task.printTask();
                keywordFound = true;
            }
        }
        if (!keywordFound) {
            System.out.println("Keyword was not found! Please try again!");
        }
        System.out.println(Ui.DIVIDER_LINE);
    }
    /**
     * Loads list of tasks from save file
     * @throws FileNotFoundException when file is not found
     */
    public static void load() throws FileNotFoundException {

        File f = new File("duke.txt");
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        String input;
        int taskCount = 0;
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            input = s.nextLine();
            String[] userInput = input.split(" ", 2);
            boolean isDone = Boolean.parseBoolean(userInput[0]);

            if (userInput[1].startsWith("todo")) {
                try {
                    addTask(userInput[1], COMMAND_TODO, false);
                } catch (DukeException e) {
                }
            } else if (userInput[1].startsWith("deadline")) {
                try {
                    addTask(userInput[1], COMMAND_DEADLINE, false);
                } catch (DukeException e) {
                }
            } else if (userInput[1].startsWith("event")) {
                try {
                    addTask(userInput[1], COMMAND_EVENT, false);
                } catch (DukeException e) {
                }
            }
            if (isDone) {
                tasks.get(taskCount).markAsDone();
            }
            taskCount++;
        }
        System.out.println(Ui.FILE_LOADED);
    }
    /**
     * Saves list of tasks
     * @param filePath name of the file
     * @throws IOException when an I/O exception has occurred
     */
    public static void save(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.saveTask());
        }
        System.out.println(Ui.FILE_SAVED);
        fw.close();
    }
}
