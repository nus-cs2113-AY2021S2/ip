package duke.taskActions;

import java.util.ArrayList;
import java.util.List;

import duke.common.Strings;
import duke.data.exception.DukeException;
import duke.data.Task;
import duke.data.exception.DukeExceptionKey;

public class AddTask {

    /**
     * Adds a new Task to Duke's storage.
     * @param taskType One of Todo, Deadline, Event tasks.
     * @param message Description of the task sent by user.
     * @return An undone task as described by the user.
     * @throws DukeException When a task is missing a name or deadline/event.
     */
    public static List<String> addTask(String taskType, String message) throws DukeException {
        List<Task> storage = TaskList.getStorage();
        List<String> messages = new ArrayList<>();
        Task task;
        switch (taskType) {
            case Strings.TODO:
                task = TaskGenerator.createTodo(message);
                break;
            case Strings.DEADLINE:
                task = TaskGenerator.createDeadline(message);
                break;
            case Strings.EVENT:
                task = TaskGenerator.createEvent(message);
                break;
            default:
                throw new DukeException(DukeExceptionKey.INVALID_COMMAND);
        }
        storage.add(task);
        messages.add(Strings.ADDED_MESSAGE);
        messages.add(task.getMessage());
        messages.add(String.format("Tasks left: %d",
            TaskList.getIncompleteTasksCount()));
        return messages;
    }
}
