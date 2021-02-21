package jarvis.storage;

import jarvis.Duke;
import jarvis.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the writing and reading of the text file
 */
public class TextManager {

    /**
     * Print contents in the text file and adds into {@code tasks}
     *
     * @throws FileNotFoundException if file is not found
     */
    public static void printFileContents() throws FileNotFoundException {
        File file = new File("jarvis.txt");
        Scanner scanner = new Scanner(file);
        if (scanner.hasNext()) {
            System.out.println("\tHere's the tasks in your list, sir: ");
        } else {
            System.out.println("\tYou do not have any pending task, sir.");
        }
        while (scanner.hasNext()) {
            String encodedTask = scanner.nextLine();
            System.out.println("\t\t" + encodedTask);
            Duke.jarvis.addToTasks(TextDecoder.decodeTaskFromString(encodedTask));
        }
    }

    /**
     * Write contents in the {@code tasks} list into the text file
     *
     * @param tasks the list of tasks to write into the text file
     * @throws IOException if an input or output exception occurred
     */
    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter("jarvis.txt");
        for (Task task : tasks) {
            fileWriter.write(TextEncoder.encodeTaskToString(task) + System.lineSeparator());
        }
        fileWriter.close();
    }
}
