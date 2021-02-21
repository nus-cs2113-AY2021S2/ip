import dukehandler.TaskList;
import ui.ErrorMessagePrinter;
import dukehandler.FileManager;
import ui.SuccessMessagePrinter;
import dukehandler.TaskManager;

import java.io.File;
import java.util.Scanner;

public class Duke {
    private final TaskManager taskManager;
    private final FileManager fileManager;
    private final SuccessMessagePrinter successMessagePrinter;
    static final String DOTTED_LINE = "____________________________________________________________";

    public Duke() {
        TaskList taskList = new TaskList();
        taskManager = new TaskManager(taskList);
        successMessagePrinter = new SuccessMessagePrinter(taskList);
        fileManager = new FileManager(taskList, successMessagePrinter);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.successMessagePrinter.printGreetMessage();

        File tasksFile = duke.fileManager.loadFileOnStartup();
        String fullCommand;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println(DOTTED_LINE);
            fullCommand = in.nextLine();
            if (fullCommand.equals("bye")) {
                break;
            }
            duke.run(fullCommand);
            System.out.println(DOTTED_LINE);
        }
        duke.exit(tasksFile);
    }

    public void run(String fullCommand) {
        Duke duke = new Duke();
        String[] partOfCommand = fullCommand.split(" ");
        switch (partOfCommand[0]) {
        case "help":
            duke.successMessagePrinter.printHelpMessage();
            break;
        case "list":
            taskManager.printAllTasks();
            break;
        case "done":
            taskManager.markTaskAsDone(fullCommand.substring(4).trim());
            break;
        case "hey":
        case "hello":
            duke.successMessagePrinter.printHelloMessage();
            break;
        case "todo":
        case "deadline":
        case "event":
            String taskType = partOfCommand[0];
            taskManager.addNewTask(taskType, fullCommand);
            break;
        case "delete":
            taskManager.removeTask(fullCommand.substring(6).trim());
            break;
        default:
            ErrorMessagePrinter.printGenericErrorMessage();
            break;
        }
    }

    private void exit(File f) {
        fileManager.endOfProgramRoutine(f);
    }
}
