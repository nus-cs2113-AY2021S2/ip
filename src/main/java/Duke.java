import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.commands.Command;
import duke.exception.DukeException;
import java.util.Scanner;

public class Duke {

    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTasklist());
        this.ui = new Ui();
    }

    private Scanner scanner = new Scanner(System.in);

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
