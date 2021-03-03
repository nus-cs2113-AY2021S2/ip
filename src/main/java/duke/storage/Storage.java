package duke.storage;

import duke.exception.DataFileNotFoundException;
import duke.exception.DukeIOException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "duke.txt");
    private static final Path dirPath = Paths.get(root, "data");

    /**
     * Constructor
     *
     * Creates a new directory based on dirPath if directory does not exist.
     * Creates a new file based on filePath if file does not exist.
     *
     * @throws DukeIOException if an IO error occurs
     */
    public Storage() throws DukeIOException {
        try{
            File fileDirectory = new File(dirPath.toString());
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
            }

            File dataFile = new File(filePath.toString());
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new DukeIOException();
        }
    }

    /**
     * Loads saved task information from data file.
     * Returns the tasks in an ArrayList of Tasks.
     *
     * @return ArrayList of tasks saved in file
     * @throws DataFileNotFoundException if file does not exist
     */
    public ArrayList<Task> loadFile() throws DataFileNotFoundException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();

            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String type = line.substring(0, 1);
                String info = line.substring(8);
                int dateIndex = info.indexOf('|');
                switch (type) {
                case "T":
                    tasks.add(new Todo(info));
                    break;
                case "D":
                    tasks.add(new Deadline(info.substring(0, dateIndex - 1), info.substring(dateIndex + 2)));
                    break;
                case "E":
                    tasks.add(new Event(info.substring(0, dateIndex - 1), info.substring(dateIndex + 2)));
                    break;
                default:
                    break;
                }
                if (line.charAt(4) == 'Y') {
                    tasks.get(tasks.size() - 1).setAsDone();
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DataFileNotFoundException();
        }
    }

    /**
     * Saves tasks to data file
     *
     * @param tasks ArrayList of Tasks to be saved
     */
    public void saveFile(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath.toString());
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    writer.write("T | " + task.getStatusIcon() + " | " + task.getDescription() + System.lineSeparator());
                } else if (task instanceof Deadline) {
                    writer.write("D | " + task.getStatusIcon() + " | " + task.getDescription() + " | " + ((Deadline) task).getBy() + System.lineSeparator());
                } else if (task instanceof Event) {
                    writer.write("E | " + task.getStatusIcon() + " | " + task.getDescription() + " | " + ((Event) task).getAt() + System.lineSeparator());
                } else {
                    return;
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
