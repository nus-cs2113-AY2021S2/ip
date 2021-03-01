package ManagerClasses;

import ExceptionClasses.SaveFileNotCreatedException;
import TaskClasses.Deadline;
import TaskClasses.Event;
import TaskClasses.Task;
import TaskClasses.Todo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Manages the saving and retrieving of data from storage files for Duke.
 */
public class StorageManager {
    private final TaskManager taskManager;

    /**
     * Constructor for StorageManager class. The taskManager needs to be passed here so as to be able to update
     * the list of tasks that are stored and updated in taskManager while Duke is running.
     * @param taskManager the taskManager that was initiated in the inputManager class.
     */
    public StorageManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Saves tasks into an existing data text file in the data folder that should have been created when
     * importTasksFromTxtFile() method is called when Duke starts.
     */
    public void saveTasksInTxtFile() {
        String currentPath = System.getProperty("user.dir");
        Path filePath = Paths.get(currentPath, "data", "duke.txt");
        try (PrintStream out = new PrintStream(new FileOutputStream(String.valueOf(filePath)))) {
            for (Task task : taskManager.getTasks()) {
                out.print(task.toSaveFormat() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Imports tasks from an existing data text file if it exists or creates one if it does not already exist.
     * Calls the method to parse the text file.
     */
    public void importTasksFromTxtFile() {
        String currentPath = System.getProperty("user.dir");
        Path folderPath = Paths.get(currentPath, "data");
        Path filePath = Paths.get(currentPath, "data", "duke.txt");
        boolean directoryExists = Files.exists(folderPath);
        if (!directoryExists) {
            createPath(folderPath, filePath);
        }
        parseTxtFile(filePath);
    }

    /**
     * Creates a new folder using the folderPath passed in and creates a file in it with the path of filePath.
     * @param folderPath the folder path where the new data files are going to be stored in.
     * @param filePath the file path where data is stored.
     */
    void createPath(Path folderPath, Path filePath) {
        File file = new File(String.valueOf(folderPath));
        try {
            if (file.mkdir()) {
                Path newFilePath = Paths.get(String.valueOf(filePath));
                Files.createFile(newFilePath);
            } else {
                throw new SaveFileNotCreatedException();
            }
        } catch (SaveFileNotCreatedException | IOException e) {
            System.out.println("Save file was not created");
        }
    }

    /**
     * Parses the data file to read each line and convert it into 'Task' type which is then stored in the tasks list
     * from the taskManager.
     * @param filePath the file path where data is stored.
     */
    void parseTxtFile(Path filePath) {
        try (Scanner inputFileScanner = new Scanner(new File(String.valueOf(filePath)))) {
            while (inputFileScanner.hasNextLine()) {
                String line = inputFileScanner.nextLine();
                String[] taskAttributes = line.split(" \\| ");
                String taskType = taskAttributes[0];
                switch (taskType) {
                case "T":
                    taskManager.getTasks().add(new Todo(Boolean.parseBoolean(taskAttributes[1]), taskAttributes[2]));
                    break;
                case "D":
                    taskManager.getTasks().add(new Deadline(Boolean.parseBoolean(taskAttributes[1]), taskAttributes[2],
                            taskAttributes[3]));
                    break;
                case "E":
                    taskManager.getTasks().add(new Event(Boolean.parseBoolean(taskAttributes[1]), taskAttributes[2],
                            taskAttributes[3]));
                    break;
                default:
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found");
        }
    }
}