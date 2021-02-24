package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class DukeTaskList {
    private static final int MINIMUM_NUM_ARGS_IMPORT = 3;
    private static final int NUM_ARGS_TODO = 2;
    private static final int NUM_ARGS_DEADLINE = 3;
    private static final int NUM_ARGS_EVENT = 3;
    private static final int NUM_ARGS_DELETE = 2;
    private static final int NUM_ARGS_DONE = 2;

    private static final int TASK_TYPE = 0;
    private static final int DESCRIPTION = 1;
    private static final int DATE = 2;
    private static final int TASK_NUMBER = 1;
    private static final int DONE = 0;

    private ArrayList<Task> tasks;

    public DukeTaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addTask(ArrayList<String> taskInfo) throws DukeException {
        if (taskInfo.size() < MINIMUM_NUM_ARGS_IMPORT) {
            throw new DukeException("Insufficient Arguments");
        }

        int lastIndex = taskInfo.size();
        ArrayList<String> commandTokens = new ArrayList<String>(taskInfo.subList(1, lastIndex));
        String taskType = commandTokens.get(TASK_TYPE);
        Task task = null;
        switch (taskType) {
        case "T":
            task = addTodo(commandTokens);
            break;
        case "D":
            task = addDeadline(commandTokens);
            break;
        case "E":
            task = addEvent(commandTokens);
            break;
        default:
            throw new DukeException("Unknown task type: " + taskType);
        }

        String doneString = taskInfo.get(DONE);
        boolean isDone = Boolean.parseBoolean(doneString);
        if (isDone) {
            task.markAsDone();
        }
    }

    public void importTaskInfo(ArrayList<ArrayList<String>> parsedTaskInfo) {
        ArrayList<String> warnings = new ArrayList<String>();
        for (int i = 0; i < parsedTaskInfo.size(); i++) {
            ArrayList<String> taskInfo = parsedTaskInfo.get(i);
            try {
                addTask(taskInfo);
            } catch (DukeException dukeException) {
                warnings.add("Error importing line #" + Integer.toString(i + 1) +
                        ": " + dukeException.getMessage());
            }
        }
    }

    public void clearTasks() {
        tasks.clear();
    }

    public Todo addTodo(ArrayList<String> commandTokens) throws DukeException {
        if (commandTokens.size() != NUM_ARGS_TODO) {
            throw new DukeException("Please give me more details about the task!");
        }
        String description = commandTokens.get(DESCRIPTION);
        if (description.isEmpty()) {
            throw new DukeException("The description of a task can't be empty. Please try again.");
        }
        Todo todo = new Todo(description);
        tasks.add(todo);
        return todo;
    }

    public Deadline addDeadline(ArrayList<String> commandTokens) throws DukeException {
        if (commandTokens.size() != NUM_ARGS_DEADLINE) {
            throw new DukeException("Please give me more details about the task!");
        }
        String description = commandTokens.get(DESCRIPTION);
        if (description.isEmpty()) {
            throw new DukeException("The description of a task can't be empty. Please try again.");
        }
        String date = commandTokens.get(DATE);
        if (date.isEmpty()) {
            throw new DukeException("Please specify a deadline for the task.");
        }
        Deadline deadline = new Deadline(description, date);
        tasks.add(deadline);
        return deadline;
    }

    public Event addEvent(ArrayList<String> commandTokens) throws DukeException {
        if (commandTokens.size() != NUM_ARGS_EVENT) {
            throw new DukeException("Please give me more details about the task!");
        }
        String description = commandTokens.get(DESCRIPTION);
        if (description.isEmpty()) {
            throw new DukeException("The description of a task can't be empty. Please try again.");
        }
        String date = commandTokens.get(DATE);
        if (date.isEmpty()) {
            throw new DukeException("Please specify a date for the event.");
        }
        Event event = new Event(description, date);
        tasks.add(event);
        return event;
    }

    public void deleteTask(ArrayList<String> commandTokens) throws DukeException, NumberFormatException {
        if (commandTokens.size() != NUM_ARGS_DELETE) {
            throw new DukeException("Insufficient number of arguments");
        }
        String taskNumberString = commandTokens.get(TASK_NUMBER);
        int taskNumber = Integer.parseInt(taskNumberString);
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new DukeException("That's an invalid task number!");
        }
        /* Change from 1-based indexing to 0-based indexing */
        taskNumber = taskNumber - 1;
        Task removedTask = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        DukePrinter.printTaskDeleted(removedTask.toString(), tasks.size());
    }

    public Task deleteTask(int taskNumber) {
        /* Change from 1-based indexing to 0-based indexing */
        taskNumber = taskNumber - 1;
        Task deletedTask = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        return deletedTask;
    }

    public Task markTaskAsDone(int taskNumber) {
        /* Change from 1-based indexing to 0-based indexing */
        taskNumber = taskNumber - 1;
        Task doneTask = tasks.get(taskNumber);
        doneTask.markAsDone();
        return doneTask;
    }

    public ArrayList<String> exportCSV() {
        ArrayList<String> tasksAsCSV = new ArrayList<String>();
        for (Task task : tasks) {
            String taskCSV = task.exportAsCSV();
            tasksAsCSV.add(taskCSV);
        }
        return tasksAsCSV;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public ArrayList<String> getTasksAsString() {
        ArrayList<String> taskStrings = new ArrayList<String>();
        for (Task task : tasks) {
            String taskString = task.toString();
            taskStrings.add(taskString);
        }
        return taskStrings;
    }
}
