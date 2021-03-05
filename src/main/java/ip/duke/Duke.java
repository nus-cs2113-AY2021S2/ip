package ip.duke;

import ip.duke.exception.DukeException;

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
    private static final String filePath = "data/duke.txt";


    /**
     * The constructor for a Duke object, returns nothing
     * loading the task information that already exist in the file
     * which is written by previous runs
     *
     * @param filePath the address of the file storing all task information into a certain taskList
     * @throws IOException the exception that occurs when encountered with problems in writing to
     *                     or reading from a file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     * Starts the program by printing greeting information.
     * The program continues running to get and handle the commands from user inputs until
     * the user input is an instruction (bye)
     * The program helps to store all task information into a specific file
     * The program ends when receives a "bye" instruction
     *
     * @throws DukeException when the command received in is incomplete or different from any acceptable commands
     * @throws IOException   the exception that occurs when encountered with problems in writing to
     *                       or reading from a file
     */
    public void run() throws IOException {
        ui.printGreetings();
        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();
        while (!userCommand.equals("bye")) {
            try {
                Parser.parseCommand(userCommand);
            } catch (DukeException e) {
                ui.printInvalidInputWarnings(userCommand);
            }
            userCommand = in.nextLine();
        }

        storage.save();

        exit();
    }

    /**
     * Exits the program by printing a bye message.
     */
    private void exit() {
        ui.printBye();
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        new Duke(filePath).run();
    }
}
