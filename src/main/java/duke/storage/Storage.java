package duke.storage;

import duke.task.Task;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Loads tasks from duke.txt or
 * creates and saves new tasks to duke.txt
 */

public class Storage {
    private static String path;

    public Storage() {
        path = System.getProperty("user.dir") + "/data/duke.txt";
    }

    /**
     * Creates data folder if it does not exist
     */
    public static void createFolder() {
        Ui.printPresentDirectory();
        String folderPath = System.getProperty("user.dir") + "/data";
        File folder = new File(folderPath);
        boolean isSuccessful = folder.mkdir();
        if (isSuccessful) {
            Ui.printSuccessfulCreateFolderMessage();

        } else {
            Ui.printFolderExistsMessage();
        }
    }

    /**
     * Checks if textfile exists, else create new file.
     *
     * @return True if file exists or text file is created.
     */
    public boolean retrieveTextFile() {
        boolean hasTextFile = false;
        try {
            File data = new File(path);
            if (data.createNewFile()) {
                System.out.println("Text file created: " + data.getName());
            } else {
                hasTextFile = true;
                Ui.printFileExistsMessage();
            }
        } catch (IOException e) { //creating or retrieving data has errors
            Ui.printErrorMessage(e);
        }
        return hasTextFile;
    }

    /**
     * Loads information from data.txt into Duke's list without change.
     *
     * @return Loaded tasks into Duke's list unformatted.
     */
    public ArrayList<String> loadData() {
        ArrayList<String> tasks = new ArrayList<>();
        try {
            File data = new File(path);
            Scanner sc = new Scanner(data);
            String taskToLoad;
            while (sc.hasNextLine()) {
                taskToLoad = sc.nextLine();
                tasks.add(taskToLoad);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            Ui.printErrorMessage(e);
        }
        return tasks;
    }

    /**
     * Updates and saves new tasks to duke.txt.
     *
     * @param tasks Task list to be updated.
     */
    public static void saveData(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (Task task : tasks) {
                String taskToWrite = task.taskToText() + "\n";
                fileWriter.write(taskToWrite);
            }
            fileWriter.close();
        } catch (IOException e) {
            Ui.printErrorMessage(e);
        }
    }


}