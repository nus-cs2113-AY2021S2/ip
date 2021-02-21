package duke.util.actions;

import java.util.ArrayList;
import java.util.List;

import duke.common.Messages;
import duke.data.exception.DukeException;
import duke.data.Task;
import duke.data.exception.DukeExceptionKey;
import duke.util.TaskList;

public class DeleteTask {

    /**
     * Removes a task from storage by their task index number.
     * @param stringIndex Index number of the task.
     * @return Messages to notify user that task has been deleted.
     * @throws DukeException When task does not exist in storage.
     */
    public static List<String> deleteTask(String stringIndex) throws DukeException {
        List<Task> storage = TaskList.getStorage();
        List<String> messages = new ArrayList<>();
        Task removedTask = null;

        try {
            int index = Integer.parseInt(stringIndex);
            removedTask = storage.remove(index - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionKey.INDEX_FORMAT_ERROR);
        }

        if (removedTask == null) {
            throw new DukeException(DukeExceptionKey.NO_TASK_FOUND);
        }

        messages.add(Messages.DELETED_MESSAGE);
        messages.add(removedTask.getMessage());
        messages.add(String.format("Tasks left: %d", TaskList.getIncompleteTasksCount()));
        return messages;
    }
}
