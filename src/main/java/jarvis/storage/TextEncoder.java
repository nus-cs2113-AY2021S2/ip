package jarvis.storage;

import jarvis.task.Task;

/**
 * Encodes a Task object into a text file for storage.
 */
public class TextEncoder {

    /**
     * Encodes a Task object into a string.
     *
     * @param task Task object to encode.
     * @return the string format of the Task object.
     */
    public static String encodeTaskToString(Task task) {
        return task.toString();
    }
}
