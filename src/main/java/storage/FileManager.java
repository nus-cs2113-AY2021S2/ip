package storage;

import myexceptions.BlankDescriptionException;
import tasklist.Task;
import tasklist.Tasklist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task t : tasks) {
            fw.write(t.getStatusIcon() + " " + t.getDescription() + "\n");
        }
        fw.close();
    }

    public static void restoreFileContents(String filePath) throws FileNotFoundException, BlankDescriptionException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        if (s.hasNext()) {
            System.out.println("Importing your saved list!");
        } else {
            System.out.println("You do not have any saved list!");
        }

        while (s.hasNext()) {
            String nextTask = s.nextLine();
            Tasklist.addTask(taskDecoder.taskDecoder(nextTask));
        }

    }
}
