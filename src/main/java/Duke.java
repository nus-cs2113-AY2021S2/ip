import dukehandler.MessagePrinter;
import dukehandler.TaskManager;

import java.util.Scanner;

public class Duke {
    static String dottedLine = "____________________________________________________________";

    public static void main(String[] args) {
        MessagePrinter.printGreetMessage();
        String command;
        Scanner in = new Scanner(System.in); // when typing input manually
        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.println(dottedLine);

            String[] partOfCommand = command.split(" ");

            switch (partOfCommand[0]) {
            case "help":
                MessagePrinter.printHelpMessage();
                break;
            case "list":
                TaskManager.printAllTasks();
                break;
            case "done":
                TaskManager.markTaskAsDone(command.substring(4).trim());
                break;
            case "hey":
            case "hello":
                MessagePrinter.printHelloMessage();
                break;
            case "todo":
            case "deadline":
            case "event":
                TaskManager.addNewTask(partOfCommand[0], command);
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
