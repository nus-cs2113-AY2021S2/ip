package duke.util.actions;

import java.util.ArrayList;
import java.util.List;

import duke.common.Commands;
import duke.common.Messages;
import duke.data.exception.DukeException;
import duke.data.Task;
import duke.data.exception.DukeExceptionKey;
import duke.util.TaskGenerator;
import duke.util.TaskList;

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
            case Commands.TODO:
                task = TaskGenerator.createTodo(message);
                break;
            case Commands.DEADLINE:
                task = TaskGenerator.createDeadline(message);
                break;
            case Commands.EVENT:
                task = TaskGenerator.createEvent(message);
                break;
            default:
                throw new DukeException(DukeExceptionKey.INVALID_COMMAND);
        }
        storage.add(task);
        messages.add(Messages.ADDED_MESSAGE);
        messages.add(task.getMessage());
        messages.add(String.format("Tasks left: %d",
            TaskList.getIncompleteTasksCount()));
        return messages;
    }
}
