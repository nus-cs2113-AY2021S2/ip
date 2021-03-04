import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    static int TODO_LENGTH = 5;
    static int DEADLINE_LENGTH = 9;
    static int EVENT_LENGTH = 6;

    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;

    public Duke(String filePath) {
        ui = new Ui(filePath);
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.uploadTasks());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            taskList = new TaskList(storage.createNewFile());
        }
    }

    public static void main(String[] args) {
        String pathOfFile = new File("").getAbsolutePath();
        new Duke(pathOfFile+"/duke.txt").run();

    }

    public static void printDash() {
        System.out.println("-".repeat(80));
    }

    public void run() {
        Ui.printGreetMessage();
        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();
        while (!command.equals("bye")) {
            // prints a checklist
            if (command.equals("list")) {
                taskList.printList();

                // marks current task as completed
            } else if (command.contains("done")) {
                taskList.taskCompleted(command);

                // adds tasks into list
            } else if (command.contains("delete")) {
                taskList.deleteTasks(command);

                // add tasks to list
            } else {
                taskList.addTasks(command);
            }
            command = myObj.nextLine();
        }
        storage.exitProgram();
    }
}

