package duke.storage;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Loads tasks from duke.txt or
 * creates and saves new tasks to duke.txt
 */

public class Storage {
    private static String path;

    public Storage() {
        this.path = System.getProperty("user.dir") + "/data/duke.txt";
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
     * Checks if text file exists
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
     * Loads in tasks from duke.txt
     *
     * @throws FileNotFoundException if duke.txt does not exist
     */
    public void loadData() {
        try {
            File data = new File(path);
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                String taskToLoad = sc.nextLine();
                TaskList.tasks.add(Task.textToTask(taskToLoad));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            Ui.printErrorMessage(e);
        }
    }

    /**
     * Appends and saves new tasks to duke.txt
     */
    public static void saveData() {
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (int i = 0; i < TaskList.tasks.size(); ++i) {
                //get the task list items
                String taskToWrite = TaskList.tasks.get(i).taskToText() + "\n";
                fileWriter.write(taskToWrite);
            }
            fileWriter.close();
        } catch (IOException e) {
            Ui.printErrorMessage(e);
        }
    }


}