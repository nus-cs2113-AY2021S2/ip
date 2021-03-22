package dukchess.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dukchess.entity.Deadline;
import dukchess.entity.Event;
import dukchess.entity.Task;
import dukchess.entity.Todo;
import dukchess.ui.Ui;

public final class Storage {
    private static final Path storageFilePath = Path.of("data" + File.separator + "data.txt");

    private static void createStorageFileIfNotFound() throws IOException {
        String dataDirPath = Paths.get("data").toString();
        File dataDir = new File(dataDirPath);
        dataDir.mkdir(); // try creating directory. If fail, its okay
        File storageFile = new File(String.valueOf(storageFilePath));
        if (!storageFile.exists()) { // if file does not exist, create it
            storageFile.createNewFile();
        }
    }

    /**
     * Loads data from Duke's data text file at the start of the program.
     * @return An ArrayList of tasks
     * @throws IOException
     */
    public static ArrayList<Task> loadData() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        Storage.createStorageFileIfNotFound();
        File storageFile = new File(String.valueOf(storageFilePath));
        Scanner storageFileScanner = new Scanner(storageFile);

        Pattern taskStringPattern = Pattern.compile("\\[(\\w)\\]\\[(.?)\\] ([^(]+)(\\((.+)\\))?");
        while (storageFileScanner.hasNext()) {
            Task task = null;
            String currentTaskString = storageFileScanner.nextLine();

            Matcher taskStringMatches = taskStringPattern.matcher(currentTaskString);
            taskStringMatches.matches();
            String taskType = taskStringMatches.group(1);
            boolean isDone = taskStringMatches.group(2).equals("X");
            String taskDescription = taskStringMatches.group(3).trim();
            String taskDetails = taskStringMatches.group(5);

            switch (taskType) {
            case "T":
                task = new Todo(taskDescription, isDone);
                break;
            case "E":
                String whenIsEventAt = taskDetails.split("at: ")[1];
                task = new Event(taskDescription, isDone, whenIsEventAt);
                break;
            case "D":
                String whenIsTaskDue = taskDetails.split("by: ")[1];
                task = new Deadline(taskDescription, isDone, whenIsTaskDue);
                break;
            default:
                break;
            }
            tasks.add(task);
        }

        return tasks;
    }

    /**
     * Handles all file saving
     *
     * @param tasks Task list to be updated.
     */
    public static void saveData(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(storageFilePath));
            for (Task task : tasks) {
                String taskToWrite = task.toString() + "\n";
                fileWriter.write(taskToWrite);
            }
            fileWriter.close();
        } catch (IOException e) {
            Ui.printErrorMessage(e);
        }
    }
}
