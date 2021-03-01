package duke.task;

import duke.exception.*;
import duke.storage.Storage;
import duke.ui.Ui;


import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Creates tasks according to user data from files.
     */
    public TaskList(ArrayList<String> dataTasks) {
        for (String task : dataTasks) {
            tasks.add(Task.textToTask(task));
        }
    }

    /**
     * Creates new tasks if no existing data in files.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }


    /**
     * Prints out all tasks in TaskList
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
     * @param input Task that was marked as done.
     */
    public static void markTaskDone(String input) throws DukeException {
        String taskNumberString;
        try {
            taskNumberString = input.split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
        if (taskNumberString.isBlank()) {
            throw new InvalidTaskNumberException();
        }

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
    }


    /**
     * Adds a To-Do task to tasks
     *
     * @param input User input for To-Do task description.
     */
    public static void addToDo(String input) throws DukeException {
        String taskInfo;
        try {
            taskInfo = input.split("todo")[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }

        if (taskInfo.isBlank()) {
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
     */

    public static void addDeadline(String input) throws DukeException {
        String taskInfo;
        try {
            taskInfo = input.split("deadline")[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }

        if (taskInfo.isBlank()) {
            throw new InvalidTaskDescriptionException();
        }

        String[] splitTaskInfo = taskInfo.split("/by", 2);
        String taskDescription = splitTaskInfo[0].trim();
        String deadline = splitTaskInfo[1].trim();
        Task currentTask = new Deadline(taskDescription, deadline);
        tasks.add(currentTask);
        Storage.saveData(tasks);

        Ui.printDivider();
        Ui.printAddedTaskMessage(currentTask);
        Ui.printRemainingTaskMessage(tasks);
        Ui.printDivider();
    }

    /**
     * Adds a Event task to tasks
     *
     * @param input User input for Event task description and date.
     */
    public static void addEvent(String input) throws DukeException {
        String taskInfo;
        try {
            taskInfo = input.split("event")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }
        if (taskInfo.isBlank()) {
            throw new InvalidTaskDescriptionException();
        }

        String[] splitTaskInfo = taskInfo.split("/at", 2);
        String taskDescription = splitTaskInfo[0].trim();
        String timeDetails = splitTaskInfo[1].trim();
        Task currentTask = new Event(taskDescription, timeDetails);
        tasks.add(currentTask);
        Storage.saveData(tasks);

        Ui.printDivider();
        Ui.printAddedTaskMessage(currentTask);
        Ui.printRemainingTaskMessage(tasks);
        Ui.printDivider();

    }

    /**
     * Deletes a task
     *
     * @param input User input for Index of task to be deleted.
     */

    public static void deleteTask(String input) throws DukeException {
        String taskNumberString;
        try {
            taskNumberString = input.split("delete")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
        if (taskNumberString.isBlank()) {
            throw new InvalidTaskNumberException();
        }

        int taskNumber = Integer.parseInt(taskNumberString);
        boolean taskNumberIsLessThanOne = (taskNumber < 1);
        boolean taskNumberIsMoreThanListSize = (taskNumber > tasks.size());
        if (taskNumberIsLessThanOne || taskNumberIsMoreThanListSize) {
            throw new InvalidTaskNumberException();
        }

        Ui.printDivider();
        System.out.print("\tRemoving task: \n");
        Ui.printTask(tasks, taskNumber - 1);
        tasks.remove(taskNumber - 1);
        Storage.saveData(tasks);

        System.out.print("\tSuccessfully removed!\n");
        Ui.printRemainingTaskMessage(tasks);
        Ui.printDivider();

    }

    /**
     * Finds a task according to keywords
     *
     * @param input User input for finTask task searchWord
     */
    public static void findTask(String input) throws DukeException {
        String keyword;
        try {
            keyword = input.split("find")[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskKeywordException();
        }
        if (keyword.isBlank()) {
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

}
