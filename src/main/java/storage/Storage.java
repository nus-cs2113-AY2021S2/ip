package storage;

import parser.Parser;
import tasks.TaskManager;
import common.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static final TaskManager taskManager = new TaskManager();
    private static final Constants constants = new Constants();
    private static final Parser parser = new Parser();


    /**
     * Saves tasks into file.
     */
    public void saveFile() throws IOException {
        File path = new File("tasks.txt");
        if (!path.exists()) {
            if (!path.createNewFile()) {
                throw new IOException();
            }
        }
        FileWriter fileWriter = new FileWriter(path);
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            fileWriter.write(taskManager.getTask(i).formatData());
        }
        fileWriter.flush();
        fileWriter.close();
    }


    /**
     * Loads tasks from file.
     */
    public void loadFile() throws FileNotFoundException {
        File path = new File("tasks.txt");
        if (!path.exists()) {
            throw new FileNotFoundException();
        }
        Scanner scanner = new Scanner(path);
        try {
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                parser.processInput(input);
                if (scanner.nextLine().equals("true")) {
                    parser.processInput("done " + taskManager.getTaskCount());
                }
            }
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_LOAD_ERROR);
            taskManager.reset();
        }
    }

}
