package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from saved text file. If file does not exist, create a file.
     *
     * @throws IOException if there is IO error.
     */
    public void loadFile(ArrayList<Task> tasks) throws IOException {
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        f.createNewFile();
        Scanner s = new Scanner(f);
        while(s.hasNext()) {
            tasks.add(deserializeFile(s.nextLine()));
            Task.totalNumberOfTasks += 1;
        }
    }
    /**
     * Converts format of tasks from text file format to program format.
     *
     * @param line line from text file.
     * @return Task in the format of program.
     */
    public static Task deserializeFile(String line) {
        String[] components = line.split(" \\| ");
        // No fallthrough required
        switch(components[0]) {
        case "[T]":
            Task fileTodo = new Todo(components[2]);
            if (components[1].equals("1")) {
                fileTodo.setDone();
            }
            return fileTodo;
        case "[E]":
            Task fileEvent = new Event(components[2], components[3]);
            if (components[1].equals("1")) {
                fileEvent.setDone();
            }
            return fileEvent;
        }
        // Case where task is deadline
        Task fileDeadline = new Deadline(components[2], components[3]);
        if (components[1].equals("1")) {
            fileDeadline.setDone();
        }
        return fileDeadline;
    }
    /**
     * Saves all tasks into file after user inputs "bye"
     *
     * @throws IOException if there is IO error.
     */
    public void saveFile(ArrayList<Task> tasks) throws IOException {
        StringBuilder saveText = new StringBuilder();
        for(Task task: tasks) {
            saveText.append(serializeFile(task));
        }
        FileWriter w = new FileWriter(filePath);
        w.write(saveText.toString());
        w.close();
    }

    /**
     * Convert task from program format to text format to prepare for storage.
     *
     * @param task task to convert.
     * @return converted task.
     */
    public static String serializeFile(Task task) {
        String serialized = task.getType();
        serialized += " | ";
        if (task.checkDone()) {
            serialized += "1 | ";
        } else {
            serialized += "0 | ";
        }
        serialized += task.getName();
        if (!(task instanceof Todo)) {
            serialized += " | ";
        }
        serialized += task.getTime();

        return serialized + "\n";
    }
}
