package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private static Path path;

    public Storage(Path path) {
        this.path = path;
    }

    /**
     * Reads the user's files and loads the task list with the tasks found in the files.
     * @return the loaded task list
     * @throws FileNotFoundException  If the file does not exist
     * @throws NoSuchElementException If the file is empty
     */
    public static ArrayList<Task> loadTasks() throws FileNotFoundException, NoSuchElementException {
        ArrayList<Task> tasks = new ArrayList<>();
        String loadedCommand;
        String[] words;
        Boolean isDone;
        File f = new File(String.valueOf(path));
        Scanner input = new Scanner(f);

        do {
            loadedCommand = input.nextLine();
            words = loadedCommand.split(",");

            if (words[words.length-1].contains("Y")) {
                isDone = true;
            } else {
                isDone = false;
            }
            if (loadedCommand.charAt(0) == 'T') {
                tasks.add(new Todo(words[1], isDone));
                Keyword.setKeywords("T");
            }
            if (loadedCommand.charAt(0) == 'E') {
                tasks.add(new Event(words[1], words[2], isDone));
                Keyword.setKeywords("E");
            }
            if (loadedCommand.charAt(0) == 'D') {
                tasks.add(new Deadline(words[1], words[2], isDone));
                Keyword.setKeywords("D");
            }
        } while (input.hasNextLine());
        return tasks;
    }

    /**
     * Writes the tasks into a formatted txt file
     * @param fw the FileWriter object created by using the Filewriter constructor, which took the
     * path of the saved file as the parameter
     * @throws IOException If the file path provided is invalid
     */
    public static void writeData(FileWriter fw) throws IOException {
        for (int i = 0; i < TaskList.getListSize(); ++i) {
            String keyword = Keyword.getKeywords(i);
            Task task = TaskList.getTaskAtIndex(i);
            fw.write(keyword + "," + TaskList.getTaskAtIndex(i).getDescription());
            if (keyword == "E") {
                fw.write(" , " + Event.getAt());
            } else if (keyword == "D") {
                fw.write(" , " + Deadline.getBy());
            }
            fw.write(" , " + task.getStatus());
            fw.write(System.lineSeparator());
        }
    }

    /**
     * Saves the tasks into the file at the specified path
     * Throws IOException if the path given is invalid
     */
    public static void saveData() {
        try {
            File path = new File("duke.txt");
            FileWriter fw = new FileWriter(path);
            writeData(fw);
            fw.close();
        } catch (IOException e) {
            Ui.printSaveError();
        }
    }

    /**
     * Creates a file at the specific path
     * Throws IOException when the path given is invalid
     */
    public static void createFile() {
        try {
            Files.createFile(path);
            Ui.printCreatedNewFile();
        } catch (IOException e) {
            Ui.printCreateFileError();
        }
    }
}
