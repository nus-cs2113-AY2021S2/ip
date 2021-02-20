package duke.storage;

import duke.data.Deadline;
import duke.data.Event;
import duke.data.Task;
import duke.data.Todo;

public class TaskEncoder {
    private static final String nullValue = "-";

    /**
     * Convert a task object from Duke into text format,
     * to be saved in local text file.
     * @param task Task object.
     * @return String input of task.
     */
    public static String convertToFile(Task task) {
        String description = task.getDescription();
        String isDone = task.isTaskDone() ? "T" : "F";
        if (task instanceof Event) {
            String event = ((Event) task).getEvent();
            return String.format(
                    "type:%s," +
                            "description:%s," +
                            "isDone:%s," +
                            "event:%s," +
                            "deadline:%s",
                    "E", description, isDone, event, nullValue
            );
        }
        if (task instanceof Deadline) {
            String deadline = ((Deadline) task).getDeadline();
            return String.format(
                    "type:%s," +
                            "description:%s," +
                            "isDone:%s," +
                            "event:%s," +
                            "deadline:%s",
                    "D", description, isDone, nullValue, deadline
            );
        }
        if (task instanceof Todo) {
            return String.format(
                    "type:%s," +
                            "description:%s," +
                            "isDone:%s," +
                            "event:%s," +
                            "deadline:%s",
                    "T",description, isDone, nullValue, nullValue
            );
        }
        return "";
    }
}
