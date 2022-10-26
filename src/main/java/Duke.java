import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    static int TODO_LENGTH = 4;
    static int DEADLINE_LENGTH = 9;
    static int EVENT_LENGTH = 6;
    static int FIND_LENGTH = 5;

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

                // finds task
            } else if (command.contains("find")) {
                taskList.filterList(command.substring(FIND_LENGTH));

                // add tasks to list
            } else if (command.contains("todo")
                    || command.contains("deadline")
                    || command.contains("event")) {
                taskList.addTasks(command);
            } else {
                Ui.printDash();
                System.out.println("\tOOPS!!! Please enter a valid command.");
                Ui.printDash();
            }
            command = myObj.nextLine();
        }
        storage.exitProgram();
    }
}

