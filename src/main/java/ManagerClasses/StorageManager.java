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

public class StorageManager {
    private final TaskManager taskManager;

    public StorageManager(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

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