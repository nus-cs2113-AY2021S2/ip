package duke.task;

import duke.exception.*;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Creates tasks according to user data from files.
     *
     * @param dataTasks List of tasks from user data.txt file.
     * @return Data.txt tasks converted to tasks in Duke.
     */
    public TaskList(ArrayList<String> dataTasks) {
        for (String task : dataTasks) {
            tasks.add(Task.textToTask(task));
        }
    }

    /**
     * Creates new tasks if no existing data in files.
     *
     * @return Empty task list created for Duke.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }


    /**
     * Prints out all tasks in TaskList
     *
     * @return Prints the list of Tasks in Duke.
     * @throws NoTaskException If list is empty.
     */
    public static void listTask() throws DukeException {
        if (tasks.isEmpty()) {
            throw new NoTaskException();
        } else {

            Ui.printDivider();
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < tasks.size(); ++i) {
                Ui.printTask(tasks, i);
            }
            Ui.printDivider();
        }
    }


    /**
     * Prints message when task is marked as done.
     *
     * @param input Task selected to be marked as done.
     * @throws InvalidTaskNumberException If task number input is out of range.
     */
    public static void markTaskDone(String input) throws DukeException {
        String taskNumberString;
        try {
            taskNumberString = input.split(" ")[1];
            int taskNumber = Integer.parseInt(taskNumberString);
            boolean taskNumberIsLessThanOne = (taskNumber < 1);
            boolean taskNumberIsMoreThanListSize = (taskNumber > tasks.size());
            if (taskNumberIsLessThanOne || taskNumberIsMoreThanListSize) throw new InvalidTaskNumberException();
            Task currentTask = tasks.get(taskNumber - 1);
            currentTask.markAsDone();
            Storage.saveData(tasks);

            Ui.printDivider();
            Ui.printMarkedTaskMessage(currentTask);
            Ui.printDivider();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }


    /**
     * Adds a To-Do task to tasks
     *
     * @param input User input for To-Do task description, date and time.
     * @throws InvalidTaskDescriptionException If any of the fields are empty.
     */
    public static void addToDo(String input) throws DukeException {
        String taskInfo;
        try {
            taskInfo = input.split("todo")[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }

        Task currentTask = new ToDo(taskInfo);
        tasks.add(currentTask);
        Storage.saveData(tasks);

        Ui.printDivider();
        Ui.printAddedTaskMessage(currentTask);
        Ui.printRemainingTaskMessage(tasks);
        Ui.printDivider();

    }

    /**
     * Adds a Deadline task to tasks
     *
     * @param input User input for Deadline task description and date.
     * @throws InvalidTaskDescriptionException If any of the fields are empty.
     * @throws MultipleTimeFieldException      If "/by" is detected more than once.
     */
    public static void addDeadline(String input) throws DukeException {
        String taskInfo;
        try {
            taskInfo = input.split("deadline")[1].trim();
            String[] splitTaskInfo = taskInfo.split("/by", 2);
            checkTaskInfo(splitTaskInfo, "/by");
            String taskDescription = splitTaskInfo[0].trim();
            String taskDateTime = splitTaskInfo[1].trim();
            String formattedDateAndTime = formatDateAndTime(taskDateTime);
            Task currentTask = new Deadline(taskDescription, formattedDateAndTime);
            tasks.add(currentTask);
            Storage.saveData(tasks);

            Ui.printDivider();
            Ui.printAddedTaskMessage(currentTask);
            Ui.printRemainingTaskMessage(tasks);
            Ui.printDivider();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }
    }

    /**
     * Adds an Event task to tasks.
     *
     * @param input User input for Event task description and date.
     * @throws InvalidTaskDescriptionException If any of the fields are empty.
     * @throws MultipleTimeFieldException      If "/at" is detected more than once.
     */
    public static void addEvent(String input) throws DukeException {
        String taskInfo;
        try {
            taskInfo = input.split("event")[1];
            String[] splitTaskInfo = taskInfo.split("/at", 2);
            checkTaskInfo(splitTaskInfo, "/at");
            String taskDescription = splitTaskInfo[0].trim();
            String taskDateTime = splitTaskInfo[1].trim();
            String formattedDateAndTime = formatDateAndTime(taskDateTime);
            Task currentTask = new Event(taskDescription, formattedDateAndTime);
            tasks.add(currentTask);
            Storage.saveData(tasks);

            Ui.printDivider();
            Ui.printAddedTaskMessage(currentTask);
            Ui.printRemainingTaskMessage(tasks);
            Ui.printDivider();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }
    }

    /**
     * Deletes a task from Duke's list.
     *
     * @param input User input for Index of task to be deleted.
     * @throws InvalidTaskNumberException If user input index is out of range.
     */

    public static void deleteTask(String input) throws DukeException {
        String taskNumberString;
        int taskNumber;
        try {
            taskNumberString = input.split("delete")[1].trim();
            taskNumber = Integer.parseInt(taskNumberString);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }

        boolean taskNumberIsLessThanOne = (taskNumber < 1);
        boolean taskNumberIsMoreThanListSize = (taskNumber > tasks.size());
        if (taskNumberIsLessThanOne || taskNumberIsMoreThanListSize) {
            throw new InvalidTaskNumberException();
        }

        Ui.printDivider();
        System.out.print("\tRemoving task: \n\t");
        Ui.printTask(tasks, taskNumber - 1);
        tasks.remove(taskNumber - 1);
        Storage.saveData(tasks);

        System.out.print("\tSuccessfully removed!\n");
        Ui.printRemainingTaskMessage(tasks);
        Ui.printDivider();

    }

    /**
     * Finds a task according to keywords.
     *
     * @param input User input for Task task keywords
     * @throws InvalidTaskKeywordException When user does not enter any keyword
     */
    public static void findTask(String input) throws DukeException {
        String keyword;
        try {
            keyword = input.split("find")[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskKeywordException();
        }

        boolean hasTask = false;
        Ui.printDivider();
        System.out.print("\tHere are the matching tasks in your list: \n");
        for (int i = 0; i < tasks.size(); ++i) {
            if (tasks.get(i).taskDescription.contains(keyword)) {
                Ui.printTask(tasks, i);
                hasTask = true;
            }
        }
        Ui.printDivider();

        if (!hasTask) {
            Ui.printSearchWordNotFoundMessage();
        }
    }

    /**
     * Reformats the date and time input by the user to be printed in task list.
     *
     * @param taskDateTime User input for date and time.
     * @return Formatted Date and Time to be printed to user.
     * @throws InvalidTaskDateTimeException If date and time input by user do not meet required format.
     */

    public static String formatDateAndTime(String taskDateTime) throws DukeException {
        String formattedDate;
        String formattedTime;
        try {
            String inputDate = taskDateTime.split(" ")[0].trim();
            String inputTime = taskDateTime.split(" ")[1].trim();
            LocalDate taskDate = LocalDate.parse(inputDate);
            formattedDate = taskDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

            LocalTime taskTime = LocalTime.parse(inputTime);
            formattedTime = taskTime.format(DateTimeFormatter.ofPattern("hh:mma"));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskDateTimeException();
        }

        return formattedDate + ", " + formattedTime;
    }

    /**
     * @param splitTaskInfo Takes in the user input for date and time.
     * @param identifier    Represents either "/at" or "/by" depending on type of task added.
     * @throws DukeException If user input for description, date or time is invalid.
     */

    public static void checkTaskInfo(String[] splitTaskInfo, String identifier) throws DukeException {
        if (splitTaskInfo[0].isBlank()) {
            throw new InvalidTaskDescriptionException();
        } else if (splitTaskInfo[1].contains(identifier)) {
            throw new MultipleTimeFieldException();
        } else if (splitTaskInfo[1].isBlank()) {
            throw new InvalidTaskDateTimeException();
        }
    }

}