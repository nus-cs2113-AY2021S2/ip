package duke;

import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.EmptyOrWrongInputException;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String BORDER = "\t____________________________________________________________\n";
    public static final List<Task> taskList = new ArrayList<>();
    public static boolean isRun = true;

    public static void showGreetings () {
        System.out.print("Hello there! This is Jack. Welcome to Task Tracker!\n");
    }

    public static String getUserInput () {
        return SCANNER.nextLine();
    }

    public static void runCommand(String command, String description) {
        try {
            CommandManager.executeCommand(command, description);
        } catch (IndexOutOfBoundsException | NumberFormatException errorToMarkAsDone) {
            System.out.print(BORDER +
                    "\t☹ OOPS!!! It seems that you have entered an invalid number.\n"
                    + BORDER);
        } catch (EmptyOrWrongInputException errorInvalidOrNoInput) {
            System.out.print(BORDER +
                    "\t☹ OOPS!!! It seems that you have entered an invalid input.\n"
                    + BORDER);
        } catch (EmptyDescriptionException emptyDescriptionException) {
            System.out.print(BORDER +
                    "\t☹ OOPS!!! It seems that you have not enter a description.\n"
                    + BORDER);
        } catch (EmptyDateException emptyDateException) {
            System.out.print(BORDER +
                    "\t☹ OOPS!!! It seems that you have not enter a date.\n"
                    + BORDER);
        }
    }

    public static void main(String[] args) {
        FileLoader.loadFile();
        showGreetings();

        while (isRun) {
            String userInput = getUserInput();
            CommandManager commandManager = new CommandManager(userInput);
            runCommand(commandManager.getCommand(), commandManager.getDescription());

        }
        FileSaver.saveFile();
    }
}
