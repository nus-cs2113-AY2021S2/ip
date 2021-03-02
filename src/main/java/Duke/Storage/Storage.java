package Duke.Storage;

import Duke.Tasks.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Reads and writes list of task to file
 * Storage class is written to write and save data into a file. More prominent methods like createfile, loadfile and writetofile
 */

public class Storage {

    private final String filePath;
    private final File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Creates a data file and its parent directory if it does not already exists
     *
     * @throws IOException When the file path is not available
     */

    public void createFile() throws IOException {
        Path pathToFile = Paths.get(filePath);
        Files.createDirectories(pathToFile.getParent());
        file.createNewFile();
    }

    /**
     * Loads user data from file into loadedTask list.
     *
     * @return a loaded arraylist filled with data read from text file
     * @throws FileNotFoundException When file does not exist
     */

    public TaskList loadFile() throws FileNotFoundException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        File fileName = new File(filePath);
        Scanner fileReader = new Scanner(fileName);
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();

            switch (data.charAt(0)) {
            case ('D'): {
                loadedTasks.add(loadDeadline(data));
                break;
            }
            case ('T'): {
                loadedTasks.add(loadTodo(data));
                break;
            }
            case ('E'): {
                loadedTasks.add(loadEvent(data));
                break;
            }
            }
        }
        fileReader.close();
        return new TaskList(loadedTasks);
    }

    /**
     * Writes list of task to file
     *
     * @param taskList list of task to write to data
     */

    public void writeToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, false);
        fileWriter.write("");
        for (Task task : taskList) {
            fileWriter.write(task.saveTask() + "\n");
        }
        fileWriter.close();
    }

    /**
     * Load Event task into Task List format to be added into array list
     *
     * @param inputs a string from text file for event
     * @return task type Event.
     */

    public Event loadEvent(String inputs) {
        String[] inputArray = inputs.split("\\s\\|\\s", 4);

        String doneCount = inputArray[1];
        String description = inputArray[2];
        String at = inputArray[3];

        Event newEvent = new Event(description, at);
        if (doneCount.equals("1")) newEvent.markAsDone();

        return newEvent;

    }

    /**
     * Load Deadline task into Task List format to be added into array list
     *
     * @param inputs a string from text file for event
     * @return deadline type Event.
     */

    public Deadline loadDeadline(String inputs) {
        String[] inputArray = inputs.split("\\s\\|\\s", 4);

        String doneCount = inputArray[1];
        String description = inputArray[2];
        String by = inputArray[3];

        Deadline newDeadline = new Deadline(description, by);
        if (doneCount.equals("1")) newDeadline.markAsDone();

        return newDeadline;

    }

    /**
     * Load Todo task into Task List format to be added into array list
     *
     * @param inputs a string from text file for event
     * @return todo type Event.
     */

    public Todo loadTodo(String inputs) {
        String[] inputArray = inputs.split("\\s\\|\\s", 4);

        String doneCount = inputArray[1];
        String description = inputArray[2];

        Todo newTodo = new Todo(description);
        if (doneCount.equals("1")) newTodo.markAsDone();

        return newTodo;
    }
}
