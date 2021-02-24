package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.TodoCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class DukeTaskList {
    private static final int MINIMUM_NUM_ARGS_IMPORT = 3;
    private static final int NUM_ARGS_TODO = 2;
    private static final int NUM_ARGS_DEADLINE = 3;
    private static final int NUM_ARGS_EVENT = 3;

    private static final int TASK_TYPE = 0;
    private static final int DESCRIPTION = 1;
    private static final int DATE = 2;
    private static final int DONE = 0;

    private ArrayList<Task> tasks;

    public DukeTaskList() {
        this.tasks = new ArrayList<Task>();
    }

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

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void clearTasks() {
        tasks.clear();
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

    public ArrayList<String> findTasks(String searchExpression) {
        ArrayList<String> matchingTasks = (ArrayList<String>) tasks.stream()
                .filter((task) -> task.getDescription().contains(searchExpression))
                .map((task) -> task.toString())
                .collect(toList());
        return matchingTasks;
    }
}
