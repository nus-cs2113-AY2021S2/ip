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

    public static void saveTasks() throws IOException {
        File file = new File(FILE_PATH);

        FileWriter writer = new FileWriter(file);
        writer.write(TaskList.convertToFileInput());
        writer.close();
    }

    public static void loadTasks() throws IOException, IllegalTaskCommandException {
        File file = new File(FILE_PATH);

        if (file.exists()) {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String[] fileInput = scanner.nextLine().split(",");
                String loadedCommand = Parser.getLoadedCommand(fileInput);

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
            }
            Ui.printSuccessfulLoad();
        } else {
            Ui.printUnsuccessfulLoad();
            initSaveFile();
        }
    }

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
