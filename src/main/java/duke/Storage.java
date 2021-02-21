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
 * Deals with loading tasks from the file and saving tasks in the file. 
 */
public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Read the file contents and add tasks to task list.
     * 
     * @throws IOException           If there is an error opening file.
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
            importedTask = markTask(importedTask, extractedTaskParameters[Constants.MARK_INDEX]);
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
     * Writes the new task list into file.
     * 
     * @throws IOException If there is an error writing to file.
     */
    private void writeToFile(TaskList tasks, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file.getAbsolutePath(), false);
        fileWriter.write(convertToFileFormat(tasks));
        fileWriter.close();
    }

    /**
     * Converts the task list into storage format.
     */
    private String convertToFileFormat(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            message.append(tasks.getTask(i).toStorageString() + System.lineSeparator());
        }
        return message.toString();
    }

    
    /**
     * Adds the tasks from storage to the task list. 
     * 
     * @param data List of parameters in storage file. 
     */
    private Task createTask(String[] extractedTaskParameters) throws ImportTaskException {
        Task importedTask;
        AddCommand addCommand = new AddCommand(extractedTaskParameters);
        switch(extractedTaskParameters[Constants.COMMAND_INDEX]) {
        case "todo":
            importedTask = addCommand.addTodoFromStorage();
            break;
        case "deadline":
            importedTask = addCommand.addDeadlineFromStorage();
            break;
        case "event":
            importedTask = addCommand.addEventFromStorage();
            break;
        default:
            throw new ImportTaskException();
        }

        return importedTask;
    }

    private Task markTask(Task importedTask, String isDone) {
        if (isDone.equals("true")) {
            importedTask.setTaskStatus();
        }
        return importedTask;
    }
}
