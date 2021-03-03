import dukehandler.FileManager;
import dukehandler.TaskManager;
import ui.ErrorMessagePrinter;
import ui.SuccessMessagePrinter;

import java.io.File;
import java.util.Scanner;

public class Duke {
    static final String DOTTED_LINE
            = "____________________________________________________________";
    static final File F = FileManager.loadFileOnStartup();
    public static void main(String[] args) {
        SuccessMessagePrinter.printGreetMessage(F.getAbsolutePath());

        String fullCommand;
        Scanner in = new Scanner(System.in);
        while (true) {
            fullCommand = in.nextLine();
            if (fullCommand.equals("bye")
                    || fullCommand.equals("exit")) {
                break;
            }
            System.out.println(DOTTED_LINE);
            readUserCommands(fullCommand);
            System.out.println(DOTTED_LINE);
        }

        FileManager.savingProgramRoutine(F);
        SuccessMessagePrinter.printByeMessage(F.getAbsolutePath());

    }

    private static void readUserCommands(String fullCommand) {
        String[] partOfCommand = fullCommand.split(" ");
        switch (partOfCommand[0]) {
        case "help":
            SuccessMessagePrinter.printHelpMessage();
            break;
        case "ll":
        case "ls":
        case "list":
            TaskManager.printAllTasks();
            break;
        case "done":
            TaskManager.markTaskAsDone(fullCommand.substring(4).trim());
            break;
        case "hi":
        case "hey":
        case "hello":
            SuccessMessagePrinter.printHelloMessage();
            break;
        case "todo":
        case "deadline":
        case "event":
            String taskType = partOfCommand[0];
            TaskManager.addNewTask(taskType, fullCommand);
            break;
        case "delete":
        case "remove":
            TaskManager.removeTask(fullCommand.substring(6).trim());
            break;
        case "find":
            TaskManager.printTasksWithKeywords(fullCommand.substring(4).trim());
            break;
        case "print":
            if (partOfCommand[1].trim().equals("type")) {
                TaskManager.printOneTaskTypeWithStreams(fullCommand.substring(11, 12).trim());
                break;
            } else if (partOfCommand[1].trim().equals("date")) {
                TaskManager.printOneTaskDateWithStreams(partOfCommand[2].trim());
                break;
            } else if (partOfCommand[1].trim().equals("filepath")
                    || (partOfCommand[1].trim().equals("file"))
                    && partOfCommand[2].trim().equals("path")) {
                SuccessMessagePrinter.printFilePath(F.getAbsolutePath());
                break;
            }
        case "save":
            FileManager.savingProgramRoutine(F);
            SuccessMessagePrinter.printTasksSaved(F.getAbsolutePath());
            break;
        default:
            ErrorMessagePrinter.printGenericErrorMessage();
            break;
        }
    }

}