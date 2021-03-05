package duke.main;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.taslist.TaskList;
import duke.ui.Ui;
import duke.command.Command;

import java.util.Scanner;

/**
 * Duke is a CLI application that can read and save tasks
 * that the user inputs.
 * It allows user to edit their list of tasks as wells as marking them
 * as done when they are done.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Create Duke object
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        Storage.loadTasks();
    }

    /**
     * Run the program
     */
    private void run() {
        Ui.greet();
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();
        while (true) {
            Command command = Parser.parse(input);
            try {
                command.execute(input);
                if (input.equals("bye")) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                Ui.commandDone();
            }
            Storage.saveTasks();
            input = in.nextLine();
        }
    }

    /**
     * Start the program
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}