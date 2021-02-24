import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final String DIRECTORY_HOME = System.getProperty("user.home");
    public static final String DATA_DELIMITER = " @_@ ";
    public static final String DATA_DIRECTORY = "data";
    public static final String SAVE_FILE_NAME = "duke.txt";

    protected String filePath;
    protected Ui ui;

    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    public List<String> load() {
        createDataDirectory();
        return openFile();
    }

    public void createDataDirectory() {
        Path path = Paths.get(DIRECTORY_HOME, "data");
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            //ui.printIOException();
        }
    }

    public List<String> openFile() {
        Path path = Paths.get(DIRECTORY_HOME, DATA_DIRECTORY, SAVE_FILE_NAME);
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
            taskStrings = loadDataFile(path);
        } catch (IOException e) {
            ui.printIOException();
        }
        return taskStrings;
    }

    private List<String> loadDataFile(Path path) throws IOException{
        return Files.readAllLines(path);
    }

    public void saveData(TaskList tasks) {
        Path path = Paths.get(DIRECTORY_HOME, DATA_DIRECTORY, SAVE_FILE_NAME);
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
