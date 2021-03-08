import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.commands.Command;
import duke.exception.DukeException;
import java.util.Scanner;

/**
 * Main class that will start the program.
 */
public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Instantiates Parser, Storage, TaskList and Ui objects.
     * Loads tasklist from storage if file is valid.
     *
     * @param filePath Relative path from the current directory of tasklist in .txt format.
     */
    public Duke(String filePath) {
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTasklist());
        this.ui = new Ui();
    }

    /**
     * Starts the entire Duke program.
     */
    public void run() {
        ui.displayWelcome();
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                Command command = parser.parseInput(input);
                command.execute(tasks);
                storage.saveTasklist(tasks.getTaskList());
            } catch (DukeException e) {
                ui.displayError(e);
            }
            input = scanner.nextLine();
        }
        ui.displayGoodbye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
