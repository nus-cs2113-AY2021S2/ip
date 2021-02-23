package duke.main;

import duke.tasks.Task;
import duke.controller.UI;
import duke.controller.Storage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the main duke class.
 */
public class Duke {

    /**
     * Creates new static UI object.
     */
    private static UI ui = new UI();

    /**
     * Creates new static Storage object.
     */
    private static Storage storage = new Storage();

    /**
     * Creates ArrayList tasks.
     */
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Creates a duke object.
     */
    public Duke() {}

    /**
     * Loads save file.
     */
    public void load() {
        tasks = storage.printFileContents();
    }

    /**
     * Run duke app.
     */
    public void run() {
        ui.displayWelcome();
        ui.displayCommands();
        load();
        ui.handleTasklist(tasks);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}