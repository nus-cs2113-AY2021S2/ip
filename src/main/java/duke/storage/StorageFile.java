package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StorageFile {

    private static void createFile() {
        try {
            File file = new File("duke.txt");
            if (file.createNewFile()) {}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile(List<Task> tasks) {
        try {
            FileInputStream file = new FileInputStream("duke.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|", 0);
                String type = parts[0];
                String isDone = parts[1];
                String task = parts[2];
                if (type.equals("T")) {
                    Task t = new Todo(task);
                    tasks.add(t);
                    if (isDone.equals("1")) {
                        t.markAsDone();
                    }
                } else if (type.equals("D")) {
                    String time = parts[3];
                    Deadline t = new Deadline(task, time);
                    tasks.add(t);
                    if (isDone.equals("1")) {
                        t.markAsDone();
                    }
                } else {
                    String time = parts[3];
                    Event t = new Event(task, time);
                    tasks.add(t);
                    if (isDone.equals("1")) {
                        t.markAsDone();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(List<Task> tasks) {
        try {
            File file = new File("duke.txt");
            FileWriter writer = new FileWriter(file);
            for (Task t : tasks) {
                if (t instanceof Todo) {
                    writer.write(t.getType() + "|" + t.getTextIcon().trim() + "|" + t.getTask().trim());
                } else {
                    writer.write(t.getType() + "|" + t.getTextIcon().trim() + "|" + t.getTask().trim() + "|" + t.getTime().trim());
                }
                writer.write("\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(List<Task> tasks) {
        createFile();
        readFile(tasks);
    }
}
