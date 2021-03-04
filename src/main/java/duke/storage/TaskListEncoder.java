package duke.storage;

import duke.data.TaskList;

/**
 * Encodes the {@code TaskList} object into a data file for storage.
 */
public class TaskListEncoder {
    public static String encoder(TaskList tasks) {
        String text = "";
        for (int i=0; i<tasks.size(); i++) {
            text += tasks.getTaskByIndex(i).toText(i+1) + System.lineSeparator();
        }
        return text;
    }
}
