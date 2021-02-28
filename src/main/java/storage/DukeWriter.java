package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a class that writes to the data storage text file.
 */
public class DukeWriter {
    /**
     * Writes the tasks in the task list to the storage text file.
     *
     * @param tasks The task list.
     */
    public static void writeToFile(ArrayList<Task> tasks) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("duke.txt");
            for (Task task : tasks) {
                fw.write(getTaskAsWritableStr(task));
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found!");
        }
    }

    /**
     * Returns a writable String based on the Task subclass identified based
     * so that it can be written to the storage text file.
     *
     * @param task The Task subclass to be identified.
     * @return The writable String.
     */
    private static String getTaskAsWritableStr(Task task) {
        String writable = "";
        if (task instanceof ToDo) {
            writable = getWritableTodo(task);
        } else if (task instanceof Deadline) {
            writable = getWritableDeadline(task);
        } else if (task instanceof Event) {
            writable = getWritableEvent(task);
        }
        return writable;
    }

    /**
     * Returns a writable String based
     * on the provided task identified as a ToDo.
     * so that it can be written to the storage text file.
     *
     * @param task The ToDo identified.
     * @return The writable String.
     */
    private static String getWritableTodo(Task task) {
        String writable;
        String type = "T";
        String isDone = task.isDone() ? "1" : "0";
        String description = task.getDescription();
        writable = String.join(" | ", type, isDone, description);
        return writable;
    }

    /**
     * Returns a writable String based
     * on the provided task identified as a DeadLine.
     * so that it can be written to the storage text file.
     *
     * @param task The DeadLine identified.
     * @return The writable String.
     */
    private static String getWritableDeadline(Task task) {
        String writable;
        String type = "D";
        String isDone = task.isDone() ? "1" : "0";
        String description = task.getDescription();
        String by = ((Deadline) task).getBy();
        writable = String.join(" | ", type, isDone, description, by);
        return writable;
    }

    /**
     * Returns a writable String based
     * on the provided task identified as a Event.
     * so that it can be written to the storage text file.
     *
     * @param task The Event identified.
     * @return The writable String.
     */
    private static String getWritableEvent(Task task) {
        String writable;
        String type = "E";
        String isDone = task.isDone() ? "1" : "0";
        String description = task.getDescription();
        String at = ((Event) task).getAt();
        writable = String.join(" | ", type, isDone, description, at);
        return writable;
    }
}
