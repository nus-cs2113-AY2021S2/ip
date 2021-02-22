package duke;

import duke.task.Task;

import java.util.ArrayList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    private final File DUKE_FILE;

    public Storage(File dukeFile) {
        this.DUKE_FILE = dukeFile;
    }

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

    public void storeTasksToFile(TaskList taskList) throws DukeException {
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
