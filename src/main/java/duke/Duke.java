package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        int taskCount = DataStorage.readFile(list);
        Ui.printWelcomeMessage();
        interact(list, taskCount);
        DataStorage.writeSaveData(list);
        Ui.printByeMessage();
    }

    public static void interact(ArrayList<Task> list, int taskCount) {

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();


        String[] parsedInput = Parser.inputParser(input);
        String command = parsedInput[0];
        String arguments = parsedInput[1];

        while (!command.equalsIgnoreCase("bye")) {
            switch (command.toLowerCase()) {
            case "help":
                Ui.printHelpMessage();
                break;
            case "list":
                Tasklist.printList(list, taskCount);
                break;
            case "todo":
                taskCount = Tasklist.addToDo(arguments, list, taskCount);
                break;
            case "deadline":
                taskCount = Tasklist.addDeadline(arguments, list, taskCount);
                break;
            case "event":
                taskCount = Tasklist.addEvent(arguments, list, taskCount);
                break;
            case "done":
                Tasklist.markAsDone(list, taskCount, arguments);
                break;
            case "undo":
                Tasklist.undoMarkAsDone(list, taskCount, arguments);
                break;
            case "delete":
                Tasklist.delete(list, taskCount, arguments);
                break;
            default:
                Ui.printInvalidCommandMessage();
            }
            input = scan.nextLine();

            parsedInput = Parser.inputParser(input);
            command = parsedInput[0];
            arguments = parsedInput[1];
        }
    }
}
