package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Implements file storage.
 *
 * @author Leonardo Irvin Pratama
 */
public class Storage {
    private final File data;
    private final String FILE = "./tasks.txt";

    /**
     * Initializes a Storage object.
     *
     * @throws DukeException If directory not found.
     */
    public Storage() throws DukeException {
        try {
            File data = new File(FILE);
            if (!data.exists()) {
                data.createNewFile();
                assert data.exists() : " Directory cannot be made";
            }
            this.data = data;
        } catch (IOException error) {
            throw new DukeException(" I cannot find the directory");
        }
    }

    /**
     * Generates list of tasks from save file.
     *
     * @return List of tasks generated from save file.
     * @throws DukeException If there is file reading error.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String[] inputs = sc.nextLine().split(" \\| ");
                String type = inputs[0];
                boolean isDone = inputs[1].equals("1");
                String description = inputs[2];
                String time;
                if (type.equals("T")) {
                    tasks.add(new Todo(description, isDone));
                } else if (type.equals(("D"))) {
                    time = inputs[3];
                    tasks.add(new Deadline(description, isDone, time));
                } else if (type.equals("E")) {
                    time = inputs[3];
                    tasks.add(new Event(description, isDone, time));
                } else {
                    throw new DukeException(" I cannot identify the task type :(");
                }
            }
            return tasks;
        } catch (FileNotFoundException error) {
            throw new DukeException(" I cannot find the directory.");
        }
    }

    /**
     * Saves task to hard disk.
     *
     * @param tasks Tasks user currently have.
     * @throws DukeException If there is writing file error.
     */
    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(data));
            for (int i = 0; i < tasks.size(); i++) {
                writer.println(tasks.get(i).saveToHardDisk());
            }
            writer.close();
        } catch (IOException error) {
            throw new DukeException(" I cannot save your tasks.");
        }
    }
}