import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is the main class for the scheduling system.
 */
public class Duke {

    private static TaskList tasks;
    private static Storage storage;
    private static Ui ui;

    /**
     * Constructor of Duke object.
     * Load the task list from the data file if it exist.
     * If the data file does not exist, create an empty file and an empty task list.
     * @param filePath Relative path of the data file
     * @throws FileNotFoundException If the data file does not exist.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(Storage.printFileContents("data/duke.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("task list doesn't exist yet");
            Storage.createDirAndFile();
            tasks = new TaskList();
        }
    }

    /**
     * Main method.
     * Print the DUKE logo first.
     * Then run the system.
     * @param args
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"+
                        "Hello! I'm Duke\n"+
                        "What can I do for you?\n"+
                        "____________________________________________________________");

        new Duke("data/tasks.txt").run();
    }

    /**
     * Uses the getCommand method from the Ui class to get and respond to user command.
     */
    private static void run() {
        Ui.getCommand(tasks);
    }


}
