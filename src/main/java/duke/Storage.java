package duke;

import tasks.Deadline;
import tasks.Task;
import tasks.Todo;
import tasks.Event;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static Task processFile(String line) {
        Task t = null;
        int idx;
        String description, at , by;

        if (line.charAt(1) == 'T') {
            t = new Todo(line.substring(7));
        } else if (line.charAt(1) == 'E') {
            idx = line.indexOf('(');
            description = line.substring(7, idx).trim();
            at = line.substring(idx+4, line.length()-1).trim();
            t = new Event(description, at);
        } else if (line.charAt(1) == 'D') {
            idx = line.indexOf('(');
            description = line.substring(7, idx).trim();
            by = line.substring(idx+4, line.length()-1).trim();
            t = new Deadline(description, by);
        }
        // check if task isDone
        if (line.contains("\u2713")) {
            t.markAsDone();
        }
        return t;
    }

    public static ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task t = processFile(line);
                tasks.add(t);
            }

        } catch (FileNotFoundException e) {
            // starts with an empty file (empty taskArray)
            System.out.println("No file is loaded. Starting with an empty task list ... ");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return tasks;
    }

    public static void saveFile() {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            ArrayList<Task> tasks = TaskList.getTasks();
            for (Task t : tasks) {
                String task = t.toString() + "\n";
                writer.write(task);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
