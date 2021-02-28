package ip.duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the whole Duke project
 * Builds a Java project named Duke to store tasks in a given address.
 * a Duke(filepath) object represents a Duke project
 * that store all task information in file at the path of filepath
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The constructor for a Duke object, returns nothing
     * loading the task information that already exist in the file
     * which is written by previous runs before each run
     *
     * @param filePath the address of the file storing all task information into a certain taskList
     * @throws IOException the exception that occurs when encountered with problems in writing to
     *                     or reading from a file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() throws IOException {
        start();
        runUntilExit();
        exit();
    }

    /**
     * Starts the program by printing greeting information.
     */
    private void start() {
        ui.printGreetings();
    }

    /**
     * The program continues running to get the commands from user inputs until
     * the user's input is an instruction (bye) to exit the program.
     * The program helps to store all task information into a certain file
     *
     * @throws DukeException when the command received in is incomplete or different from any acceptable commands
     * @throws IOException   the exception that occurs when encountered with problems in writing to
     *                       or reading from a file
     */
    private void runUntilExit() throws IOException {
        Scanner in = new Scanner(System.in);
        String commandText;

        do {
            commandText = in.nextLine();
            try {
                Parser.parseCommand(commandText);
            } catch (DukeException e) {
                ui.printInvalidInputWarnings(commandText);
            }
        } while (!commandText.equals("bye"));

        storage.saveData();
    }

    /**
     * Exits the program by printing a bye message.
     */
    private void exit() {
        ui.printBye();
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}
