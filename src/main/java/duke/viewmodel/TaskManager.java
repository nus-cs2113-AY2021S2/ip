package duke.viewmodel;

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
     * Checks if task is present in storage by index.
     * @param index The task index.
     * @return True if task is present in storage.
     */
    public boolean isTaskPresent(int index) {
        return storage.containsKey(index);
    }

    /**
     * Marks the tasks as completed by their given task numbers.
     * @param indexes Index numbers of tasks.
     * @return Tasks that were completed.
     * @throws DukeException When no indexes are provided.
     */
    public List<String> completeTasks(List<Integer> indexes) throws DukeException {
        if (indexes.isEmpty()) {
            throw new DukeException(Constants.EMPTY_TASK_COMPLETE_LIST);
        }
        List<String> messages = new ArrayList<String>();
        messages.add(Constants.DONE_MESSAGE);
        for (int index : indexes) {
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
                messages.add(completedTask.getMessage());
                storage.put(index, completedTask);
            }
        }
        messages.add(String.format("Tasks left: %d",getIncompleteTasksCount()));
        return messages;
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
}