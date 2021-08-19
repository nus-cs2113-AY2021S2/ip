package Duke.Storage;

import Duke.Parser.DukeParser;
import Duke.Task.Task;
import Duke.TaskManager.TaskManager;
import Duke.UI.DukeUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeStorage {
    public static final String FILE_PATH_TO_SAVE_TASKS = "duke.txt";

    /**
     * Perform a check on whether a file with name duke exist. If false, call createNewFile
     */
    public static void createFileIfThereIsNone() {
        File toCheck = new File(FILE_PATH_TO_SAVE_TASKS);
        if (!toCheck.exists()) {
            createNewFile(toCheck);
        }
    }

    private static void createNewFile(File fForCheck) {
        try {
            fForCheck.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear the file after the tasks from the file has been loaded into tasksList in Duke.
     */
    private static void emptyFileAfterInitializing() {
        FileWriter fw = null;
        fw = createFileWriterObject(null);
        writeEmptyStringToFile(fw);
    }

    private static FileWriter createFileWriterObject(FileWriter fw) {
        try {
            fw = new FileWriter(FILE_PATH_TO_SAVE_TASKS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fw;
    }


    private static void writeEmptyStringToFile(FileWriter fw) {
        try {
            fw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load data from Duke.txt into TaskManager and returns the TaskManager
     *
     * @return TaskManager object loaded with Tasks from Duke.txt
     */
    public static TaskManager loadData() {
        final TaskManager taskManager = new TaskManager();
        File f = new File(FILE_PATH_TO_SAVE_TASKS);
        DukeUI.print("Loading data");
        try {
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                String[] listOfDataFromFile = DukeParser.splitInputIntoString(s.nextLine());
                String userCommand = listOfDataFromFile[0];
                String inputDetails = listOfDataFromFile[1];
                Task newTask = DukeParser.processSavedData(userCommand, inputDetails);
                taskManager.addTask(newTask);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //emptyFileAfterInitializing();
        return taskManager;
    }

    /**
     * Write tasks into Duke.txt
     *
     * @param filePath Name and location of destinaton file
     * @param tasks Task to be written
     * @throws IOException If there is I/O errors
     */
    private static void writeToFile(String filePath, Task tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String description = tasks.getDescriptionWithoutBrackets();
        String taskType = tasks.getTaskTypeInWords();
        Boolean status = tasks.getStatusInWords();
        if (status) {
            fw.write(taskType + "done " + description + "\n");
        } else {
            fw.write(taskType + ' ' + description + "\n");
        }
        fw.close();
    }

    /**
     * Clear the file before saving the new list of Tasks.
     * Print exiting message and end the program.
     *
     * @param latestTaskManager Most updated version of Task Manager
     * @throws IOException If there is I/O error
     */
    private static void exitDuke(TaskManager latestTaskManager) throws IOException {
        emptyFileAfterInitializing();
        DukeUI.printLine();
        ArrayList<Task> finalTasksList = latestTaskManager.returnTaskList();
        for (Task task : finalTasksList) {
            try {
                writeToFile(FILE_PATH_TO_SAVE_TASKS, task);
            } catch (IOException e) {
                DukeUI.print("Something went wrong: " + e.getMessage());
            }
        }
        DukeUI.printExitingMessage();
    }

    public static void endDuke(TaskManager latestTaskManager) {
        try {
            exitDuke(latestTaskManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
