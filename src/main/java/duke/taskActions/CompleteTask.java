package duke.taskActions;

import java.util.ArrayList;
import java.util.List;

import duke.common.Strings;
import duke.data.Deadline;
import duke.data.exception.DukeException;
import duke.data.Event;
import duke.data.Task;
import duke.data.Todo;
import duke.data.exception.DukeExceptionKey;

public class CompleteTask {

    /**
     * Marks the task as completed by the given task index.
     * @param stringIndex Index number of task.
     * @return Messages to notify user that task has been completed.
     * @throws DukeException When task does not exist in storage.
     */
    public static List<String> completeTask(String stringIndex) throws DukeException {
        List<Task> storage = TaskList.getStorage();
        try {
            List<String> messages = new ArrayList<>();
            int index = Integer.parseInt(stringIndex);
            Task task = storage.get(index - 1);
            Task completedTask = null;
            if (task instanceof Todo) {
                completedTask = new Todo(task.getDescription(), true);
            }
            if (task instanceof Deadline) {
                completedTask = new Deadline(task.getDescription(), true,
                        ((Deadline) task).getDeadline());
            }
            if (task instanceof Event) {
                completedTask = new Event(task.getDescription(), true,
                        ((Event) task).getEvent());
            }
            if (completedTask != null) {
                storage.set(index - 1, completedTask);
                messages.add(Strings.DONE_MESSAGE);
                messages.add(completedTask.getMessage());
                messages.add(String.format("Tasks left: %d",
                    TaskList.getIncompleteTasksCount()));
                return messages;
            } else {
                throw new DukeException(DukeExceptionKey.NO_TASK_FOUND);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionKey.INDEX_FORMAT_ERROR);
        }
    }
}
