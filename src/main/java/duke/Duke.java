package duke;

import duke.command.Command;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.time.format.DateTimeParseException;

/**
 * Class to create the duke object.
 */

public class Duke {

    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;
    private java.nio.file.Path path = java.nio.file.Paths.get("duke.txt");

    /**
     * creates the duke object
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            Storage.createFile();
        } catch (NoSuchElementException e) {
            ui.fileIsEmpty();
        }
    }

    /**
     * This method keeps getting inputs from the user and performs the tasks accordingly
     * Terminates when the user inputs "bye"
     */
    public void run() {
        boolean isExit = false;
        ui.showWelcome();
        if (tasks == null) {
            tasks = new TaskList(new ArrayList<>());
        }
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (FileNotFoundException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                Ui.printTimeParseError();
            } catch (IndexOutOfBoundsException e) {
                Ui.printIndexOfOfBounds();
            } catch (NullPointerException e) {
                Ui.printCommandIsInvalid();
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
        Storage.saveData();
    }
}

