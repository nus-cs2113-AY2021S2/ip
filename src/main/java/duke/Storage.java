package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.TaskList.taskList;

/**
 * Represents the file used to store Duke data.
 */
public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the <code>TaskList</code> data to the storage file.
     *
     * @throws IOException If there is a failure during reading, writing and searching file or directory operations.
     */
    public static void save() throws IOException {
        int index = 0;
        FileWriter fileWriter = new FileWriter("data/duke.txt");
        while (index < taskList.size()) {
            fileWriter.write(taskList.get(index).saveTask() + System.lineSeparator());
            index++;
        }
        fileWriter.close();
    }

    /**
     * Loads the <code>TaskList</code> data from this storage file, and then returns it.
     * Returns an empty <code>TaskList</code> if the file does not exist, or is not a regular file.
     *
     * @return Task data loaded from the storage file.
     * @throws IOException If there is a failure during reading, writing and searching file or directory operations.
     */
    public List<String> load() throws IOException {
        List<String> loadTasks = new ArrayList<>();
        File file = new File(filePath);
        file.getParentFile().mkdir();
        file.createNewFile();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            loadTasks.add(scanner.nextLine());
        }
        return loadTasks;
    }

}
