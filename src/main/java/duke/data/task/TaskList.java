package duke.data.task;

import duke.data.exceptions.DukeException;
import duke.storage.Storage;
import duke.ui.TextUI;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static duke.common.Utils.OUTPUT_DATE_FORMAT;
import static duke.common.Messages.LIST_TASK_MESSAGE;
import static duke.common.Messages.TASK_ADDED_MESSAGE;
import static duke.common.Messages.TASK_REMOVED_MESSAGE;
import static duke.common.Messages.TASK_MARK_AS_DONE_MESSAGE;
import static duke.common.Messages.TASK_MATCH_FOUND_MESSAGE;
import static duke.common.Messages.ERROR_WRITE_TO_FILE_MESSAGE;
import static duke.common.Messages.ERROR_INVALID_TASK_NUMBER_MESSAGE;
import static duke.common.Messages.ERROR_TASK_NO_MATCH_MESSAGE;
import static duke.common.Messages.DOUBLE_SPACE_PREFIX_STRING_FORMAT;
import static duke.common.Messages.TASK_TOTAL_TASKS_STRING_FORMAT;
import static duke.common.Messages.FOUND_DATE_TASK_STRING_FORMAT;

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

    private static void printTaskArray(ArrayList<Task> tasks, TextUI ui) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            ui.printStatement(String.format("%d.%s", i + 1, task));
        }
    }

    /**
     * Prints all tasks (tasks are numbered based on addition).
     */
    public void printAllTasks(TextUI ui) {
        ui.printHorizontalLine();
        ui.printStatement(LIST_TASK_MESSAGE);
        printTaskArray(tasks, ui);
        ui.printHorizontalLine();
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

    private void validateTaskNumber(int taskNumber) throws DukeException {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            // Prevents the throwing of IndexOutOfBoundsException in the caller
            throw new DukeException(ERROR_INVALID_TASK_NUMBER_MESSAGE);
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

    private void printMatchedDateTasks(ArrayList<Task> deadlines, ArrayList<Task> events,
                                       TextUI ui, String date) {
        ui.printHorizontalLine();
        ui.printStatement(String.format(FOUND_DATE_TASK_STRING_FORMAT,
                deadlines.size(), events.size(), date));
        if (deadlines.size() > 0) {
            ui.printStatement("Deadline(s):");
            printTaskArray(deadlines, ui);
        }
        if (events.size() > 0) {
            ui.printStatement("Events(s):");
            printTaskArray(events, ui);
        }
        ui.printHorizontalLine();
    }

    public void printTasksByDate(LocalDateTime dateTime, TextUI ui) {
        ArrayList<Task> deadlines = new ArrayList<>();
        ArrayList<Task> events = new ArrayList<>();
        for (Task t : tasks) {
            if (t instanceof TaskWithDateTime) {
                TaskWithDateTime dateTask = (TaskWithDateTime) t;
                copyIfDateMatch(deadlines, events, dateTask, dateTime);
            }
        }
        printMatchedDateTasks(deadlines, events, ui, dateTime.format(OUTPUT_DATE_FORMAT));
    }

    private void copyIfDateMatch(ArrayList<Task> deadlines, ArrayList<Task> events,
                                 TaskWithDateTime dateTask, LocalDateTime dateTime) {
        boolean hasDate = (dateTask.hasDateTime()) && (dateTask.getDateTime() != null);
        boolean dateMatched = hasDate && dateTask.getDateTime()
                                                 .toLocalDate()
                                                 .isEqual(dateTime.toLocalDate());
        if (dateMatched) {
            if (dateTask instanceof Deadline) {
                deadlines.add(dateTask);
            } else {
                events.add(dateTask);
            }
        }
    }

    public void findKeyword(String keyword, TextUI ui) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword)) {
                matches.add(t);
            } else if (!(t instanceof Todo)) {
                addIfKeywordMatched(keyword, t, matches);
            }
        }
        printMatchedTasks(matches, ui);
    }

    private void printMatchedTasks(ArrayList<Task> matches, TextUI ui) {
        if (matches.size() > 0) {
            ui.printHorizontalLine();
            ui.printStatement(TASK_MATCH_FOUND_MESSAGE);
            printTaskArray(matches, ui);
            ui.printHorizontalLine();
        } else {
            ui.printStatements(ERROR_TASK_NO_MATCH_MESSAGE);
        }

    }

    private void addIfKeywordMatched(String keyword, Task task, ArrayList<Task> matches) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            if (deadline.getBy().toLowerCase().contains(keyword)) {
                matches.add(task);
            }
        } else if (task instanceof Event) {
            Event event = (Event) task;
            if (event.getAt().toLowerCase().contains(keyword)) {
                matches.add(task);
            }
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }
}
