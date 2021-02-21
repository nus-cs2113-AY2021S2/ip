package jarvis.storage;

import jarvis.task.Task;

/**
 * Encodes the {@code Task} object into a text file for storage
 */
public class TextEncoder {

    /**
     * Encodes {@code Task} object into a string
     *
     * @param task {@code Task} object to encode
     * @return the string format of {@code Task} object
     */
    public static String encodeTaskToString(Task task) {
        return task.toString();
    }
}
