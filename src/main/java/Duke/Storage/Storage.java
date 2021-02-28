package Duke.Storage;

import Duke.Tasks.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;
    private final File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public void createFile() throws IOException {
        Path pathToFile = Paths.get(filePath);
        Files.createDirectories(pathToFile.getParent());
        file.createNewFile();
    }

    public TaskList loadFile() throws FileNotFoundException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        File fileName = new File(filePath);
        Scanner fileReader = new Scanner(fileName);
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();

            switch (data.charAt(0)) {
            case ('D'): {
                loadedTasks.add(loadDeadline(data));
                break;
            }
            case ('T'): {
                loadedTasks.add(loadTodo(data));
                break;
            }
            case ('E'): {
                loadedTasks.add(loadEvent(data));
                break;
            }
            }
        }
        fileReader.close();
        return new TaskList(loadedTasks);
    }

    public void writeToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, false);
        fileWriter.write("");
        for (Task task : taskList) {
            fileWriter.write(task.saveTask() + "\n");
        }
        fileWriter.close();
    }

    public Event loadEvent(String inputs) {
        String[] inputArray = inputs.split("\\s\\|\\s", 4);

        String doneCount = inputArray[1];
        String description = inputArray[2];
        String at = inputArray[3];

        Event newEvent = new Event(description, at);
        if (doneCount.equals("1")) newEvent.markAsDone();

        return newEvent;

    }

    public Deadline loadDeadline(String inputs) {
        String[] inputArray = inputs.split("\\s\\|\\s", 4);

        String doneCount = inputArray[1];
        String description = inputArray[2];
        String by = inputArray[3];

        Deadline newDeadline = new Deadline(description, by);
        if (doneCount.equals("1")) newDeadline.markAsDone();

        return newDeadline;

    }

    public Todo loadTodo(String inputs) {
        String[] inputArray = inputs.split("\\s\\|\\s", 4);

        String doneCount = inputArray[1];
        String description = inputArray[2];

        Todo newTodo = new Todo(description);
        if (doneCount.equals("1")) newTodo.markAsDone();

        return newTodo;
    }
}
