import dukehandler.MessagePrinter;
import dukehandler.TaskManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    static String dottedLine = "____________________________________________________________";

    public static void main(String[] args) {
        MessagePrinter.printGreetMessage();
        String fullCommand;
        String filePath = new File("").getAbsolutePath();
        File f = new File(filePath+"/src/main/java/tasks.txt");
        try {
            if (f.createNewFile()) {
                System.out.println(" I have created a file at this location:\n "
                                + f.getAbsolutePath() + "\n"
                                + " to store all your tasks!");
            }
            TaskManager.loadTasksFromFile(f.getAbsolutePath());
        } catch (IOException e) {
            MessagePrinter.printIOErrorMessage();
        }

        Scanner in = new Scanner(System.in); // when typing input manually
        while (true) {
            fullCommand = in.nextLine();
            if (fullCommand.equals("bye")) {
                try {
                    TaskManager.saveTasksToFile(f.getAbsolutePath());
                } catch (IOException e) {
                    MessagePrinter.printIOErrorMessage();
                }
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
            default:
                MessagePrinter.printGenericErrorMessage();
                break;
            }
            System.out.println(dottedLine);
        }
        MessagePrinter.printByeMessage();
    }
}
