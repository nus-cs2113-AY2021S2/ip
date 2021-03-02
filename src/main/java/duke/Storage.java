package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static void downloadTask() throws IOException {
        File dataDir = new File("data");
        dataDir.mkdir();
        File dataFile = new File("data/tasks.txt");
        if (!dataFile.createNewFile()) {
            initializeExistingTasks(dataFile);
        }
    }

    private static void initializeExistingTasks(File dataFile) throws java.io.IOException {
        Scanner s = new Scanner(dataFile);
        Task t = null;
        while (s.hasNext()) {
            String commandInput = s.nextLine();
            if (commandInput.startsWith("T")) {
                String description = commandInput.substring(8);
                t = new Todo(description);
                TaskList.add(t);
            } else if (commandInput.startsWith("D")) {
                int timeIndex = commandInput.lastIndexOf("| ");
                String description = commandInput.substring(8, timeIndex - 1);
                t = new Deadline(description, commandInput.substring(timeIndex + 2));
                TaskList.add(t);
            } else if (commandInput.startsWith("E")) {
                int timeIndex = commandInput.lastIndexOf("| ");
                String description = commandInput.substring(8, timeIndex - 1);
                t = new Event(description, commandInput.substring(timeIndex + 2));
                TaskList.add(t);
            }
            if (commandInput.charAt(4) == '1') {
                t.setDone();
            }
        }
    }

    public static void uploadTask() throws java.io.IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        for (int i = 0; i < TaskList.getTaskCount(); ++i) {
            Task t = TaskList.getTask(i + 1);
            int taskStatus = 0;
            if (t.isDone) {
                taskStatus = 1;
            }
            if (t.getAlphabet().equals("T")) {
                fw.write("T | " + taskStatus + " | " + t.description + System.lineSeparator());
            } else {
                fw.write(t.getAlphabet() + " | " + taskStatus + " | " +
                        t.description + " | " + t.time + System.lineSeparator());
            }
        }
        fw.close();
    }
}
