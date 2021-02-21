package duke.data;

import duke.exception.LoadDataException;
import duke.parser.DataParser;
import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class DataManager {

    private final String FILEPATH;
    private DataParser parser;

    public DataManager(String FILEPATH) {
        this.FILEPATH = FILEPATH;
        parser = new DataParser();
    }

    public void saveData(ArrayList<Task> tasks) {

        StringBuilder data = new StringBuilder();

        for (Task task : tasks) {
            String taskString = parser.parseTaskToString(task);
            data.append(taskString);
            data.append(System.lineSeparator());
        }

        try {
            FileWriter writer = new FileWriter(FILEPATH);
            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + FILEPATH);
        }
    }

    public ArrayList<Task> loadData() {

        File file = new File(FILEPATH);
        ArrayList<Task> taskList = null;

        try {
            ArrayList<String> taskStringList = readFile(file);
            taskList = parser.parseTaskString(taskStringList);
        } catch (IOException e) {
            System.out.println("Error creating file: " + FILEPATH);
            System.out.println("Data will not be saved in " + FILEPATH);
            return null;
        } catch (LoadDataException e) {
            System.out.println("Error loading file: " + FILEPATH);
            System.out.println("Existing file will be overwritten");
        }

        return taskList;
    }

    private ArrayList<String> readFile(File file) throws IOException {

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }

        return (ArrayList<String>) Files.readAllLines(file.toPath(), Charset.defaultCharset());
    }

}
