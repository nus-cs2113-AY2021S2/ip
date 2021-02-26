package duke.storage;

import duke.ui.Ui;
import duke.task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String ROOT_PATH = System.getProperty("user.dir");
    private static final Path FOLDER_PATH = Paths.get(ROOT_PATH, "data");
    private static final Path FILE_PATH = Paths.get(ROOT_PATH, "data", "duke.txt");

    private Ui ui;

    public Storage(Ui ui) {
        this.ui = ui;
        try {
            Files.createDirectory(FOLDER_PATH);
        } catch (FileAlreadyExistsException e) {
            // Do nothing, directory already exists
        } catch (IOException e) {
            ui.printDirectoryError();
        }

        try {
            Files.createFile(FILE_PATH);
        } catch (FileAlreadyExistsException e) {
            // Do nothing, file already exists
        } catch (IOException e) {
            ui.printFileError();
        }
    }

    public ArrayList<Task> loadFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(FILE_PATH);
            for (String line : lines) {
                String[] components = line.split("/");
                String taskType = components[0];
                String done = components[1];
                String description = components[2];
                Task newTask = new Task(description);
                switch (taskType) {
                case "T":
                    newTask = new ToDo(description);
                    break;
                case "D":
                    LocalDateTime by = LocalDateTime.parse(components[3]);
                    newTask = new Deadline(description, by);
                    break;
                case "E":
                    LocalDateTime at = LocalDateTime.parse(components[3]);
                    newTask = new Event(description, at);
                }
                if (done.equals("1")) {
                    newTask.markAsDone();
                }
                tasks.add(newTask);
            }
        } catch (IOException e) {
            ui.printLoadError();
        }
        return tasks;
    }

    public void saveToFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH.toString());
            ArrayList<Task> taskArrayList = tasks.getList();
            for (Task t: taskArrayList) {
                String description = t.getDescription();
                String done = (t.isDone()) ? "1" : "0";
                if (t instanceof ToDo) {
                    fw.write("T" + "/" + done + "/" + description + System.lineSeparator());
                } else if (t instanceof Deadline) {
                    LocalDateTime by = ((Deadline) t).getByForSaving();
                    fw.write("D" + "/" + done + "/" + description + "/" + by + System.lineSeparator());
                } else if (t instanceof Event) {
                    LocalDateTime at = ((Event) t).getAtForSaving();
                    fw.write("E" + "/" + done + "/" + description + "/" + at + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            ui.printSaveError();
        }
    }
}
