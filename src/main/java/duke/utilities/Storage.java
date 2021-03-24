package duke.utilities;

import duke.exception.InvalidFileFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a data handler to save and load a list of task in a specific format.
 */
public class Storage {

    private final Path filePath;

    private static final int TASK_TYPE_INDEX = 0;
    private static final int DONE_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int DATE_INDEX = 3;
    public static final int TODO_FORMAT = 3;
    public static final int EVENT_DEADLINE_FORMAT = 4;

    private static final String IS_DONE = "1";
    private static final String EVENT_TYPE = "[E]";
    private static final String DEADLINE_TYPE = "[D]";
    private static final String TODO_TYPE = "[T]";
    private static final String separator = " | ";

    /**
     * Construct Storage using a file path.
     *
     * @param path the default path to store task.
     */
    public Storage (String path) {
        this.filePath = new File(path).toPath();
    }

    /**
     * Load the saved data from a specific file location into TaskList.
     *
     * @return a list of task.
     * @throws FileNotFoundException if data is not found in the file path.
     */
    public ArrayList<Task> load() throws FileNotFoundException, InvalidFileFormatException {
        ArrayList<Task> tasksData = new ArrayList<>();
        readFileContents(tasksData);
        return tasksData;
    }

    /**
     * Save the TaskList into a local file.
     *
     * @param tasks he supply data to be saved.
     */
    public void save(TaskList tasks) throws IOException {
        writeToFile(tasks);
    }

    /**
     * Creates a directory from the specific file directory.
     * if the directory already exist, then no action is done.
     */
    public void createDirectory() throws IOException {
        Path directory = filePath.getParent();

        if (directory != null && !Files.isDirectory(directory)) {
            Files.createDirectories(directory);
        }

        if (!Files.isRegularFile(filePath)) {
            Files.createFile(filePath);
        }
    }

    private void readFileContents(ArrayList<Task> tasksData) throws FileNotFoundException,
            InvalidFileFormatException {
        File file = new File(String.valueOf(filePath));
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            String data = scanner.nextLine();
            addFileContents(data, tasksData);
        }
    }

    private void addFileContents(String data, ArrayList<Task> tasksData) throws InvalidFileFormatException {
        String[] contents  = splitContent(data);
        if (contents.length < TODO_FORMAT || contents.length > EVENT_DEADLINE_FORMAT) {
            throw new InvalidFileFormatException();
        }
        String taskType = contents[TASK_TYPE_INDEX].trim();
        String isDone = contents[DONE_INDEX].trim();
        String description = contents[DESCRIPTION_INDEX].trim();

        switch(taskType) {
        case EVENT_TYPE:
            if (contents.length != EVENT_DEADLINE_FORMAT) {
                throw  new InvalidFileFormatException();
            }
            String at = contents[DATE_INDEX].trim();
            Task event = new Event(description, at);
            checkIsDone(isDone, event);
            tasksData.add(event);
            break;
        case DEADLINE_TYPE:
            if (contents.length != EVENT_DEADLINE_FORMAT) {
                throw  new InvalidFileFormatException();
            }
            String by = contents[DATE_INDEX].trim();
            Task deadline = new Deadline(description, by);
            checkIsDone(isDone, deadline);
            tasksData.add(deadline);
            break;
        case TODO_TYPE:
            Task todo = new ToDo(description);
            checkIsDone(isDone, todo);
            tasksData.add(todo);
            break;
        }
    }

    private String[] splitContent(String content) {
        return content.split("\\|");
    }

    private void checkIsDone(String isDone, Task task) {
        if (isDone.equals(IS_DONE)) {
            task.setAsDone();
        }
    }

    private void writeToFile(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(String.valueOf(filePath));
        for (int i = 0; i < tasks.size(); ++i) {
            switch (tasks.get(i).getTaskType()) {
            case EVENT_TYPE:
                //Fallthrough
            case DEADLINE_TYPE:
                tasks.get(i).getDate();
                fileWriter.write(tasks.get(i).getTaskType() + separator
                        + tasks.get(i).getIsDone() + separator
                        + tasks.get(i).getDescription() + separator
                        + tasks.get(i).getDate()
                        + System.lineSeparator());
                break;
            case TODO_TYPE:
                fileWriter.write(tasks.get(i).getTaskType() + separator
                        + tasks.get(i).getIsDone() + separator
                        + tasks.get(i).getDescription()
                        + System.lineSeparator());
            }
        }
        fileWriter.close();
    }

}
