package jarvis.storage;

import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.Todo;

/**
 * Decodes the text file into a {@code Task} object
 */
public class TextDecoder {

    private static final int TASK_TYPE_INDEX = 1;
    private static final int TASK_STATUS_INDEX = 4;
    private static final int TASK_DESCRIPTION_START_INDEX = 7;

    /**
     * Decodes the text into a {@code Task} object
     *
     * @param encodedTask task details in String format
     * @return {@code Task} object
     */
    public static Task decodeTaskFromString(String encodedTask){
        String taskDescription = encodedTask.substring(TASK_DESCRIPTION_START_INDEX);
        Task task = new Task(taskDescription);
        char taskType = encodedTask.charAt(TASK_TYPE_INDEX);
        switch (taskType) {
        case 'T':
            task = new Todo(taskDescription);
            task.setTaskStatus(encodedTask.charAt(TASK_STATUS_INDEX) != ' ');
            break;
        case 'D':
            String[] detailsDeadline = taskDescription.split("\\(by:", 2);
            String descriptionDeadline = detailsDeadline[0];
            String by = detailsDeadline[1].replace(")", "");
            task = new Deadline(descriptionDeadline, by);
            task.setTaskStatus(encodedTask.charAt(TASK_STATUS_INDEX) != ' ');
            break;
        case 'E':
            String[] detailsEvent = taskDescription.split("\\(at:", 2);
            String descriptionEvent = detailsEvent[0];
            String at = detailsEvent[1].replace(")", "");
            task = new Event(descriptionEvent, at);
            task.setTaskStatus(encodedTask.charAt(TASK_STATUS_INDEX) != ' ');
            break;
        }
        return task;
    }
}
