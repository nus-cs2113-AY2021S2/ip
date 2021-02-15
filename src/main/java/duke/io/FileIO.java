package duke.io;

import duke.InvalidFieldException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileIO {
    private static final Path DATA_DIRECTORY_PATH = Paths.get(".", "data");
    private static final Path DATA_FILE_PATH = DATA_DIRECTORY_PATH.resolve("duke.txt");
    private static final String TODO_REGEX_PATTERN = "[T]\\s\\|\\s[01]\\s\\|\\s.+";
    private static final String DEADLINE_REGEX_PATTERN = "[D]\\s\\|\\s[01]\\s\\|\\s[^\\|]+\\s\\|\\s.+";
    private static final String EVENT_REGEX_PATTERN = "[E]\\s\\|\\s[01]\\s\\|\\s[^\\|]+\\s\\|\\s.+";

    private static boolean directoryExists(Path path) {
        return Files.exists(path);
    }

    private static boolean fileExists(Path dirPath, Path filePath) {
        return directoryExists(dirPath) && Files.exists(filePath);
    }

    public static void writeTasksToFile(ArrayList<Task> tasks) throws IOException {
        if (!directoryExists(DATA_DIRECTORY_PATH)) {
            Files.createDirectories(DATA_DIRECTORY_PATH);
        }
        FileWriter fw = new FileWriter(DATA_FILE_PATH.toString(), false);
        for (Task t : tasks) {
            fw.write(t.toFileEntry() + System.lineSeparator());
        }
        fw.close();
    }

    public static ArrayList<Task> retrieveTasksFromFile() {
        ArrayList<Task> tasks =  new ArrayList<>();
        if (!fileExists(DATA_DIRECTORY_PATH, DATA_FILE_PATH)) {
            return tasks;
        }
        try {
            File taskFile = DATA_FILE_PATH.toFile();
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseAndMatchTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (Exception e) {
            return tasks;
        }
    }

    private static String extractDataField(int fieldIndex, String line) throws InvalidFieldException {
        String[] fields = line.split("\\|");
        if (fieldIndex >= 0 && fieldIndex < fields.length) {
            return fields[fieldIndex].trim();
        }
        throw new InvalidFieldException();
    }

    private static boolean extractIsDone(String line) throws InvalidFieldException {
        return extractDataField(1, line).equals("1");
    }

    private static Todo extractTodo(String line) throws InvalidFieldException {
        boolean isDone = extractIsDone(line);
        String description = extractDataField(2, line);
        return new Todo(description, isDone);
    }

    private static Deadline extractDeadline(String line) throws InvalidFieldException {
        boolean isDone = extractIsDone(line);
        String description = extractDataField(2, line);
        String by = extractDataField(3, line);
        return new Deadline(description, isDone, by);
    }

    private static Event extractEvent(String line) throws InvalidFieldException {
        boolean isDone = extractIsDone(line);
        String description = extractDataField(2, line);
        String at = extractDataField(3, line);
        return new Event(description, isDone, at);
    }

    public static Task parseAndMatchTask(String line) {
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
