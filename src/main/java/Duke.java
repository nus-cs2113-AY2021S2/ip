import java.io.IOException;
import java.util.Scanner;

/**
 * The Duke Program is an application
 * which stores a list of tasks for the user
 *
 * @author Calvin
 * @version 0.2
 * @since 2021-02-28
 */

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public static void main(String[] args) {
        new Duke().run();
    }
    /**
     * Constructor for the Duke Object
     * Creates Ui object for application as well as reads from storage file.
     */
    public Duke(){
        ui = new Ui();
        storage = new Storage();
        tasks = storage.readFromSaveFile();
    }

    /**
     * Duke application main function
     * Consists of a switch which calls the appropriate methods
     * based on user input
     */
    public void run() {
        ui.startupMessage();
        Parser userInput;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            userInput = new Parser(sc.nextLine());
            switch (userInput.getTaskType()) {
            case ("todo"):
                tasks.addTodo(userInput);
                break;
            case ("deadline"):
                tasks.addDeadline(userInput);
                break;
            case ("event"):
                tasks.addEvent(userInput);
                break;
            case ("list"):
                tasks.printList();
                break;
            case ("done"):
                tasks.markAsDone(userInput);
                break;
            case ("delete"):
                tasks.deleteTask(userInput);
                break;
            case ("find"):
                tasks.findTask(userInput);
                break;
            case ("bye"):
                storage.writeToSaveFile(tasks);
                ui.shutdownMessage();
                System.exit(0);
            default:
                ui.invalidInputMessage();
            }
            storage.writeToSaveFile(tasks);
        }
    }
}
