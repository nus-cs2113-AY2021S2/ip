package duke.taskActions;

import java.util.ArrayList;
import java.util.List;

import duke.data.exception.DukeException;
import duke.data.Task;
import duke.data.exception.DukeExceptionKey;

public class FetchTasks {

    /**
     * Fetches all previously mentioned messages.
     * @return All tasks in storage.
     * @throws DukeException When the storage is empty or index is less than 0.
     */
    public static List<String> fetchTasks() throws DukeException {
        List<Task> storage = TaskList.getStorage();
        List<String> messages = new ArrayList<>();
        int index = 1;
        for (Task task : storage) {
            String taskDetail = String.format("%d.%s", index, task.getMessage());
            messages.add(taskDetail);
            index++;
        }
        if (messages.isEmpty()) {
            throw new DukeException(DukeExceptionKey.EMPTY_TASK_LIST);
        }
        return messages;
    }
}
