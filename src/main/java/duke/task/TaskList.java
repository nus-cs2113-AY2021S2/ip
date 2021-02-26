package duke.task;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private static final String DIVIDER = "\t_______________________________\n";
    public static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Checks number of tasks for printing fluency
     */
    protected static String checkSingular() {
        if (tasks.size() > 1) {
            return " tasks ";
        }
        return " task ";
    }

    /**
     * Prints out all tasks in TaskList
     */
    public static void listTask() {
        System.out.print(DIVIDER);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println("\t" + (i + 1) + "." +
                    tasks.get(i).toString());
        }
        System.out.print(DIVIDER);
    }

    /**
     * Prints message when task is marked as done.
     *
     * @param task Task that was marked as done.
     */
    public static void markTaskDone(String input) {
        //task number can be found on 5th index of input
        int taskNumber = Integer.parseInt(input.substring(5));

        //to check if tasks exists
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            DukeException.invalidTask();
            return;
        }

        Task currentTask = tasks.get(taskNumber - 1);
        currentTask.markAsDone();
        Storage.saveData();
        System.out.print(DIVIDER + "\tNice! I've marked this task as done:" +
                "\n\t" + currentTask.toString() + "\n" + DIVIDER);
    }

    /**
     * Adds a task to tasks
     *
     * @param command User input for task description.
     */
    public static void addTask(String input)
            throws ArrayIndexOutOfBoundsException {
        String[] splitInput = input.split("add");
        String taskInfo;
        try {
            taskInfo = splitInput[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            //if no input after "add" command
            DukeException.addTaskInvalidDescription();
            return;
        }
        if (taskInfo.isBlank()) {
            //if input after "add" command is only whitespace
            DukeException.addTaskInvalidDescription();
            return;
        }
        Task currentTask = new Task(taskInfo);
        tasks.add(currentTask);
        Storage.saveData();
        System.out.print(DIVIDER + "\tadded " + currentTask.toString() + "\n" + DIVIDER);
    }

    /**
     * Adds a To-Do task to tasks
     *
     * @param command User input for To-Do task description.
     */
    public static void addToDo(String input) throws ArrayIndexOutOfBoundsException {
        String[] splitInput = input.split("todo");
        String taskInfo;
        try {
            taskInfo = splitInput[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            //if no input after "to-Do" command
            DukeException.toDoInvalidDescription();
            return;
        }

        if (taskInfo.isBlank()) {
            //if input after "to-Do" command is only whitespace
            DukeException.toDoInvalidDescription();
            return;
        }

        Task currentTask = new ToDo(taskInfo);
        tasks.add(currentTask);
        Storage.saveData();
        System.out.println(DIVIDER + "\tGot it. I've added this task:\n" +
                "\t\t" + currentTask.toString());
        System.out.print("\tNow you have " + tasks.size() + checkSingular() + "in your list." + "\n" + DIVIDER);
    }

    /**
     * Adds a Deadline task to tasks
     *
     * @param command User input for Deadline task description and date.
     */

    public static void addDeadline(String input) throws ArrayIndexOutOfBoundsException {
        String[] splitInput = input.split("deadline");
        String taskInfo;
        try {
            taskInfo = splitInput[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            //if no input after "deadline" command
            DukeException.deadlineInvalidDescription();
            return;
        }

        if (taskInfo.isBlank()) {
            //if input after "deadline" command is only whitespace
            DukeException.deadlineInvalidDescription();
            return;
        }

        String[] splitTaskInfo = taskInfo.split("/by", 2);
        String taskDescription = splitTaskInfo[0].trim();
        String deadline = splitTaskInfo[1].trim();
        Task currentTask = new Deadline(taskDescription, deadline);
        tasks.add(currentTask);
        Storage.saveData();
        System.out.println(DIVIDER + "\tGot it. I've added this task:\n\t\t" +
                currentTask.toString());
        System.out.print("\tNow you have " + tasks.size() + checkSingular() + "in your list.\n" + DIVIDER);
    }

    /**
     * Adds a Event task to tasks
     *
     * @param command User input for Event task description and date.
     */
    public static void addEvent(String input) throws ArrayIndexOutOfBoundsException {
        String[] splitInput = input.split("event");
        String taskInfo;
        try {
            taskInfo = splitInput[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            //if no input after "event" command
            DukeException.eventInvalidDescription();
            return;
        }

        if (taskInfo.isBlank()) {
            //if input after "event" command is only whitespace
            DukeException.eventInvalidDescription();
            return;
        }
        String[] splitTaskInfo = taskInfo.split("/at", 2);
        String taskDescription = splitTaskInfo[0].trim();
        String timeDetails = splitTaskInfo[1].trim();
        Task currentTask = new Event(taskDescription, timeDetails);
        tasks.add(currentTask);
        Storage.saveData();
        System.out.println(DIVIDER + "\tGot it. I've added this task:\n\t\t" +
                currentTask.toString());
        System.out.print("\tNow you have " + tasks.size() + checkSingular() + "in your list.\n" + DIVIDER);

    }

    /**
     * Deletes a task
     *
     * @param command User input for Index of task to be deleted.
     */

    public static void deleteTask(String input) {
        //to add exception
        //task number can be found on 7th index of input
        int taskNumber = Integer.parseInt(input.substring(7));

        //to  check if task to delete exists
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            DukeException.invalidTask();
            return;
        }

        Task currentTask = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        Storage.saveData();
        System.out.print(DIVIDER + "\tNoted. I've removed this task:" +
                "\n\t" + currentTask.toString() + "\n"
                + DIVIDER);
        System.out.print("\tNow you have " + (tasks.size()) + checkSingular() + "in your list.\n" + DIVIDER);

    }

    /**
     * Finds a task according to keywords
     *
     * @param command User input for finTask task searchWord
     */
    public static void findTask(String input) {
        String searchWord = input.split("find")[1];
        boolean hasTask = false;
        System.out.print(DIVIDER + "\tHere are the matching tasks in your list: \n");
        for (int i = 0; i < tasks.size(); ++i) {
            if (tasks.get(i).taskDescription.contains(searchWord)) {
                System.out.println("\t" + (i + 1) + "." +
                        tasks.get(i).toString());
                hasTask = true;
            }
        }
        System.out.print(DIVIDER);

        if (!hasTask) {
            Ui.printSearchWordNotFoundMessage();
        }
    }

}
