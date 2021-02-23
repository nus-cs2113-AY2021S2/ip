package duke;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import duke.error.*;
import java.util.Scanner;
import java.io.IOException;
import duke.task.*;
import duke.command.*;

/**
 * Represents an instance of a storage. An Storage object corresponds to the saving 
 * and loading of stored data for an application. 
 */
public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the file contents and add tasks to task list. 
     * Sets the task status depending on status data in storage file. 
     * 
     * @throws IOException If there is an error opening file.
     * @throws FileNotFoundException If the file is not found.
     */
    protected ArrayList<Task> readFromFile() throws ImportTaskException, FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        Scanner fileScanner = new Scanner(file);
        ArrayList<Task> importedTasks = new ArrayList<Task>();
        while (fileScanner.hasNextLine()) {
            String[] extractedTaskParameters = fileScanner.nextLine().split(", ");
            Task importedTask = createTask(extractedTaskParameters);
            importedTask.setTaskStatus();
            importedTasks.add(importedTask);
        }
        fileScanner.close();
        return importedTasks;
    }

    /**
     * Checks for presence of file before saving. If file is not found, create file
     * for storing task list. Then, store task list into file.
     * 
     * @throws IOException If there is an error reating file.
     */
    public void saveToFile(TaskList tasks) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        writeToFile(tasks, file);
    }

    /**
     * Writes the task list into file.
     * 
     * @throws IOException If there is an error writing to file.
     */
    private void writeToFile(TaskList tasks, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file.getAbsolutePath(), false);
        fileWriter.write(convertToFileFormat(tasks));
        fileWriter.close();
    }

    /**
     * Converts the task list into a string in the storage format.
     */
    private String convertToFileFormat(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            message.append(tasks.getTask(i).toStorageString() + System.lineSeparator());
        }
        return message.toString();
    }

    /**
     * Adds the tasks from storage to the task list according to the type of task. 
     * 
     * @param data List of parameters in storage file. 
     */
    private Task createTask(String[] extractedTaskParameters) throws ImportTaskException {
        AddCommand addCommand = new AddCommand(extractedTaskParameters);
        switch(extractedTaskParameters[Constants.COMMAND_INDEX]) {
        case "todo":
            return addCommand.addTodoFromStorage();
        case "deadline":
            return addCommand.addDeadlineFromStorage();
        case "event":
            return addCommand.addEventFromStorage();
        default:
            throw new ImportTaskException();
        }
    }
}
