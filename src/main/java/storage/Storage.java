package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import parser.Parser;
import common.Messages;
import task.Task;
import task.TaskManager;

/**
 * load, save and create save file
 */
public class Storage {

    public static final Parser parser = new Parser();
    public static final Messages messages = new Messages();
    public static final TaskManager taskManager = new TaskManager();

    /**
     * load save file
     * @throws FileNotFoundException when no save file found
     */
    public static void loadFile() throws FileNotFoundException {
        File f = new File("duke.txt");
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(f);
        while(s.hasNext()) {
            final String input = s.nextLine();
            final String[] commandDoneAndTypeAndParams = parser.splitSaveFileInput(input);
            final String commandDone = commandDoneAndTypeAndParams[0];
            final String commandType = commandDoneAndTypeAndParams[1];
            final String commandArgs = commandDoneAndTypeAndParams[2];
            parser.processFileInput(commandDone, commandType, commandArgs);
        }
        System.out.println(messages.MESSAGE_SAVE_FILE_LOADED);
    }

    /**
     * create or save save file
     * @throws IOException when error occurs when saving file
     */
    public static void saveFile() throws IOException {
        File f = new File("duke.txt");
        if (f.createNewFile()) {
            System.out.println(messages.MESSAGE_SAVE_FILE_CREATED);
        }
        FileWriter fw = new FileWriter("duke.txt");
        for (Task task : taskManager.getTaskList()) {
            fw.write(task.saveToFile());
        }
        fw.close();
        System.out.println(messages.MESSAGE_SAVE_FILE_SAVED);
    }
}
