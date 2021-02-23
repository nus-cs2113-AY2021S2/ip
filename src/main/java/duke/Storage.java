package duke;

import duke.task.Task;

import java.util.ArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Handles loading data from file and storing data to file operations.
 */
public class Storage {
    /**
     * File object use for loading and storing Duke program task data.
     */
    private final File DUKE_FILE;

    /**
     * Constructs the initial value of DUKE_FILE
     *
     * @param dukeFile File object for loading and storing of data
     */
    public Storage(File dukeFile) {
        this.DUKE_FILE = dukeFile;
    }

    /**
     * Loads the tasks data from data file on disk.
     * Returns empty ArrayList if file does not exist or fail to load data.
     *
     * @return An ArrayList with tasks data loaded from DUKE_FILE.
     * @throws DukeException If there is an error arise from loading data and closing resources.
     */
    public ArrayList<Task> loadTasksFromFile() throws DukeException {

        ArrayList<Task> tasks = new ArrayList<>();

        if (DUKE_FILE.exists()) {
            FileInputStream fileInputStream = null;
            ObjectInputStream objectInputStream = null;

            try {
                fileInputStream = new FileInputStream(DUKE_FILE);
                objectInputStream = new ObjectInputStream(fileInputStream);

                tasks = (ArrayList<Task>) objectInputStream.readObject();
            } catch (IOException e) {
                throw new DukeException("Input/Output error. Failed to load tasks from file.");
            } catch (ClassNotFoundException e) {
                throw new DukeException("Data corrupted. Failed to load tasks from file.");
            } finally {
                // Close all resources
                try {
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }

                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                } catch (IOException e) {
                    throw new DukeException("Something's wrong when closing the file...");
                }
            }
        }

        return tasks;
    }

    /**
     * Stores the tasks data from taskList to disk.
     *
     * @param taskList TaskList data which needs to be saved to disk.
     * @throws DukeException If there is an error arise from storing data and closing resources.
     */
    public void storeTasksToFile(TaskList taskList) throws DukeException {
        // Creates parent directories if file does not exist
        if (!DUKE_FILE.exists()) {
            DUKE_FILE.getParentFile().mkdirs();
        }

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(DUKE_FILE);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(taskList.getTasks());
        } catch (IOException e) {
            throw new DukeException("Input/Output error. Failed to store tasks to file.");
        } finally {
            // Close all resources
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }

                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                throw new DukeException("Something's wrong when closing the file...");
            }
        }
    }
}
