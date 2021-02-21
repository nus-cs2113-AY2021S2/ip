package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.data.Task;
import duke.ui.Ui;

public class Storage {
    private static final String filePath = "./tasks.txt";

    /**
     * Saves all tasks in memory to a local text file.
     * File found at file path.
     * @param tasks Tasks in memory.
     */
    public static void save(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                String textInput = TaskEncoder.convertToFile(task);
                fw.write(textInput + "\n");
            }
            fw.close();
        } catch (IOException | NullPointerException exception) {
            // Exception will be thrown when user immediately
            // exits the app on the first run.
        }
    }

    /**
     * Reads all tasks in local text file into Duke memory.
     * @return List of tasks from text file specified in file path.
     */
    public static List<Task> read() {
        List<String> messages = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (f.createNewFile()) {
                messages.add("Tasks can be found at: " + f.getPath());
                Ui.reply(messages);
            }
            Scanner s = new Scanner(f);
            List<Task> tasks = new ArrayList<>();
            while (s.hasNext()) {
                String input = s.nextLine();
                if (!input.isEmpty()) {
                    Task task = TaskDecoder.convertToTask(input);
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (IOException | NullPointerException exception) {
            Ui.notifyError(exception.getMessage());
        }
        return null;
    }
}
