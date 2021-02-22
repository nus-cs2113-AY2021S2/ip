package duke.parser;

import duke.exception.LoadDataException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

/**
 * Converts between Task and String data types.
 * Task objects are used in the program while String
 * is used to store in the hard disk.
 */
public class DataParser {

    /**
     * Converts tasks' data extracted from hard disk into a list
     * of Task objects for the program to use.
     * @param taskStringList Data extracted from hard disk.
     * @return List of Task objects converted from hard disk data.
     * @throws LoadDataException On corrupted data.
     */
    public ArrayList<Task> parseTaskString(ArrayList<String> taskStringList) throws LoadDataException {

        ArrayList<Task> taskList = new ArrayList<>();

        for (String taskString : taskStringList) {
            Task task = parseStringToTask(taskString);
            taskList.add(task);
        }

        return taskList;
    }

    private Task parseStringToTask(String taskString) throws LoadDataException {

        String[] parsedLine = taskString.split(";", 3);

        char taskType = parsedLine[0].charAt(0);
        boolean taskIsDone = parsedLine[1].equals("1");
        String taskDescription = parsedLine[2];

        Task task;

        switch (taskType) {
        case 'T':
            task = new Todo(taskDescription);
            break;
        case 'D': {
            String[] nameAndDate = taskDescription.split(";", 2);
            task = new Deadline(nameAndDate[0], nameAndDate[1]);
            break;
        }
        case 'E': {
            String[] nameAndDate = taskDescription.split(";", 2);
            task = new Event(nameAndDate[0], nameAndDate[1]);
            break;
        }
        default:
            throw new LoadDataException();
        }

        if (taskIsDone) {
            task.setAsDone();
        }

        return task;
    }

    /**
     * Converts task data into String format so as to be
     * stored inside hard disk.
     * @param task Task object to be converted.
     * @return task data in String format.
     */
    public String parseTaskToString(Task task) {
        return task.getData();
    }
}
