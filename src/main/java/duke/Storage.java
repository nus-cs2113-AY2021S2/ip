package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages storage of data in a file at a defined filePath.
 */
public class Storage {
    String filePath;

    /**
     * Checks if filePath given leads to existing directories and files.
     * If the directory/file does not exist, new directories/files will be created.
     *
     * @param filePath Relative path from current directory.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        String[] directories = filePath.split("/");
        // check if the directory and file exists
        try {
            //check all directories. (ignores file)
            String currentPath = "";
            for (int i = 0; i < directories.length - 1; i++) {
                currentPath += directories[i] + "/";
                if (Files.notExists(Paths.get(currentPath))) {
                    Files.createDirectory(Paths.get(currentPath));
                }
            }

            if (Files.notExists(Paths.get(filePath))) {
                Files.createFile(Paths.get(filePath));
            }
        } catch (IOException e) {
            System.out.println("Exception occurred when creating directory/file at given path! :(");
        }
    }

    /**
     * Saves current tasklist at given filePath.
     *
     * @param tasks Current array of tasks, forming the tasklist.
     */
    public void saveTasklist(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : tasks) {
                fw.write(task.toString() + System.lineSeparator());
            }
            fw.close();
    }
        catch (IOException e) {
        System.out.println("Exception occurred when writing tasklist to file! :(");
    }

    }

    /**
     * Returns an ArrayList of tasks, loaded from file at given filePath.
     * If file at given filePath is empty, an empty ArrayList is returned.
     *
     * @return ArrayList of tasks loaded from filePath.
     */
    public ArrayList<Task> loadTasklist() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            // check if the directory and file exists
            if (Files.exists(Paths.get(this.filePath))) {
                File f = new File(filePath);
                Scanner scanner = new Scanner(f);
                boolean isDone;
                while (scanner.hasNext()) {
                    String taskString = scanner.nextLine();
                    if (taskString.contains("[X]")) {
                        isDone = true;
                    } else {
                        isDone = false;
                    }
                    if (taskString.startsWith("[E]")) {
                        String description = taskString.substring(7, taskString.indexOf('(')-1);
                        String timing = taskString.substring(taskString.indexOf('(') + 5, taskString.indexOf(')'));
                        tasks.add(Task.taskCount, new Event(description, timing, isDone));
                    } else if (taskString.startsWith("[D]")) {
                        String description = taskString.substring(7, taskString.indexOf('(')-1);
                        String timing = taskString.substring(taskString.indexOf('(') + 5, taskString.indexOf(')'));
                        MonthDay monthDay = MonthDay.parse(timing, DateTimeFormatter.ofPattern("d MMM YYYY"));
                        LocalDate deadline = monthDay.atYear(Integer.parseInt(timing.split(" ")[2]));
                        tasks.add(Task.taskCount, new Deadline(description, deadline, isDone));
                    } else if (taskString.startsWith("[T]")) {
                        String description = taskString.substring(7);
                        tasks.add(Task.taskCount, new Todo(description, isDone));
                    }

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tasklist file not found! :(");
        }
        return tasks;
    }



}
