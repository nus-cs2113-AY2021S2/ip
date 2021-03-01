package duke.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.tasks.TaskList;
import duke.ui.TextUI;

import static duke.common.Messages.DIVIDER;

public class Storage {

    public TaskList taskList;
    public static final String DIRECTORY_ERROR = "An error occured while trying to create a data directory :(";
    public static final String POPULATING = "Populating list with existing tasks...";
    public static final String FILE_NOT_FOUND = "No existing data found. Creating empty list";
    public static final String SAVING = "Saving your data...";
    public static final String SAVE_SUCCESS = "Data saved.";
    public static final String SAVE_ERROR = "An error occured while saving your data :(";

    public Storage(TaskList taskList, TextUI ui) {
        try {
            this.taskList = taskList;
            ui.printToScreen(POPULATING);
            Path dataFilePath = Paths.get("data/");
            Files.createDirectories(dataFilePath);
            File dataFile = new File("data/duke.txt");
            Scanner fileReader = new Scanner(dataFile);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] splitLine = line.split("\\s\\|\\s");
                if (line.startsWith("T")) {
                    String todo = splitLine[2];
                    Todo t = new Todo(todo);
                    taskList.silentAdd(t);
                    if (splitLine[1].equals("1")) {
                        t.markAsDone();
                    }
                } else if (line.startsWith("E")) {
                    String description = splitLine[2];
                    LocalDate date = LocalDate.parse(splitLine[3], DateTimeFormatter.ofPattern("d MMM yyyy"));
                    Event t = new Event(description, date);
                    taskList.silentAdd(t);
                    if (splitLine[1].equals("1")) {
                        t.markAsDone();
                    }
                } else if (line.startsWith("D")) {
                    String description = splitLine[2];
                    LocalDate date = LocalDate.parse(splitLine[3], DateTimeFormatter.ofPattern("d MMM yyyy"));
                    Deadline t = new Deadline(description, date);
                    taskList.silentAdd(t);
                    if (splitLine[1].equals("1")) {
                        t.markAsDone();
                    }
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            ui.printToScreen(FILE_NOT_FOUND);
        } catch (IOException e) {
            ui.printToScreen(DIRECTORY_ERROR);
        } finally {
            ui.printToScreen(DIVIDER);
        }
    }

    public boolean saveData(TextUI ui) {
        try {
            ui.printToScreen(SAVING);
            FileWriter fileWriter = new FileWriter("data/duke.txt");
            for (int i=0; i<taskList.tasks.size(); i++) {
                Task currentTask = taskList.tasks.get(i);
                fileWriter.write(currentTask.getType() + " | " + ((currentTask.isDone()) ? "1" : "0") + " | "
                        + currentTask.getDescription() + ((currentTask.hasDate()) ? " | " + currentTask.getDate() : "") + '\n' );
            }
            fileWriter.close();
            ui.printToScreen(SAVE_SUCCESS);
            return true;
        } catch (IOException e) {
            ui.printToScreen(SAVE_ERROR);
            return false;
        } 
    }
}
