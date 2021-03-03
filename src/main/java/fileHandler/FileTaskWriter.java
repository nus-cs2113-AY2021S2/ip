package fileHandler;

import task.Task;
import task.TaskList;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represent the handler that stores changes made by user input to the text file
 */
public class FileTaskWriter {
    private String filePath;
    public final static String SEPERATOR = " \\| ";

    public FileTaskWriter(String path) {
        filePath = path;
    }

    /**
     * store the current task list to the text file
     * @param taskList
     * @throws IOException if there is problem accessing or writing to the file
     */
    public void storeToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        String string = "";
        ArrayList<Task> tasks = taskList.getTasks();
        for(Task task : tasks) {
            string += task.toFile();
            string += "\n";
        }
        fileWriter.write(string);
        fileWriter.close();
    }
}
