package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.taslist.TaskList;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Store and load the taskList as a txt file
 */
public class Storage {

    public static String dir;

    /**
     * Creates Storage object
     *
     * @param dir
     */
    public Storage(String dir) {
        this.dir = dir;
    }


    /**
     * Checks if file path and file to save list exists.
     * Creates if it do not exist, then write list into file.
     */
    public static void saveTasks() {

        Path path = Paths.get(dir, "data");

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Path file = FileSystems.getDefault().getPath(dir, "data", "duke.txt");
            File tasks = file.toFile();

            if (!tasks.exists()) {
                tasks.createNewFile();
            }

            writeToFile(file.toString(), TaskList.taskList);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes list to file one by one, line by line.
     *
     * @param filePath
     * @param tasks
     * @throws IOException
     */
    private static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {

        FileWriter fileWriter = new FileWriter(filePath);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (int i = 0; i < TaskList.numOfTasks; i++) {
            bufferedWriter.write(tasks.get(i).toString());
            bufferedWriter.write("\n");
        }

        bufferedWriter.close();
    }

    /**
     * Checks if file path and file to load list exists.
     * Creates if it does not exist, then load list from file.
     */
    public static void loadTasks() {

        Path path = Paths.get(dir, "data");

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Path file = FileSystems.getDefault().getPath(dir, "data", "duke.txt");
            File tasks = file.toFile();

            if (!tasks.exists()) {
                tasks.createNewFile();
            }

            BufferedReader reader = Files.newBufferedReader(file);
            String data = reader.readLine();
            int i = 0;

            while (data != null) {
                Task newTask = toTask(data);
                TaskList.addTask(newTask);
                i++;
                data = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Converts line read from file into a Task object
     *
     * @param data
     * @return Task
     */
    private static Task toTask(String data) {

        String[] read = data.trim().split("]");

        if (read[0].contains("T")) {
            Task newTodo = new Todo(read[2].trim());
            if (read[1].contains("X")) {
                newTodo.setAsDone();
            }
            return newTodo;
        }
        else if (read[0].contains("D")) {
            String[] split = read[2].trim().split(" \\(by:");
            Task newDeadline = new Deadline(split[0], split[1].replace(")", ""));
            if (read[1].contains("X")) {
                newDeadline.setAsDone();
            }
            return newDeadline;
        }
        else if (read[0].contains("E")) {
            String[] split = read[2].trim().split(" \\(at:");
            Task newEvent = new Event(split[0], split[1].replace(")", ""));
            if (read[1].contains("X")) {
                newEvent.setAsDone();
            }
            return newEvent;
        }
        return null;
    }
}
