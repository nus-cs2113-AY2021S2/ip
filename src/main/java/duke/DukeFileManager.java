package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages reading and writing data to and from files.
 */
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

    /**
     * Creates a new DukeFileManager object pointing to the specified filepath
     *
     * @param directory the directory to read/write the file from
     * @param filename the file to be read/written to.
     */
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

    /**
     * Reads task data from the specified CSV file, and returns the imported data as an ArrayList of Strings
     *
     * @return an ArrayList of Strings containing each line from the input file
     */
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

    /**
     * Writes task data to the specified CSV file.
     *
     * @param tasksAsCSV an ArrayList of Strings containing task information.
     */
    public void writeToFile(ArrayList<String> tasksAsCSV) throws IOException {
        openFile();
        writer = new FileWriter(file);
        for (String taskCSV : tasksAsCSV) {
            writer.write(taskCSV + "\n");
        }
        writer.close();
    }
}
