package duke.task;

import duke.exception.DescriptionFieldEmptyException;
import duke.exception.MultipleTimeFieldsException;
import duke.exception.TimeFieldEmptyException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Tasklist to store current task list and contains methods to
 * add, delete, find and mark ToDo, Event, Deadline tasks as done.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private final int COMMAND_TASK_SEPARATOR = 2;

    /**
     * Constructs TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets tasks.
     *
     * @return Current tasks.
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Prints every task in tasks.
     */
    public void printListMessage() {
        System.out.println(" Here are the tasks in your list:");
        for (Task task: tasks) {
            System.out.println(" " + (tasks.indexOf(task) + 1) + "." + task.toString());
        }
    }

    /**
     * Marks task as done.
     *
     * @param command Index of task to be marked as done.
     */
    public void markTaskAsDone(String[] command) {
        try {
            int doneTaskNumber = Integer.parseInt(command[1]);
            Task task = tasks.get(doneTaskNumber - 1);
            task.markAsDone();
            printDoneMessage(task);
        } catch (IndexOutOfBoundsException e) {
            printNonExistentTaskMessage();
        } catch (NumberFormatException e) {
            printNotANumberMessage();
        }
    }

    /**
     * Prints message when task is marked as done.
     *
     * @param task Task that was marked as done.
     */
    public static void printDoneMessage(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task.toString());
    }

    /**
     * Adds a Deadline task to tasks
     *
     * @param command User input for Deadline task description and time.
     */
    public void addDeadline(String[] command) {
        try {
            String[] description = command[1].split("/by", COMMAND_TASK_SEPARATOR);
            String[] splitDateAndTime = description[1].trim().split(" ");

            checkForValidDeadlineInput(description);
            String formattedDateAndTime = checkForValidDateAndTime(
                    splitDateAndTime[0], splitDateAndTime[1]);

            Deadline deadline = new Deadline(description[0].trim(), formattedDateAndTime.trim());
            tasks.add(deadline);

            printAddedMessage(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            printMissingFieldsMessage();
        } catch (DescriptionFieldEmptyException e) {
            printDescriptionFieldEmptyMessage();
        } catch (TimeFieldEmptyException e) {
            printTimeFieldEmptyMessage();
        } catch (MultipleTimeFieldsException e) {
            printTooManyTimeFieldsMessage();
        } catch (DateTimeParseException e) {
            printInvalidDateAndTimeFormat();
        }
    }

    /**
     * Adds an Event task to tasks
     *
     * @param command User input for Event task description and time.
     */
    public void addEvent(String[] command) {
        try {
            String[] description = command[1].split("/at", COMMAND_TASK_SEPARATOR);
            String[] splitDateAndTime = description[1].trim().split(" ");

            checkForValidEventInput(description);
            String formattedDateAndTime = checkForValidDateAndTime(
                    splitDateAndTime[0], splitDateAndTime[1]);

            Event event = new Event(description[0].trim(), formattedDateAndTime.trim());
            tasks.add(event);

            printAddedMessage(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            printMissingFieldsMessage();
        } catch (DescriptionFieldEmptyException e) {
            printDescriptionFieldEmptyMessage();
        } catch (TimeFieldEmptyException e) {
            printTimeFieldEmptyMessage();
        } catch (MultipleTimeFieldsException e) {
            printTooManyTimeFieldsMessage();
        } catch (DateTimeParseException e) {
            printInvalidDateAndTimeFormat();
        }
    }

    /**
     * Adds a ToDo task to tasks
     *
     * @param command User input for ToDo task description and time.
     */
    public void addToDo(String[] command) {
        try {
            ToDo toDo = new ToDo(command[1]);
            tasks.add(toDo);
            printAddedMessage(toDo);
        } catch (ArrayIndexOutOfBoundsException e) {
            printMissingFieldsMessage();
        }
    }

    /**
     * Prints message when task is added.
     *
     * @param task Task to be printed.
     */
    public void printAddedMessage(Task task) {
        System.out.println(" Alright, I've added this task:\n   " + task.toString() + "\n"
                + " Now you have " + tasks.size() + " tasks in your list.");
    }

    /**
     * Deletes task.
     *
     * @param command Index of task to be deleted.
     */
    public void deleteTask(String[] command) {
        try {
            int taskNumberToBeDeleted = Integer.parseInt(command[1]);
            Task deletedTask = tasks.get(taskNumberToBeDeleted - 1);
            tasks.remove(deletedTask);
            printDeletedMessage(deletedTask);
        } catch (IndexOutOfBoundsException e) {
            printNonExistentTaskMessage();
        } catch (NumberFormatException e) {
            printNotANumberMessage();
        }
    }

    /**
     * Prints task that was deleted
     *
     * @param task Task that was deleted.
     */
    public void printDeletedMessage(Task task) {
        System.out.println(" Alright, I've deleted this task:\n   " + task.toString() + "\n"
                + " Now you have " + tasks.size() + " tasks in your list.");
    }

    /**
     * Checks for valid input for Deadline task.
     *
     * @param input User input for Deadline task description and time.
     * @throws DescriptionFieldEmptyException If description field of task is empty.
     * @throws TimeFieldEmptyException If time field of task is empty.
     * @throws MultipleTimeFieldsException If there are multiple time fields for task.
     */
    public void checkForValidDeadlineInput(String[] input) throws DescriptionFieldEmptyException,
            TimeFieldEmptyException,
            MultipleTimeFieldsException {
        if (input[0].trim().equals("")) {
            throw new DescriptionFieldEmptyException();
        } else if (input[1].contains("/by")) {
            throw new MultipleTimeFieldsException();
        } else if (input[1].trim().equals("")) {
            throw new TimeFieldEmptyException();
        }
    }

    /**
     * Checks for valid input for Event task.
     *
     * @param input User input for Event task description and time.
     * @throws DescriptionFieldEmptyException If description field of task is empty.
     * @throws TimeFieldEmptyException If time field of task is empty.
     * @throws MultipleTimeFieldsException If there are multiple time field for task.
     */
    public void checkForValidEventInput(String[] input) throws DescriptionFieldEmptyException,
            TimeFieldEmptyException,
            MultipleTimeFieldsException {
        if (input[0].trim().equals("")) {
            throw new DescriptionFieldEmptyException();
        } else if (input[1].contains("/at")) {
            throw new MultipleTimeFieldsException();
        } else if (input[1].trim().equals("")) {
            throw new TimeFieldEmptyException();
        }
    }

    public String checkForValidDateAndTime(String date, String time) throws DateTimeParseException {
        LocalDate taskDate = LocalDate.parse(date);
        String formattedDate = taskDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        LocalTime taskTime = LocalTime.parse(time);
        String formattedTime = taskTime.format(DateTimeFormatter.ofPattern("hh:mm a"));

        String formattedDateAndTime = formattedDate + ", " + formattedTime;
        return formattedDateAndTime;
    }

    public void printDescriptionFieldEmptyMessage() {
        System.out.println(" ERROR: the description field of a task cannot be empty :(");
    }

    public void printTimeFieldEmptyMessage() {
        System.out.println(" ERROR: the time field of a task cannot be empty :(");
    }

    public void printMissingFieldsMessage() {
        System.out.println(" ERROR: make sure that you've input a description, day and time field!");
    }

    public void printTooManyTimeFieldsMessage() {
        System.out.println(" ERROR: too many timings, try again with just one!");
    }

    public void printNonExistentTaskMessage() {
        System.out.println(" ERROR: this task number doesn't exist!");
    }

    public void printNotANumberMessage() {
        System.out.println(" ERROR: this is not a task number!");
    }

    public void printInvalidDateAndTimeFormat() {
        System.out.println(" ERROR: Invalid date and time format :( Try again!");
    }
}
