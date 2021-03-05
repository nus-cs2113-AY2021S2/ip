package fileHandler;

import task.Task;
import task.TaskList;

import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Represents the handler that update current task list to the text file
 */
public class FileTaskWriter {
    private String filePath;

    public FileTaskWriter(String path) {
        filePath = path;
    }

    /**
     * Stores the current task list to the text file.
     *
     * @param taskList
     * @throws IOException if there is problem accessing or writing to the file
     */
    public void storeToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        String string = "";
        ArrayList<Task> tasks = taskList.getTasks();
        for(Task task : tasks) {
            string += task.getTaskInfoForStorage();
            string += "\n";
        }
        fileWriter.write(string);
        fileWriter.close();
    }
}
