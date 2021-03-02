package Duke;

import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This Duke class is where the main program runs.
 * The Duke program is an application that helps user keep track of their
 * tasks in various forms such as Todos, Deadlines and Events.
*/


public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Creates a new instance of Ui, Storage.
     * Initialises the taskList using Storage loadfile method
     * and prints message if a file cannot be created.
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadFile();
        } catch (FileNotFoundException e) {
            try {
                storage.createFile();
                tasks = storage.loadFile();
            } catch (IOException e1) {
                System.out.println("File cannot be created");
            }
        }
    }

    /**
     * This method is used to runDuke continuously on a loop doing the following things:
     * take in a userInput, parse the output through the Parser to get the command to run
     * The loop is exited when the userInput is "bye"
     */

    public void run() {
        ui.showWelcome();
        ui.showLine();
        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNext()) {
            String input = userInput.nextLine();
            String s = Parser.parse(input, tasks);

            if (input.equals("bye")) break;
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasksList.txt").run();
    }
}