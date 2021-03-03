package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.TodoCommand;
import duke.task.Task;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 * Manages a list of the user's tasks and implements functions to manipulate the tasks.
 */
public class DukeTaskList {
    private static final int MINIMUM_NUM_ARGS_IMPORT = 3;
    private static final int TASK_TYPE = 0;
    private static final int DONE = 0;

    private ArrayList<Task> tasks;

    public DukeTaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Imports a task from a tokenized file input.
     *
     * @param taskInfo tokenized task info read from an input file
     */
    public void importTask(ArrayList<String> taskInfo) throws DukeException {
        if (taskInfo.size() < MINIMUM_NUM_ARGS_IMPORT) {
            throw new DukeException("Insufficient Arguments");
        }

        String doneString = taskInfo.get(DONE);
        boolean isDone = Boolean.parseBoolean(doneString);

        int lastIndex = taskInfo.size();
        ArrayList<String> commandTokens = new ArrayList<String>(taskInfo.subList(1, lastIndex));
        String taskType = commandTokens.get(TASK_TYPE);
        Command command = null;
        switch (taskType) {
        case "T":
            command = new TodoCommand(commandTokens, this, false, isDone);
            break;
        case "D":
            command = new DeadlineCommand(commandTokens, this, false, isDone);
            break;
        case "E":
            command = new EventCommand(commandTokens, this, false, isDone);
            break;
        default:
            throw new DukeException("Unknown task type: " + taskType);
        }
        command.execute();
    }

    /**
     * Imports all tasks from the tokenized file input.
     *
     * @param parsedTaskInfo ArrayList of ArrayLists of Strings containing tokenized task info
     */
    public void importTaskInfo(ArrayList<ArrayList<String>> parsedTaskInfo) {
        ArrayList<String> warnings = new ArrayList<String>();
        for (int i = 0; i < parsedTaskInfo.size(); i++) {
            ArrayList<String> taskInfo = parsedTaskInfo.get(i);
            try {
                importTask(taskInfo);
            } catch (DukeException dukeException) {
                warnings.add("Error importing line #" + Integer.toString(i + 1) +
                        ": " + dukeException.getMessage());
            }
        }
        if (!warnings.isEmpty()) {
            DukePrinter.printWarnings(warnings);
        }
    }

    /**
     * Add a task to the list of tasks.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes all tasks from the list of tasks
     */
    public void clearTasks() {
        tasks.clear();
    }

    /**
     * Delete a specified task from the list, based on its position.
     *
     * @param taskNumber the 1-based index of the task to be removed
     * @return the string representation of the deleted task
     */
    public String deleteTask(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("That's an invalid task number!");
        }

        /* Change from 1-based indexing to 0-based indexing */
        taskNumber = taskNumber - 1;
        Task deletedTask = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        String deletedTaskString = deletedTask.toString();
        return deletedTaskString;
    }

    /**
     * Mark a specified task from the list as done, based on its position.
     *
     * @param taskNumber the 1-based index of the task to be marked as done
     * @return the string representation of the task that was marked as done
     */
    public String markTaskAsDone(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("That's an invalid task number!");
        }

        /* Change from 1-based indexing to 0-based indexing */
        taskNumber = taskNumber - 1;
        Task doneTask = tasks.get(taskNumber);
        doneTask.markAsDone();
        String doneTaskString = doneTask.toString();
        return doneTaskString;
    }

    /**
     * Returns a ArrayList of Strings containing the user's task information in CSV format.
     *
     * @return an ArrayList of Strings containing the user's tasks
     */
    public ArrayList<String> exportCSV() {
        ArrayList<String> tasksAsCSV = new ArrayList<String>();
        for (Task task : tasks) {
            String taskCSV = task.exportAsCSV();
            tasksAsCSV.add(taskCSV);
        }
        return tasksAsCSV;
    }

    /**
     * Returns the number of tasks that the user has in their list.
     *
     * @return the total number of tasks that the user has
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Returns the String representation of all tasks that a user has in their list.
     *
     * @return an ArrayList of Strings containing all tasks that a user has
     */
    public ArrayList<String> getTasksAsString() {
        ArrayList<String> taskStrings = new ArrayList<String>();
        for (Task task : tasks) {
            String taskString = task.toString();
            taskStrings.add(taskString);
        }
        return taskStrings;
    }

    /**
     * Returns the String representation of all tasks that that match the required search criteria.
     *
     * @param searchExpression the criteria to search for
     * @return an ArrayList of Strings containing all tasks that match the search criteria
     */
    public ArrayList<String> findTasks(String searchExpression) {
        ArrayList<String> matchingTasks = (ArrayList<String>) tasks.stream()
                .filter((task) -> task.getDescription().contains(searchExpression))
                .map((task) -> task.toString())
                .collect(toList());
        return matchingTasks;
    }
}
