package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Represents a class that reads in the data storage text file and loads it if it exists and has existing data.
 */
public class DukeReader {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Reads the data in the text file and creates tasks to add to the task list if data is present.
     */
    public static void readFromFile() {
        try {
            File f = new File("duke.txt");
            if (!f.exists()) {
                f.createNewFile();
            }
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                createTask(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    /**
     * Reads the input from the text file and creates a Task subclass to add to the task list.
     * @param input A line of input read from the text file.
     */
    private static void createTask(String input) {
        String[] parts = input.split(Pattern.quote(" | "));
        String type = parts[0];
        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];
        switch (type) {
        case "T":
            createToDo(isDone, description);
            break;
        case "D":
            String by = parts[3];
            createDeadline(isDone, description, by);
            break;
        case "E":
            String at = parts[3];
            createEvent(isDone, description, at);
            break;
        }
    }
    /**
     * Reads the input from the text file and creates a Event to add to the task list
     * with corresponding description and done status.
     * @param input A line of input read from the text file.
     */
    private static void createEvent(boolean isDone, String description, String at) {
        Event event = new Event(description, at);
        if (isDone) {
            event.markDone();
        }
        tasks.add(event);
    }
    /**
     * Reads the input from the text file and creates a DeadLine to add to the task list
     * with corresponding description and done status.
     * @param input A line of input read from the text file.
     */
    private static void createDeadline(boolean isDone, String description, String by) {
        Deadline deadline = new Deadline(description, by);
        if (isDone) {
            deadline.markDone();
        }
        tasks.add(deadline);
    }
    /**
     * Reads the input from the text file and creates a ToDo to add to the task list
     * with corresponding description and done status.
     * @param input A line of input read from the text file.
     */
    private static void createToDo(boolean isDone, String description) {
        ToDo todo = new ToDo(description);
        if (isDone) {
            todo.markDone();
        }
        tasks.add(todo);
    }

    /**
     * Returns the task list loaded from reading the file.
     * If no file exists, the task list returned is empty.
     * @return The task list loaded from reading the file.
     */
    public static ArrayList<Task> getTaskListFromFile() {
        return tasks;
    }
}
