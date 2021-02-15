import dukehandler.MessagePrinter;
import dukehandler.TaskManager;

import java.util.Scanner;

public class Duke {
    static String dottedLine = "____________________________________________________________";

    public static void main(String[] args) {
        MessagePrinter.printGreetMessage();
        String fullCommand;
        Scanner in = new Scanner(System.in); // when typing input manually
        while (true) {
            fullCommand = in.nextLine();
            if (fullCommand.equals("bye")) {
                break;
            }
            System.out.println(dottedLine);

            String[] partOfCommand = fullCommand.split(" ");

            switch (partOfCommand[0]) {
            case "help":
                MessagePrinter.printHelpMessage();
                break;
            case "list":
                TaskManager.printAllTasks();
                break;
            case "done":
                TaskManager.markTaskAsDone(fullCommand.substring(4).trim());
                break;
            case "hey":
            case "hello":
                MessagePrinter.printHelloMessage();
                break;
            case "todo":
            case "deadline":
            case "event":
                String taskType = partOfCommand[0];
                TaskManager.addNewTask(taskType, fullCommand);
                break;
            case "delete":
                TaskManager.removeTask(fullCommand.substring(6).trim());
                break;
            default:
                MessagePrinter.printGenericErrorMessage();
                break;
            }
            System.out.println(dottedLine);
        }
        MessagePrinter.printByeMessage();
    }
}
