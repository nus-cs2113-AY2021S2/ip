package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.error.*;
import duke.command.*;

/**
 * Represents an instance of the program. A Duke object refers to one run of the application. 
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for the Duke class. 
     * Retrieves data from storage and add to task list. 
     * If no is data found, display fil to import message and load an empty task list. 
     * 
     * @param filePath Location of the storage file. 
     */
    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (FileNotFoundException e) {
            ui.displayToUser(Constants.ERROR_FILE_NOT_FOUND);
            tasks = new TaskList();
        } catch (ImportTaskException exception) {
            ui.displayToUser(Constants.ERROR_IMPORT_TASK);
        }
    }

    /**
     * Runs the program. 
     * Gets user input and parses command.
     * Executes the program according to the command input.  
     */
    public void run() {
        ui.displayWelcomeMessage();
        while (true) {
            try {
                String fullCommand = ui.getUserInput();
                Parser parser = new Parser();
                Command command = new Command(parser.getCommand(tasks, fullCommand));
                command.executeCommand(tasks, ui, storage);
            } catch (IllegalCommandException exception) {
                // If command detected is not found in available commands
                ui.displayToUser(Constants.ERROR_INVALID_COMMAND_RECEIVED);
            } catch (IndexOutOfBoundsException exception) {
                // If task number input by user is out of range
                ui.displayToUser(Constants.ERROR_INDEX_OUT_OF_RANGE, 
                        String.format(Constants.MESSAGE_NUMBER_OF_TASKS, tasks.getSize()));
            } catch (TaskListEmptyException exception) {
                // If task list is empty
                ui.displayToUser(Constants.ERROR_EMPTY_LIST);
            } catch (InvalidSyntaxException exception) {
                // If the syntax for the command is invalid
                ui.displayToUser(Constants.ERROR_INVALID_SYNTAX_RECEIVED, exception.getMessage());
            } catch (IOException exception) { 
                ui.displayToUser(Constants.ERROR_IO);
            } catch (NullPointerException exception) {
                ui.displayToUser(Constants.ERROR_IO);
            } catch (DateTimeParseException exception) {
                ui.displayToUser(Constants.ERROR_INVALID_DATE_RECEIVED);
            } catch (NumberFormatException exception) {
                ui.displayToUser(Constants.ERROR_INVALID_NUMBER);
            }
        }
    }

    /**
     * Starts the program.
     */
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}