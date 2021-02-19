import controller.Storage;
import controller.UI;

import tasks.Task;

import java.io.FileNotFoundException;

import java.util.ArrayList;

/**
 * Main Class Duke app.
 */
public class Duke {

    private static Storage store = new Storage();
    private static UI ui = new UI();
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Duke constructor that loads the saved text file.
     */
    public Duke () {
        try {
            tasks = store.loadFile();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("--------------------------------------------");
        }
    }

    /**
     * Duke run app method.
     */
    public void run() {
        ui.welcomeMessage();
        ui.scheduleTimetable(tasks);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}





