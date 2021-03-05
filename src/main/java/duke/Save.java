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

public class Save {
    public static String filePath = "savefile.txt";

    Save(){}
    public static void checkSave() {
        Path path = Paths.get(filePath); //creates Path instance
        try {
            Path p = Files.createFile(path);     //creates file at specified location

        } catch (IOException e) {
            loadSave();
        }
    }

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
                    list.addToList(todo);
                    break;
                case 'D':
                    int add = line.indexOf(':') - 3;
                    Deadline deadline = new Deadline(line.substring(7, add + 1) + "/by" + line.substring(add + 4));
                    list.addToList(deadline);
                    break;
                case 'E':
                    int add1 = line.indexOf(':') - 3;
                    Event event = new Event(line.substring(7, add1 + 1) + "/at" + line.substring(add1 + 4));
                    list.addToList(event);
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
