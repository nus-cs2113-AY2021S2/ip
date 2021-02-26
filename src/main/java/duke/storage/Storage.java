package duke.storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private static String filePath = "data/tasks.txt";

    public Storage(String filepath) {
        this.filePath = filepath;
    }

    public ArrayList <Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            if (Files.exists(Paths.get(filePath))) {
                File f = new File(filePath);
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String text = sc.nextLine();
                    if (text.startsWith("[T]")) {
                        String getDescription = text.substring(7);
                        tasks.add(TaskList.tasksCount, new Todo(getDescription));
                        TaskList.tasksCount++;
                        if (text.substring(4, 5).compareTo("\u2718") == 0) {
                            tasks.get(TaskList.tasksCount - 1).markAsDone();
                        }
                    } else if (text.startsWith("[E]")) {
                        String getDescription = text.substring(7, text.indexOf("(") - 1);
                        String event = text.substring(text.indexOf("(") + 5, text.indexOf(")"));
                        tasks.add(TaskList.tasksCount, new Event(getDescription, event));
                        TaskList.tasksCount++;
                        if (text.substring(4, 5).compareTo("\u2718") == 0) {
                            tasks.get(TaskList.tasksCount - 1).markAsDone();
                        }
                    } else if (text.startsWith("[D]")) {
                        String description = text.substring(7, text.indexOf("(") - 1);
                        String deadline = text.substring(text.indexOf("(") + 5, text.indexOf(")"));
                        tasks.add(TaskList.tasksCount, new Deadline(description, deadline));
                        TaskList.tasksCount++;
                        if (text.substring(4, 5).compareTo("\u2718") == 0) {
                            tasks.get(TaskList.tasksCount - 1).markAsDone();
                        }
                    }

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! File not found!");
        }
        return tasks;
    }

    public static void writeFile() {
        try {
            if (Files.notExists(Paths.get("data/"))) {
                Files.createDirectory(Paths.get("data/"));
            } else if (Files.notExists(Paths.get(filePath))) {
                Files.createFile(Paths.get(filePath));
            }
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < TaskList.tasksCount; i++) {
                fw.write(TaskList.get(i).toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error! Unable to write to file");
        }


    }
}

