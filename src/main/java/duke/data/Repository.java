package duke.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import duke.model.Deadline;
import duke.model.Event;
import duke.model.Task;
import duke.model.Todo;

public class Repository {
    private static final String filePath = "./src/main/java/duke/data/tasks.txt";
    private static final String nullValue = "-";

    public static void save(HashMap<Integer, Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Map.Entry<Integer,Task> entry : tasks.entrySet()) {
                int index = entry.getKey();
                Task task = entry.getValue();
                String textInput = convertToFile(index, task);
                fw.write(textInput + "\n");
            }
            fw.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public static HashMap<Integer, Task> read() {
        try {
            File f = new File(filePath);
            if (f.createNewFile()) {
                System.out.println("New file created at: " + f.getPath());
            }
            Scanner s = new Scanner(f);
            HashMap<Integer, Task> tasks = new HashMap<>();
            while (s.hasNext()) {
                String input = s.nextLine();
                if (!input.isEmpty()) {
                    Task task = convertToTask(input);
                    int index = task.getIndex();
                    tasks.put(index, task);
                }
            }
            return tasks;
        } catch (IOException fileNotFoundException) {
            System.out.println(fileNotFoundException.getMessage());
        }
        return null;
    }

    private static String convertToFile(int index, Task task) {
        String stringIndex = Integer.toString(index);
        String description = task.getDescription();
        String isDone = task.isTaskDone() ? "T" : "F";
        if (task instanceof Event) {
            String event = ((Event) task).getEvent();
            return String.format(
                "type:%s," +
                "index:%s," +
                "description:%s," +
                "isDone:%s," +
                "event:%s," +
                "deadline:%s",
                "E",stringIndex, description, isDone, event, nullValue
            );
        }
        if (task instanceof Deadline) {
            String deadline = ((Deadline) task).getDeadline();
            return String.format(
                            "type:%s," +
                            "index:%s," +
                            "description:%s," +
                            "isDone:%s," +
                            "event:%s," +
                            "deadline:%s",
                    "D",stringIndex, description, isDone, nullValue, deadline
            );
        }
        if (task instanceof Todo) {
            return String.format(
                            "type:%s," +
                            "index:%s," +
                            "description:%s," +
                            "isDone:%s," +
                            "event:%s," +
                            "deadline:%s",
                    "T",stringIndex, description, isDone, nullValue, nullValue
            );
        }
        return "";
    }

    private static Task convertToTask(String input) {
        HashMap<String,String> json = new HashMap<>();
        String[] jsonArray = input.split(",");
        for (String attribute : jsonArray) {
            String[] detail = attribute.split(":");
            String key = detail[0];
            String value = detail[1];
            json.put(key,value);
        }
        String type = json.get("type");
        int index = Integer.parseInt(json.get("index"));
        String description = json.get("description");
        boolean isDone = json.get("isDone").equals("T");
        if (type.equals("T")) {
            return new Todo(index, description, isDone);
        }
        if (type.equals("E")) {
            String event = json.get("event");
            return new Event(index, description, isDone, event);
        }
        if (type.equals("D")) {
            String deadline = json.get("deadline");
            return new Deadline(index, description, isDone, deadline);
        }
        return null;
    }
}
