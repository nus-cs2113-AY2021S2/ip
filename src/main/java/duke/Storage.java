package duke;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import duke.exception.SaveException;
import duke.exception.SaveException.SaveExceptionType;

/**
 * Storage class for handling the loading and writing of local save file
 */
public class Storage {
    protected String filepath;

    /**
     * Construct a storage instance by setting the filepath attribute with the given value
     * @param filepath Filepath of the save file
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Load a task list from the save file
     * @return The task list loaded from the file
     * @throws IOException This is thrown when object reading encounters an error
     * @throws SaveException This is thrown when save file is missing, or a task list cannot be retrieved
     */
    public TaskList load() throws IOException, SaveException {
        TaskList tasks = null;
        FileInputStream fileIn = null;
        ObjectInputStream objIn = null;
        try {
            fileIn = new FileInputStream(this.filepath);
            objIn = new ObjectInputStream(fileIn);
            Object obj = objIn.readObject();

            if (obj instanceof TaskList) {
                tasks = (TaskList) obj;
                tasks.setStorage(this);
            } else {
                throw new SaveException(SaveExceptionType.INVALID_SAVE);
            }
        } catch (FileNotFoundException e) {
            throw new SaveException(SaveExceptionType.NO_SAVE, e);
        } catch (ClassNotFoundException e) {
            throw new SaveException(SaveExceptionType.INVALID_SAVE, e);
        } finally {
            // Close all open file and object handles
            if (objIn != null) {
                objIn.close();
            }
            if (fileIn != null) {
                fileIn.close();
            }
        }
        return tasks;
    }

    /**
     * Save the task list to the save file
     * @param tasks Task list to be saved
     * @throws IOException This is thrown when object writing encounters an error
     * @throws SaveException This is thrown when being failed to open the save file
     */
    public void save(TaskList tasks) throws IOException, SaveException {
        FileOutputStream fileOut = null;
        ObjectOutputStream objOut = null;
        try {
            fileOut = new FileOutputStream(this.filepath);
            objOut = new ObjectOutputStream(fileOut);

            // Nullify storage attribute before saving
            tasks.setStorage(null);
            objOut.writeObject(tasks);
            tasks.setStorage(this);
        } catch (FileNotFoundException e) {
            // Thrown by `new FileOutputStream`, usually when `file` is a folder or cannot be created
            throw new SaveException(SaveExceptionType.INVALID_PATH);
        } finally {
            // Close all open file and object handles
            if (objOut != null) {
                objOut.close();
            }
            if (fileOut != null) {
                fileOut.close();
            }
        }
    }
}
