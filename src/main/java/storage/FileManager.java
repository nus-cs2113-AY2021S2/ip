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

/**
 * The methods here manage the file writing and reading, upon quitting and loading
 * GuiltySpark, respectively.
 */
public class FileManager {

    /**
     * This method writes the contents of the list to a text file
     * named Duke.txt. File writing takes place automatically upon quitting GuiltySpark
     * @param filePath This is the name of the text file to be written into
     * @param tasks This is the task list
     * @throws IOException if file does not exist beforehand
     */
    public static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task t : tasks) {
            fw.write(t.getStatusIcon() + " " + t.getDescription() + "\n");
        }
        fw.close();
    }

    /**
     * This method transfers the contents of the list in the text file, into
     * the ArrayList
     * @param filePath This is the name of the text file to be read from
     * @throws FileNotFoundException if the specified file name in filePath is not located
     * @throws BlankDescriptionException if the file is blank
     */
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
