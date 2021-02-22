package duke.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class Storage {

    private static Ui ui;

    public Storage(Ui uiObject) {
        ui = uiObject;
	}

    public void loadHistory(String home, TaskList taskList) {
        Path path = Paths.get(home, "data", "duke.txt");
        if (!Files.exists(path)) {
            return;
        }
        try {
            List<String> data = Files.readAllLines(path);
            for (String line : data) {
                Task task = parseTask(line);
                taskList.addTask(task);
            }
        } catch (Exception e) {
            ui.showLoadingError(e);
        }
    }

    public static Task parseTask(String line) {
        String[] tokens = line.split("~");
        String taskType = tokens[0];
        String isDone = tokens[1];
        String description = tokens[2];
        Task task = new Task(description);
        switch (taskType) {
            case "Todo":
                task = new Todo(description);
                break;
            case "Deadline":
                String by = tokens[3];
                task = new Deadline(description, by);
                break;
            case "Event":
                String at = tokens[3];
                task = new Event(description, at);
                break;
        }
        if (isDone == String.valueOf(true)) {
            task.setIsDone();
        }
        return task;
    }    

	public void saveHistory(String home, TaskList taskList) {
	    try {
	        Path directoryPath = Paths.get(home, "data");
	        if (Files.notExists(directoryPath)) {
	            Files.createDirectory(directoryPath);
	        }
	
	        Path filePath = Paths.get(home, "data", "duke.txt");
	        Files.deleteIfExists(filePath);
	        Files.createFile(filePath);
	        ArrayList<String> listOfTaskDetails = taskList.getListOfTaskDetails();
	        Files.write(filePath, listOfTaskDetails);
	    } catch (Exception e) {
	        ui.showSavingError(e);
	    }
	}
    
}
