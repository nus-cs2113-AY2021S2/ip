import java.util.Scanner;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String BORDER = "\t____________________________________________________________\n";

    public static void showGreetings () {
        System.out.print("Hello there! This is Jack. Welcome to Task Tracker!\n");
    }

    public static String getUserInput () {
        return SCANNER.nextLine();
    }

    public static void runCommand(String command, String description, TaskManager taskManager) {
        try {
            CommandManager.executeCommand(command, description, taskManager);
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
