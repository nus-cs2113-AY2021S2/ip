package duke.storage;

import java.util.HashMap;

import duke.data.Deadline;
import duke.data.Event;
import duke.data.Task;
import duke.data.Todo;

public class TaskDecoder {

    /**
     * Decode task in text file into a task object.
     * @param input String input of task.
     * @return Task object.
     */
    public static Task convertToTask(String input) {
        HashMap<String,String> json = new HashMap<>();
        String[] jsonArray = input.split(",");
        for (String attribute : jsonArray) {
            String[] detail = attribute.split(":");
            String key = detail[0];
            String value = detail[1];
            json.put(key,value);
        }
        String type = json.get("type");
        String description = json.get("description");
        boolean isDone = json.get("isDone").equals("T");
        if (type.equals("T")) {
            return new Todo(description, isDone);
        }
        if (type.equals("E")) {
            String event = json.get("event");
            return new Event(description, isDone, event);
        }
        if (type.equals("D")) {
            String deadline = json.get("deadline");
            return new Deadline(description, isDone, deadline);
        }
        return null;
    }
}
