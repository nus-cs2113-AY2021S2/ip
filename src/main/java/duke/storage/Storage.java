package duke.storage;

import duke.data.exceptions.InvalidFieldException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private static final Path DATA_DIRECTORY_PATH = Paths.get(".", "data");
    private static final Path DATA_FILE_PATH = DATA_DIRECTORY_PATH.resolve("duke.txt");
    private static final String TODO_REGEX_PATTERN = "[T]\\s\\|\\s[01]\\s\\|\\s.+";
    private static final String DEADLINE_REGEX_PATTERN = "[D]\\s\\|\\s[01]\\s\\|\\s[^|]+\\s\\|\\s.+";
    private static final String EVENT_REGEX_PATTERN = "[E]\\s\\|\\s[01]\\s\\|\\s[^|]+\\s\\|\\s.+";

    private Path dataDirectoryPath;
    private Path dataFilePath;

    public Storage() {
        this(DATA_DIRECTORY_PATH, DATA_FILE_PATH);
    }

    public Storage(Path dataDirectoryPath, Path dataFilePath) {
        this.dataDirectoryPath = dataDirectoryPath;
        this.dataFilePath = dataFilePath;
    }

    private boolean directoryExists(Path path) {
        return Files.exists(path);
    }

    private boolean fileExists(Path dirPath, Path filePath) {
        return directoryExists(dirPath) && Files.exists(filePath);
    }

    /**
     * Writes (Overwrites) all the tasks to file stored at dataFilePath.
     * Creates a directory if directory does not exist.
     *
     * @param tasks an ArrayList of Task objects
     * @throws IOException If an I/O error occurs
     */
    public void writeTasksToFile(ArrayList<Task> tasks) throws IOException {
        if (!directoryExists(dataDirectoryPath)) {
            Files.createDirectories(dataDirectoryPath);
        }
        FileWriter fw = new FileWriter(dataFilePath.toString(), false);
        for (Task t : tasks) {
            fw.write(t.toFileEntry() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Retrieve all tasks from file stored at dataFilePath.
     * Rejects any corrupted line in the file (line that does not
     * match regex pattern).
     *
     * @return a Task ArrayList retrieved from file.
     * @see #matchAndParseTask(String)
     */
    public ArrayList<Task> retrieveTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!fileExists(dataDirectoryPath, dataFilePath)) {
            return tasks;
        }
        try {
            File taskFile = dataFilePath.toFile();
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = matchAndParseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (Exception e) {
            return tasks;
        }
    }

    /**
     * Extracts data from a data field based on the fieldIndex.
     * The line contains a set of data fields.
     * An example of a line is: "D | 1 | math hw | 27-02-2021 00:50"
     * Each data field is separated by the pipe symbol.
     *
     * @param fieldIndex the index in the field to read from.
     * @param line a string containing a set of data fields.
     * @return a string of data extracted from the data field.
     * @throws InvalidFieldException If field is corrupted or missing.
     */
    private String extractDataField(int fieldIndex, String line) throws InvalidFieldException {
        String[] fields = line.split("\\|");
        if (fieldIndex >= 0 && fieldIndex < fields.length) {
            return fields[fieldIndex].trim();
        }
        throw new InvalidFieldException();
    }

    /**
     * Extracts the isDone boolean from the data field (index 1).
     *
     * @param line a string containing a set of data fields.
     * @return the extracted boolean from the data field
     * @throws InvalidFieldException If field is corrupted or missing.
     */
    private boolean extractIsDone(String line) throws InvalidFieldException {
        return extractDataField(1, line).equals("1");
    }

    /**
     * Extracts the information required for creating a Todo object.
     *
     * @param line a string containing a set of data fields.
     * @return the created Todo object
     * @throws InvalidFieldException If field is corrupted or missing.
     */
    private Todo extractTodo(String line) throws InvalidFieldException {
        boolean isDone = extractIsDone(line);
        String description = extractDataField(2, line);
        return new Todo(description, isDone);
    }

    /**
     * Extracts the information required for creating a Deadline object.
     *
     * @param line a string containing a set of data fields.
     * @return the created Deadline object
     * @throws InvalidFieldException If field is corrupted or missing.
     */
    private Deadline extractDeadline(String line) throws InvalidFieldException {
        boolean isDone = extractIsDone(line);
        String description = extractDataField(2, line);
        String by = extractDataField(3, line);
        return new Deadline(description, isDone, by);
    }

    /**
     * Extracts the information required for creating a Event object.
     *
     * @param line a string containing a set of data fields.
     * @return the created Event object
     * @throws InvalidFieldException If field is corrupted or missing.
     */
    private Event extractEvent(String line) throws InvalidFieldException {
        boolean isDone = extractIsDone(line);
        String description = extractDataField(2, line);
        String at = extractDataField(3, line);
        return new Event(description, isDone, at);
    }

    /**
     * Performs a regex pattern match on the line consisting of
     * data fields. Each correct match yields either a Todo object,
     * Deadline object or Event object. All are returned as a Task
     * object.
     * Returns null if pattern does not match or data cannot be
     * extracted.
     *
     * @param line a string consisting of data fields
     * @return a Task object if matching and data extraction is
     *         successful.
     */
    public Task matchAndParseTask(String line) {
        try {
            if (Pattern.matches(TODO_REGEX_PATTERN, line)) {
                return extractTodo(line);
            } else if (Pattern.matches(DEADLINE_REGEX_PATTERN, line)) {
                return extractDeadline(line);
            } else if ((Pattern.matches(EVENT_REGEX_PATTERN, line))) {
                return extractEvent(line);
            }
        } catch (InvalidFieldException e) {
            return null;
        }
        return null;
    }
}
