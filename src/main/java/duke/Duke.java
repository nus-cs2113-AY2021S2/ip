package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        DataStorage.readFile(list);
        Ui.printWelcomeMessage();
        interact(list);
        DataStorage.writeSaveData(list);
        Ui.printByeMessage();
    }

    /**
     * Reads user input and executes commands accordingly
     *
     * @param list ArrayList containing all tasks
     */
    public static void interact(ArrayList<Task> list) {
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
                TaskList.listCommand(list);
                break;
            case "todo":
                TaskList.toDoCommand(arguments, list);
                DataStorage.writeSaveData(list);
                break;
            case "deadline":
                TaskList.deadlineCommand(arguments, list);
                DataStorage.writeSaveData(list);
                break;
            case "event":
                TaskList.eventCommand(arguments, list);
                DataStorage.writeSaveData(list);
                break;
            case "done":
                TaskList.doneCommand(list, arguments);
                DataStorage.writeSaveData(list);
                break;
            case "undo":
                TaskList.undoCommand(list, arguments);
                DataStorage.writeSaveData(list);
                break;
            case "delete":
                TaskList.deleteCommand(list, arguments);
                DataStorage.writeSaveData(list);
                break;
            case "find":
                TaskList.findCommand(list, arguments);
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
