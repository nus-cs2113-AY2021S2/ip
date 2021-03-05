package duke;

import duke.myExceptions.NoContent;
import duke.myExceptions.NoTime;
import duke.myTasks.Deadline;
import duke.myTasks.Event;
import duke.myTasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The Save class handles reading and writing data to a text file
 * for saving memory between sessions.
 *
 * The parameter filePath is the relative path of the textfile.
 */
public class Save {
    public static String filePath = "savefile.txt";

    Save(){}

    /**
     * Creates a textfile if it does not already exist.
     */
    public static void checkSave() {
        Path path = Paths.get(filePath); //creates Path instance
        try {
            Path p = Files.createFile(path);     //creates file at specified location

        } catch (IOException e) {
            loadSave();
        }
    }

    /**
     * Reads the data from the textfile.
     * @return a TaskList object constructed from the data in the savefile.
     */
    public static TaskList loadSave() {
        TaskList list = new TaskList();
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String line = s.nextLine();
                switch(line.charAt(1)) {
                case 'T':
                    Todo todo = new Todo(line.substring(7));
                    if (line.charAt(4) == 'X') {
                        todo.markDone();
                    }
                    list.add(todo);
                    break;
                case 'D':
                    int add = line.indexOf(':') - 3;
                    Deadline deadline = new Deadline(line.substring(7, add + 1) + "/by" + line.substring(add + 4));
                    if (line.charAt(4) == 'X') {
                        deadline.markDone();
                    }
                    list.add(deadline);
                    break;
                case 'E':
                    int add1 = line.indexOf(':') - 3;
                    Event event = new Event(line.substring(7, add1 + 1) + "/at" + line.substring(add1 + 4));
                    if (line.charAt(4) == 'X') {
                        event.markDone();
                    }
                    list.add(event);
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException | NoContent | NoTime e) {
            System.out.println("error loading save");
        }
        return list;
    }

    /**
     * Overwrites the current textfile with data from the current session
     * @param list the TaskList object from the current session to be encoded into the textfile
     */
    public static void save(TaskList list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");//clear file
            fw.close();
            FileWriter f = new FileWriter(filePath, true); // create a FileWriter in append mode
            for (int i = 0; i < list.index; i++) {
                f.write(list.get(i).toString() + "\n");
            }
            f.close();
        } catch (IOException e) {
            System.out.println("cannot write to file");
        }
    }
}
