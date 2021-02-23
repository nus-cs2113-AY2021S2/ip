package duke.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;
import duke.task.TaskList;

public class Storage {

    private static Ui ui;

    public Storage(Ui uiObject) {
        ui = uiObject;
	}

    public void loadHistory(String home, TaskList taskList, Parser parser) {
        Path path = Paths.get(home, "data", "duke.txt");
        if (!Files.exists(path)) {
            return;
        }
        try {
            List<String> data = Files.readAllLines(path);
            for (String line : data) {
                Task task = parser.parseTask(line);
                taskList.addTask(task);
            }
        } catch (Exception e) {
            ui.showLoadingError(e);
            System.exit(1);
        }
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
