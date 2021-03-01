import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the read and write operations to save file
 */

public class Storage {
    public static final String DIRECTORY_HOME = System.getProperty("user.dir");
    public static final String DATA_DELIMITER = " @_@ ";

    protected String filePath;
    protected Ui ui;

    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Reads the save file and return the list of tasks
     * @return List of tasks in saved file format
     */
    public List<String> load() {
        createDataDirectory();
        return openFile();
    }

    /**
     * Creates the save file directory if it does not exist
     */
    public void createDataDirectory() {
        Path path = Paths.get(DIRECTORY_HOME, filePath);
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            ui.printIOException();
        }
    }

    /**
     * Creates the file if it does not exist
     * If the file exists, read the file instead
     * @return Contents of the file, empty if just created
     */
    private List<String> openFile() {
        Path path = Paths.get(DIRECTORY_HOME, filePath);
        List<String> taskStrings = new ArrayList<>();

        try {
            Files.createFile(path);
        } catch (FileAlreadyExistsException e) {
            taskStrings = readFile(path);
        } catch (IOException e) {
            ui.printIOException();
        }

        return taskStrings;
    }

    private List<String> readFile(Path path) {
        List<String> taskStrings = new ArrayList<>();

        try {
            taskStrings = Files.readAllLines(path);
        } catch (IOException e) {
            ui.printIOException();
        }
        return taskStrings;
    }

    /**
     * Saves tasks into the save file
     * Overrides the old file completely
     * @param tasks list of tasks of Bob class
     */
    public void saveData(TaskList tasks) {
        Path path = Paths.get(DIRECTORY_HOME, filePath);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            List<String> taskStrings = tasks.saveTaskList();
            Files.write(path, taskStrings);
        } catch (IOException e) {
            ui.printIOException();
        }
    }
}
