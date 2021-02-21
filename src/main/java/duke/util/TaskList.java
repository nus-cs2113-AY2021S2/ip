package duke.util;

import java.util.List;

import duke.storage.Storage;
import duke.data.Task;

public class TaskList {
    private static List<Task> tasks;

    public static List<Task> getStorage() {
        if (tasks == null) {
            tasks = Storage.read();
        }
        return tasks;
    }

    /**
     * Get number of undone tasks in memory.
     * @return Number of tasks to be completed in memory.
     */
    public static int getIncompleteTasksCount() {
        int count = 0;
        for (Task task : tasks) {
            if (!task.isTaskDone()) {
                count++;
            }
        }
        return count;
    }

    public static void saveTasksToTextFile() {
        Storage.save(tasks);
    }
}