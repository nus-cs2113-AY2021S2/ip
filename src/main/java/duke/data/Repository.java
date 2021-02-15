package duke.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import duke.model.Deadline;
import duke.model.Event;
import duke.model.Task;
import duke.model.Todo;
import duke.viewmodel.Utils;

public class Repository {
    private static final String filePath = "./tasks.txt";
    private static final String nullValue = "-";

    public static void save(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                String textInput = convertToFile(task);
                fw.write(textInput + "\n");
            }
            fw.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public static List<Task> read() {
        List<String> messages = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (f.createNewFile()) {
                messages.add("Tasks can be found at: " + f.getPath());
                Utils.reply(messages);
            }
            Scanner s = new Scanner(f);
            List<Task> tasks = new ArrayList<>();
            while (s.hasNext()) {
                String input = s.nextLine();
                if (!input.isEmpty()) {
                    Task task = convertToTask(input);
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (IOException fileNotFoundException) {
            messages.add(fileNotFoundException.getMessage());
            Utils.reply(messages);
        }
        return null;
    }

    private static String convertToFile(Task task) {
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
