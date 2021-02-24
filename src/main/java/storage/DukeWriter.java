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

public class DukeWriter {
    public static void writeToFile(ArrayList<Task> tasks) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("duke.txt");
            for (Task task: tasks) {
                fw.write(getTaskAsWritableStr(task));
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found!");
        }
    }
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

    private static String getWritableTodo(Task task) {
        String writable;
        String type = "T";
        String isDone = task.isDone() ? "1" : "0";
        String description = task.getDescription();
        writable = String.join(" | ", type, isDone, description);
        return writable;
    }

    private static String getWritableDeadline(Task task) {
        String writable;
        String type = "D";
        String isDone = task.isDone() ? "1" : "0";
        String description = task.getDescription();
        String by = ((Deadline) task).getBy();
        writable = String.join(" | ", type, isDone, description, by);
        return writable;
    }

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
