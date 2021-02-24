package duke;

import tasks.Deadline;
import tasks.Task;
import tasks.Todo;
import tasks.Event;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving task in the file.
 */
public class Storage {

    private static String filePath;

    /**
     * Contructor for Storage.
     * Initialise the file path.
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Process task object from a line in the loaded file.
     * Determine task type, description, isDone, at (for event), by (for deadline)x.
     * @param line A line in the loaded file.
     * @return Task object to be added to the arraylist
     */
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

    /**
     * Read line by line from the file as specified by the file path.
     * @return Arraylist containing all the tasks loaded from the file
     */
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

    /**
     * Write the updated arraylist back into the file path specified.
     */
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
