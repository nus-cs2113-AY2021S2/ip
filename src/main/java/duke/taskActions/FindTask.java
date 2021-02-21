package duke.taskActions;

import java.util.ArrayList;
import java.util.List;

import duke.common.Messages;
import duke.data.Task;
import duke.data.exception.DukeException;
import duke.data.exception.DukeExceptionKey;

public class FindTask {

    public static List<String> findTask(String keyword) throws DukeException {
        if (keyword.isEmpty() || keyword == null) {
            throw new DukeException(DukeExceptionKey.EMPTY_KEYWORD);
        }
        List<Task> storage = TaskList.getStorage();
        List<String> matchingTasks = new ArrayList<>();
        int index = 1;
        for (Task task : storage) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                String taskDetail = String.format("%d.%s", index, task.getMessage());
                matchingTasks.add(taskDetail);
            }
            index++;
        }
        List<String> messages = new ArrayList<>();
        if (matchingTasks.isEmpty()) {
            messages.add(Messages.NO_MATCHING_TASKS + keyword);
        } else {
            messages.add(Messages.FOUND_TASK_MESSAGE);
            messages.addAll(matchingTasks);
        }
        return messages;
    }
}
