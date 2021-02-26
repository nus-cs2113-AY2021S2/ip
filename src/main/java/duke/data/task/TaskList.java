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
     * Writes task object to file.
     *
     * @param task task object to be recorded
     * @param ui the TextUI object that that engages user input and program output.
     * @param storage the Storage object that writes/retrieves to/from file.
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
     *
     * @param ui the TextUI object that that engages user input and program output.
     * @see #printTaskArray(ArrayList, TextUI)
     */
    public void printAllTasks(TextUI ui) {
        ui.printHorizontalLine();
        ui.printStatement(LIST_TASK_MESSAGE);
        printTaskArray(tasks, ui);
        ui.printHorizontalLine();
    }

    /**
     * Marks a task as done based on the order of the list.
     * Fails if taskNumber is invalid.
     * Writes changes to file.
     *
     * @param taskNumber an integer representing a task number in the Task ArrayList.
     * @param ui the TextUI object that that engages user input and program output.
     * @param storage the Storage object that writes/retrieves to/from file.
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

    /**
     * Deletes a task from the Task ArrayList, based on the order of the list.
     * Fails if taskNumber is invalid.
     * Writes changes to file.
     *
     * @param taskNumber an integer representing a task number in the Task ArrayList.
     * @param ui the TextUI object that that engages user input and program output.
     * @param storage the Storage object that writes/retrieves to/from file.
     * @see #validateTaskNumber(int)
     */
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
            // Prevents the throwing of IndexOutOfBoundsException in the caller.
            throw new DukeException(ERROR_INVALID_TASK_NUMBER_MESSAGE);
        }
    }

    /**
     * Prints all the task with datetime (Deadline and Events) which have the
     * same date if compared. Only the Deadline/Events with proper datetime
     * input will be printed.
     *
     * @param dateTime a LocalDateTime object to compare with.
     * @param ui the TextUI object that that engages user input and program output.
     * @see #copyIfDateMatch(ArrayList, ArrayList, TaskWithDateTime, LocalDateTime)
     * @see #printMatchedDateTasks(ArrayList, ArrayList, TextUI, String)
     */
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

    /**
     * Copies the task object into the relevant array if the date matches with
     * the given date from LocalDateTime object dateTime.
     *
     * @param deadlines a Task ArrayList of Deadline objects.
     * @param events a Task ArrayList of Event objects.
     * @param dateTask a TaskWithDateTime object to be matched with.
     * @param dateTime a LocalDateTime object to match with dateTask's date.
     */
    private void copyIfDateMatch(ArrayList<Task> deadlines, ArrayList<Task> events,
                                 TaskWithDateTime dateTask, LocalDateTime dateTime) {
        boolean hasDate = (dateTask.hasDateTime()) && (dateTask.getDateTime() != null);
        boolean dateMatched = hasDate && dateTask.getDateTime()
                                                 .toLocalDate()
                                                 .isEqual(dateTime.toLocalDate());
        if (dateMatched) {
            if (dateTask instanceof Deadline) {
                deadlines.add(dateTask);
            } else if (dateTask instanceof Event) {
                events.add(dateTask);
            }
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

    /**
     * Searches for tasks that matches the the keyword (non case-sensitive).
     * Prints out the tasks that has a match.
     *
     * @param keyword a string of a keyword to match with.
     * @param ui the TextUI object that that engages user input and program output.
     * @see #addIfKeywordMatched(String, Task, ArrayList)
     * @see #printMatchedTasks(ArrayList, TextUI)
     */
    public void findKeyword(String keyword, TextUI ui) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword)) {
                matches.add(t);
            } else if (t instanceof TaskWithDateTime) {
                addIfKeywordMatched(keyword, t, matches);
            }
        }
        printMatchedTasks(matches, ui);
    }

    /**
     * Searches for Deadline/Event that matches the the keyword (non case-sensitive).
     * Checks the "by" of the Deadline and "at" of the Event for any match.
     *
     * @param keyword a string of a keyword to match with.
     * @param task the Task object to match with the keyword.
     * @param matches a Task ArrayList of matched tasks.
     */
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

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }
}
