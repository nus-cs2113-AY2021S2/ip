package duke;

import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.EmptyInputException;
import duke.exception.WrongInputException;
import duke.task.TaskManager;

import java.util.Scanner;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String BORDER = "\t________________________________________________________________\n";

    public static void showGreetings () {
        System.out.print("Hello there! This is Jack. Welcome to Task Tracker!\n");
    }

    public static String getUserInput () {
        return SCANNER.nextLine();
    }

    public static void runCommand(String command, String description, TaskManager taskManager) {
        try {
            CommandManager.executeCommand(command, description, taskManager);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.out.print(BORDER +
                    "\t☹ OOPS!!! " + description + " is not in the list.\n"
                    + BORDER);
        } catch (NumberFormatException numberFormatException) {
            System.out.print(BORDER +
                    "\t☹ OOPS!!! The description of " + command + " can only be numeric.\n"
                    + BORDER);
        } catch (WrongInputException wrongInputException) {
            System.out.print(BORDER +
                    "\t☹ OOPS!!! Sorry I don't understand what you mean.\n"
                    + BORDER);
        } catch (EmptyInputException emptyInputException) {
            System.out.print(BORDER +
                    "\t☹ OOPS!!! It seems that you have not entered anything.\n"
                    + BORDER);
        } catch (EmptyDescriptionException emptyDescriptionException) {
            System.out.print(BORDER +
                    "\t☹ OOPS!!! The description of a " + command + " cannot be empty.\n"
                    + BORDER);
        } catch (EmptyDateException emptyDateException) {
            System.out.print(BORDER +
                    "\t☹ OOPS!!! The description of a " + command + " must comes with a date.\n"
                    + BORDER);
        }
    }

    public static void main(String[] args) {
        showGreetings();
        boolean isRun = true;
        TaskManager taskManager = new TaskManager();

        while (isRun) {
            String userInput = getUserInput();
            CommandManager commandManager = new CommandManager(userInput);
            String command = commandManager.getCommand();
            String description = commandManager.getDescription();
            runCommand(command, description, taskManager);
        }
    }
}
