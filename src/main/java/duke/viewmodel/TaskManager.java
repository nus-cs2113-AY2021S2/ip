package duke.viewmodel;

import com.sun.tools.internal.jxc.ap.Const;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import duke.model.Deadline;
import duke.model.Event;
import duke.model.Task;
import duke.model.Todo;

public class TaskManager {

    private static TaskManager instance = null;
    private static HashMap<Integer, Task> storage;
    private static TaskGenerator taskGenerator;
    
    private TaskManager() {
        storage = new HashMap<Integer,Task>();
        taskGenerator = new TaskGenerator();
    }

    /**
     * New TaskManager instance. Handles all tasks.
     * @return The part of Duke that handles tasks.
     */
    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    /**
     * Adds a new Task to Duke's storage.
     * @param taskType One of Todo, Deadline, Event tasks.
     * @param message Description of the task sent by user.
     * @return An undone task as described by the user.
     * @throws DukeException When a task is missing a name or deadline/event.
     */
    public List<String> addTask(String taskType, String message) throws DukeException {
        List<String> messages = new ArrayList<String>();
        Task task;
        int taskIndex = storage.size() + 1;
        switch (taskType) {
        case Constants.TODO:
            task = taskGenerator.createTodo(message, taskIndex);
            break;
        case Constants.DEADLINE:
            task = taskGenerator.createDeadline(message, taskIndex);
            break;
        case Constants.EVENT:
            task = taskGenerator.createEvent(message, taskIndex);
            break;
        default:
            throw new DukeException(Constants.INVALID_COMMAND);
        }
        storage.put(taskIndex, task);
        messages.add(Constants.ADDED_MESSAGE);
        messages.add(task.getMessage());
        messages.add(String.format("Tasks left: %d",getIncompleteTasksCount()));
        return messages;
    }

    /**
     * Fetches all previously mentioned messages.
     * @return All tasks in storage.
     * @throws DukeException When the storage is empty.
     */
    public List<String> fetchTasks() throws DukeException {
        List<String> messages = new ArrayList<String>();
        for (int index = 0; index < storage.size(); index++) {
            Task task = storage.get(index + 1);
            messages.add(task.toString());
        }
        if (messages.isEmpty()) {
            throw new DukeException(Constants.EMPTY_TASK_LIST);
        }
        return messages;
    }

    /**
     * Marks the task as completed by the given task index.
     * @param stringIndex Index number of task.
     * @return Messages to notify user that task has been completed.
     * @throws DukeException When task does not exist in storage.
     */
    public List<String> completeTask(String stringIndex) throws DukeException {
        try {
            List<String> messages = new ArrayList<String>();
            int index = Integer.parseInt(stringIndex);
            Task task = storage.get(index);
            Task completedTask = null;
            if (task instanceof Todo) {
                completedTask = new Todo(index, task.getDescription(), true);
            }
            if (task instanceof Deadline) {
                completedTask = new Deadline(index, task.getDescription(), true,
                        ((Deadline) task).getDeadline());
            }
            if (task instanceof Event) {
                completedTask = new Event(index, task.getDescription(), true,
                        ((Event) task).getEvent());
            }
            if (completedTask != null) {
                storage.put(index, completedTask);
                messages.add(Constants.DONE_MESSAGE);
                messages.add(completedTask.getMessage());
                messages.add(String.format("Tasks left: %d",getIncompleteTasksCount()));
                return messages;
            } else {
                throw new DukeException(Constants.NO_TASK_FOUND);
            }
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException(Constants.INDEX_FORMAT_ERROR);
        }
    }

    /**
     * Get number of undone tasks in storage.
     * @return Number of tasks to be completed in storage.
     */
    public int getIncompleteTasksCount() {
        int count = 0;
        for (Task task : storage.values()) {
            if (!task.isTaskDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Removes a task from storage by their task index number.
     * @param stringIndex Index number of the task.
     * @return Messages to notify user that task has been deleted.
     * @throws DukeException When task does not exist in storage.
     */
    public List<String> deleteTask(String stringIndex) throws DukeException {
        try {
            int index = Integer.parseInt(stringIndex);
            List<String> messages = new ArrayList<String>();
            Task removedTask = storage.remove(index);
            if (removedTask != null) {
                messages.add(Constants.DELETED_MESSAGE);
                messages.add(removedTask.getMessage());
                messages.add(String.format("Tasks left: %d",getIncompleteTasksCount()));
                return messages;
            } else {
                throw new DukeException(Constants.NO_TASK_FOUND);
            }
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException(Constants.INDEX_FORMAT_ERROR);
        }
    }
}