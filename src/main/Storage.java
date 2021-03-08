package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Storage handles all loading and saving of data for the Duke Application.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class Storage {

    public static final String DEFAULT_STORAGE_FILEPATH = "taskdata.ser";
    public static TaskList taskList;

    /**
     * Saves the {@code taskList} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public static void save(TaskList taskList) throws StorageOperationException {
        try {
            FileOutputStream writeData = new FileOutputStream("taskdata.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(taskList);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            throw new StorageOperationException("Error saving to file!");
        }
    }

    /**
     * Loads the saved task list data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or loading data to file.
     */
    public static TaskList loadData() throws StorageOperationException {
        if (!Files.exists(Path.of(DEFAULT_STORAGE_FILEPATH)) || !Files.isRegularFile(Path.of(DEFAULT_STORAGE_FILEPATH))) {
            return new TaskList();
        }

        try {
            FileInputStream readData = new FileInputStream("taskdata.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            taskList = (TaskList) readStream.readObject();
            readStream.close();

            return taskList;

        } catch (FileNotFoundException e1) {
            throw new AssertionError("File does not exist. Creating new storage file.");
        } catch (IOException | ClassNotFoundException e) {
            throw new StorageOperationException("Error loading data.");
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }


}
