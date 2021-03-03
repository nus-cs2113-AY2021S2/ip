package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a storage that deals with the saving and loading
 * of all tasks in the computer
 */
public class Storage {

    /**
     * Checks if there is an existing previously saved file and call
     * the private method <code>initializeExistingTasks</code> to load the tasks
     * <p>
     * If there is no existing saved file, a new file is created.
     * <p>
     * If the file creation failed, the exception thrown will be captured
     */
    public static void downloadTask() {
        try {
            File dataDir = new File("data");
            dataDir.mkdir();
            File dataFile = new File("data/tasks.txt");
            if (!dataFile.createNewFile()) {
                initializeExistingTasks(dataFile);
            }
        } catch (IOException e) {
            System.out.println("File creation failed.");
            e.printStackTrace();
        }

    }

    private static void initializeExistingTasks(File dataFile) throws java.io.IOException {
        Scanner s = new Scanner(dataFile);
        Task t = null;
        while (s.hasNext()) {
            String commandInput = s.nextLine();
            if (commandInput.startsWith("T")) {
                String description = commandInput.substring(8);
                t = new Todo(description);
                TaskList.add(t);
            } else if (commandInput.startsWith("D")) {
                int timeIndex = commandInput.lastIndexOf("| ");
                String description = commandInput.substring(8, timeIndex - 1);
                t = new Deadline(description, commandInput.substring(timeIndex + 2));
                TaskList.add(t);
            } else if (commandInput.startsWith("E")) {
                int timeIndex = commandInput.lastIndexOf("| ");
                String description = commandInput.substring(8, timeIndex - 1);
                t = new Event(description, commandInput.substring(timeIndex + 2));
                TaskList.add(t);
            }
            if (commandInput.charAt(4) == '1') {
                t.setDone();
            }
        }
    }


    /**
     * Calls the private method <code>processUploadTasks</code>
     * to save the file
     * <p>
     * If the file saving process failed, the exception thrown
     * will be captured
     */
    public static void uploadTasks() {
        try {
            processUploadTasks();
        } catch (IOException e) {
            System.out.println("Failed to save file.");
            e.printStackTrace();
        }

    }

    private static void processUploadTasks() throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        for (int i = 0; i < TaskList.getTaskCount(); ++i) {
            Task t = TaskList.getTask(i + 1);
            int taskStatus = 0;
            if (t.isDone) {
                taskStatus = 1;
            }
            if (t.getAlphabet().equals("T")) {
                fw.write("T | " + taskStatus + " | " + t.description + System.lineSeparator());
            } else {
                fw.write(t.getAlphabet() + " | " + taskStatus + " | " +
                        t.description + " | " + t.time + System.lineSeparator());
            }
        }
        fw.close();
    }
}
