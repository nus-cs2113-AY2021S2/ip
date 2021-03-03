package fileHandler;

import task.Task;
import task.TaskList;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileTaskWriter {
    private String filePath;
    public final static String SEPERATOR = " \\| ";

    public FileTaskWriter(String path) {
        filePath = path;
    }

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
