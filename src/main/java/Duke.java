import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public static void main(String[] args) {
        new Duke().run();
    }
    public Duke(){
        ui = new Ui();
        storage = new Storage();
        tasks = Storage.readFromSaveFile();
    }
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
                int noOfTasks = tasks.size();
                try {
                    Storage.writeToSaveFile(tasks, noOfTasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ui.shutdownMessage();
                System.exit(0);
            default:
                ui.invalidInputMessage();
            }
        }
    }
}
