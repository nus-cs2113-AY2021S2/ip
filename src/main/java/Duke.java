import duke.commands.Command;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

public class Duke {
    public static Ui ui;
    public static TaskList taskList;
    private static Storage storage;
    private static String home;

    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(ui);
        home = System.getProperty("user.dir");
    }

    public static void main(String[] args) {
        new Duke();
        storage.loadHistory(home, taskList);
        ui.displayWelcomeMessage();
        run();
        storage.saveHistory(home, taskList);
        ui.displayExitMessage();
    }

    public static void run() {
	    boolean isExit = false;
	    while (!isExit) {
            String fullCommand = ui.readLine();
            Parser parser = new Parser(fullCommand);
	        Command command = parser.getCommand();
	        try {
	            command.execute(taskList, ui);
	            isExit = command.isExit();
	        } catch (Exception e) {
	            ui.printErrorMessage(e);
	        }
	    }
	}
}