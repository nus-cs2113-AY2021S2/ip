package fileHandler;


import task.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class that deals with loading from and storing to the text file
 */
public class Storage {
    private FileTaskReader fileTaskReader;
    private FileTaskWriter fileTaskWriter;
    private String filePath;
    private File source;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        source = new File(filePath);
        if (!source.exists()) {
            if (!source.getParentFile().exists()) {
                source.getParentFile().mkdirs();
                source.createNewFile();
            }
        }
        fileTaskWriter = new FileTaskWriter(filePath);
        fileTaskReader = new FileTaskReader(source);
    }

    public TaskList getTaskList() throws FileNotFoundException {
        return new TaskList(fileTaskReader.parseToArraylist());
    }

    public void store(TaskList tasks) throws IOException{
        fileTaskWriter.storeToFile(tasks);
    }

}
