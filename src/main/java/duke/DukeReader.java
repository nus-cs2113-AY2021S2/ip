package duke;

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

public class DukeReader {
    private static ArrayList<Task> tasks = new ArrayList<>();

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

    private static void createEvent(boolean isDone, String description, String at) {
        Event event = new Event(description, at);
        if (isDone) {
            event.markDone();
        }
        tasks.add(event);
    }

    private static void createDeadline(boolean isDone, String description, String by) {
        Deadline deadline = new Deadline(description, by);
        if (isDone) {
            deadline.markDone();
        }
        tasks.add(deadline);
    }

    private static void createToDo(boolean isDone, String description) {
        ToDo todo = new ToDo(description);
        if (isDone) {
            todo.markDone();
        }
        tasks.add(todo);
    }

    public static ArrayList<Task> getTaskListFromFile() {
        return tasks;
    }
}
