package duke;

import duke.exception.DataErrorException;
import duke.exception.FullListException;
import duke.inputhandlers.CommandRunner;
import duke.inputhandlers.Parser;
import duke.storage.FileManager;
import duke.storage.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.TextUi;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */

public class Duke {
    public static TaskList tasks = new TaskList();
    public static TextUi ui = new TextUi();
    public static CommandRunner runner = new CommandRunner();
    
    public static void main(String[] args) {
        ui.printHello();
        FileManager.checkSavedData();
        runner.receiveUserInput();
        ui.printBye();
    }
}
