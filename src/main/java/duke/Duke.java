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
                TaskList.printList(list, taskCount);
                break;
            case "todo":
                taskCount = TaskList.addToDo(arguments, list, taskCount);
                DataStorage.writeSaveData(list);
                break;
            case "deadline":
                taskCount = TaskList.addDeadline(arguments, list, taskCount);
                DataStorage.writeSaveData(list);
                break;
            case "event":
                taskCount = TaskList.addEvent(arguments, list, taskCount);
                DataStorage.writeSaveData(list);
                break;
            case "done":
                TaskList.markAsDone(list, taskCount, arguments);
                DataStorage.writeSaveData(list);
                break;
            case "undo":
                TaskList.undoMarkAsDone(list, taskCount, arguments);
                DataStorage.writeSaveData(list);
                break;
            case "delete":
                TaskList.delete(list, taskCount, arguments);
                DataStorage.writeSaveData(list);
                break;
            case "find":
                TaskList.find(list, arguments);
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
