package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeFileManager {
    private final static int MINIMUM_NUMBER_OF_ARGS = 3;
    private final static int OPTIONAL_NUMBER_OF_ARGS = 4;

    private final static int IS_DONE_INDEX = 0;
    private final static int TASK_TYPE_INDEX = 1;
    private final static int DESCRIPTION_INDEX = 2;
    private final static int DATE_INDEX = 3;

    private String directory, filename;
    private File file;
    private Scanner reader;
    private FileWriter writer;

    public DukeFileManager(String directory, String filename) {
        this.directory = directory;
        this.filename = filename;
        this.reader = null;
    }

    private void openFile() throws IOException {
        File fileDirectory = new File(directory);
        if (!fileDirectory.exists()) {
            fileDirectory.mkdir();
        }
        file = new File(directory + filename);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public ArrayList<String> readFromFile() throws IOException {
        openFile();
        reader = new Scanner(file);
        ArrayList<String> taskStrings = new ArrayList<String>();
        while (reader.hasNext()) {
            String taskString = reader.nextLine();
            taskStrings.add(taskString);
        }
        reader.close();
        return taskStrings;
    }

    public void writeToFile(ArrayList<String> tasksAsCSV) throws IOException {
        openFile();
        writer = new FileWriter(file);
        for (String taskCSV : tasksAsCSV) {
            writer.write(taskCSV + "\n");
        }
        writer.close();
    }
}
