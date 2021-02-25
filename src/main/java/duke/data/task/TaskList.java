package duke.data.task;

import duke.data.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.TextUI;

import java.io.IOException;
import java.util.ArrayList;

import static duke.common.Messages.LIST_TASK_MESSAGE;
import static duke.common.Messages.TASK_ADDED_MESSAGE;
import static duke.common.Messages.TASK_REMOVED_MESSAGE;
import static duke.common.Messages.TASK_MARK_AS_DONE_MESSAGE;
import static duke.common.Messages.ERROR_WRITE_TO_FILE_MESSAGE;
import static duke.common.Messages.ERROR_INVALID_TASK_NUMBER_MESSAGE;
import static duke.common.Messages.DOUBLE_SPACE_PREFIX_STRING_FORMAT;
import static duke.common.Messages.TASK_TOTAL_TASKS_STRING_FORMAT;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Records the given task object into the task ArrayList.
     *
     * @param task task object to be recorded
     */
    public void recordTask(Task task, TextUI ui, Storage storage) {
        tasks.add(task);
        ui.printStatements(TASK_ADDED_MESSAGE,
                String.format(DOUBLE_SPACE_PREFIX_STRING_FORMAT, task),
                String.format(TASK_TOTAL_TASKS_STRING_FORMAT, tasks.size()));
        try {
            storage.writeTasksToFile(tasks);
        } catch (IOException e) {
            ui.printError(ERROR_WRITE_TO_FILE_MESSAGE);
        }
    }

    /**
     * Prints all tasks (tasks are numbered based on addition).
     */
    public void printAllTasks(TextUI ui) {
        ui.printStatement(LIST_TASK_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            ui.printStatement(String.format("%d.%s", i + 1, task));
        }
    }

    /**
     * Marks a task as done based on the order of the list.
     *
     * @param taskNumber
     * @param ui
     * @param storage
     * @see #validateTaskNumber(int)
     */
    public void markTaskDone(int taskNumber, TextUI ui, Storage storage) {
        try {
            validateTaskNumber(taskNumber);
            int taskIndex = taskNumber - 1;
            tasks.get(taskIndex).setDone(true);
            Task task = tasks.get(taskIndex);
            ui.printStatements(TASK_MARK_AS_DONE_MESSAGE,
                    String.format(DOUBLE_SPACE_PREFIX_STRING_FORMAT, task));
            storage.writeTasksToFile(tasks);
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        } catch (IOException e) {
            ui.printError(ERROR_WRITE_TO_FILE_MESSAGE);
        }
    }

    public void deleteTask(int taskNumber, TextUI ui, Storage storage) {
        try {
            validateTaskNumber(taskNumber);
            int taskIndex = taskNumber - 1;
            Task task = tasks.remove(taskIndex);
            ui.printStatements(TASK_REMOVED_MESSAGE,
                    String.format(DOUBLE_SPACE_PREFIX_STRING_FORMAT, task),
                    String.format(TASK_TOTAL_TASKS_STRING_FORMAT, tasks.size()));
            storage.writeTasksToFile(tasks);
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        } catch (IOException e) {
            ui.printError(ERROR_WRITE_TO_FILE_MESSAGE);
        }
    }

    private void validateTaskNumber(int taskNumber) throws DukeException {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            // Prevents the throwing of IndexOutOfBoundsException in the caller
            throw new DukeException(ERROR_INVALID_TASK_NUMBER_MESSAGE);
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }
}
