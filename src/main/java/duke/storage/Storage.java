package duke.storage;

import duke.commands.CommandHandler;
import duke.exception.IllegalTaskCommandException;
import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "Duke_Tasks.txt";

    /**
     * Writes the ArrayList to a file specified by the FILE_PATH.
     *
     * @throws IOException when the file is corrupted.
     */
    public static void saveTasks() throws IOException {
        File file = new File(FILE_PATH);

        FileWriter writer = new FileWriter(file);
        writer.write(TaskList.convertToFileInput());
        writer.close();
    }

    /**
     * Loads the content of the file specified by the FILE_PATH
     * into the ArrayList.
     *
     * @throws IOException when the file is corrupted.
     * @throws IllegalTaskCommandException when the content of the
     * file is not recognised by the program.
     */
    public static void loadTasks() throws IOException, IllegalTaskCommandException {
        File file = new File(FILE_PATH);

        if (file.exists()) {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String[] fileInput = scanner.nextLine().split(",");
                String loadedCommand = Parser.getLoadedCommand(fileInput);
                try {
                    switch (fileInput[0].strip()) {
                    case "T":
                        CommandHandler.executeTodo(loadedCommand);
                        CommandHandler.executeLoadMarkDone(fileInput);
                        break;
                    case "D":
                        CommandHandler.executeDeadline(loadedCommand);
                        CommandHandler.executeLoadMarkDone(fileInput);
                        break;
                    case "E":
                        CommandHandler.executeEvent(loadedCommand);
                        CommandHandler.executeLoadMarkDone(fileInput);
                        break;
                    }
                } catch (IllegalTaskCommandException e) {
                    Ui.printSaveFileCorrupted();
                    System.exit(-2);
                }
            }
            Ui.printSuccessfulLoad();
        } else {
            Ui.printUnsuccessfulLoad();
            initSaveFile();
        }
    }

    /**
     * Called by the loadTasks method, this method will only be
     * called when the file does not exist.
     *
     * @throws IOException when the file is corrupted.
     */
    private static void initSaveFile() throws IOException {
        File newFile = new File(FILE_PATH);
        if (newFile.createNewFile()) {
            Ui.printSuccessfulCreation();
        } else {
            Ui.printUnsuccessfulCreation();
            System.exit(-1);
        }
    }
}
