import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class TaskManager {

    private static TaskManager instance = null;
    private static HashMap<Integer,Task> storage;
    
    private TaskManager() {
        storage = new HashMap<Integer,Task>();
    }

    /**
     * New TaskManager instance. Handles all tasks.
     */
    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    /**
     * Saves current task.
     */
    public static List<String> addTask(String taskType, String message) {
        List<String> messages = new ArrayList<String>();
        Task task;
        int taskIndex = storage.size() + 1;
        switch (taskType) {
            case Constants.TODO:
                task = new Todo(taskIndex, message, false);
                break;
            case Constants.DEADLINE:
                String[] deadlineTask = message.split(" /by ");
                String details = deadlineTask[0].trim();
                if (deadlineTask.length <= 1 || details.isEmpty()) {
                    messages.add(Constants.deadlineErrorMessage);
                    return messages;
                }
                String time = deadlineTask[1].trim();
                task = new Deadline(taskIndex, details, false, time);
                break;
            case Constants.EVENT:
                String[] eventTask = message.split(" /at ");
                details = eventTask[0].trim();
                if (eventTask.length <= 1 || details.isEmpty()) {
                    messages.add(Constants.eventErrorMessage);
                    return messages;
                }
                time = eventTask[1].trim();
                task = new Event(taskIndex, details, false, time);
                break;
            default:
                messages.add(Constants.todoErrorMessage);
                return messages;
        }
        storage.put(taskIndex, task);
        messages.add(Constants.addedMessage);
        messages.add(task.getMessage());
        messages.add(String.format("Tasks left: %d",getIncompleteTasksCount()));
        return messages;
    }

    /**
     * Fetch all previously mentioned messages.
     */
    public static List<String> fetchTasks() {
        List<String> messages = new ArrayList<String>();
        for (int index = 0; index < storage.size(); index++) {
            Task task = storage.get(index + 1);
            messages.add(task.toString());
        }
        return messages;
    }

    /**
     * Checks if task is present in storage by index.
     */
    public static boolean isTaskPresent(int index) {
        return storage.containsKey(index);
    }

    /**
     * Marks the tasks as completed by their given task numbers.
     */
    public static List<String> completeTasks(List<Integer> indexes) {
        List<String> messages = new ArrayList<String>();
        messages.add(Constants.doneMessage);
        for (int index : indexes) {
            Task task = storage.get(index);
            messages.add(String.format(" [X] %s\n", task.getDescription()));
            Todo completedTask = new Todo(index, task.getDescription(), true);
            storage.put(index, completedTask);
        }
        messages.add(String.format("Tasks left: %d",getIncompleteTasksCount()));
        return messages;
    }

    /**
     * Get number of undone tasks in storage.
     */
    public static int getIncompleteTasksCount() {
        int count = 0;
        for (Task task : storage.values()) {
            if (!task.isTaskDone()) {
                count++;
            }
        }
        return count;
    }
}