package duke.storage;

import duke.Command;
import duke.list.TaskList;
import duke.exceptions.InvalidCommandException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the Storage used to store the tasks.
 */
public class Storage {
    private static final String DIRECTORY_NAME = "data";
    private static final String FILE_NAME = "duke.txt";
    private static final String DONE = "1";

    /**
     * Loads tasks from file into taskList.
     *
     * @param taskList ArrayList of tasks
     * @throws IOException             If I/O is failed or interrupted
     * @throws InvalidCommandException If file was corrupted and wrong command is given
     * @throws SecurityException       If there is a security violation in accessing the file
     */
    public static void loadFile(TaskList taskList) throws IOException, InvalidCommandException, SecurityException {
        createDirectory();
        createFile();
        loadTasks(taskList);
    }

    private static void createDirectory() throws SecurityException {
        File directory = new File(DIRECTORY_NAME);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private static void createFile() throws SecurityException, IOException {
        File file = new File(DIRECTORY_NAME + "/" + FILE_NAME);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private static void loadTasks(TaskList taskList) throws FileNotFoundException, InvalidCommandException {
        File file = new File(DIRECTORY_NAME + "/" + FILE_NAME);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            processTasks(taskList, scanner.nextLine());
        }
    }

    private static void processTasks(TaskList taskList, String task) throws InvalidCommandException {
        String[] taskArray = task.split("\\|");

        switch (Parser.getCommand(taskArray[0])) {
        case TODO:
            taskList.addTask(taskArray[2], Command.TODO);
            break;
        case EVENT:
            taskList.addTask(taskArray[2], Command.EVENT);
            break;
        case DEADLINE:
            taskList.addTask(taskArray[2], Command.DEADLINE);
            break;
        }
        if (taskArray[1].equals(DONE)) {
            taskList.finishLastTask();
        }
    }

    /**
     * Saves tasks into file from taskList.
     *
     * @param taskList ArrayList of tasks
     * @throws IOException       If I/O is failed or interrupted
     * @throws SecurityException If there is a security violation in accessing the file
     */
    public static void saveFile(TaskList taskList) throws IOException, SecurityException {
        createDirectory();
        createFile();
        saveTasks(taskList);
    }

    private static void saveTasks(TaskList taskList) throws IOException {
        FileWriter writer = new FileWriter(DIRECTORY_NAME + "/" + FILE_NAME);
        int taskCount = taskList.getSize();
        for (int i = 0; i < taskCount; i++) {
            Task task = taskList.getTask(i);
            String outputString = processTasks(task) + "\n";
            writer.write(outputString);
        }
        writer.close();
    }

    private static String processTasks(Task task) {
        String[] outputArray = new String[3];
        if (task instanceof ToDo) {
            outputArray[0] = "todo ";
            outputArray[2] = task.getDescription();
        } else if (task instanceof Deadline) {
            outputArray[0] = "deadline ";
            outputArray[2] = task.getDescription() + "/by " + ((Deadline) task).getEndTime();
        } else if (task instanceof Event) {
            outputArray[0] = "event ";
            outputArray[2] = task.getDescription() + "/at " + ((Event) task).getEventTime();
        }
        if (task.isDone()) {
            outputArray[1] = "|1|";
        } else {
            outputArray[1] = "|0|";
        }
        return String.join("", outputArray);
    }

}
