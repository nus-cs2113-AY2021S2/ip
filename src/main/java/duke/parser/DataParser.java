package duke.parser;

import duke.exception.LoadDataException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class DataParser {

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

    public String parseTaskToString(Task task) {
        return task.getData();
    }
}
