import java.util.Scanner;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);

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
            System.out.print("☹ OOPS!!! It seems that you have entered an invalid task number.\n");
        } catch (EmptyOrWrongInputException errorInvalidOrNoInput) {
            System.out.print("☹ OOPS!!! It seems that you have entered an invalid input.\n");
        } catch (EmptyDescriptionException emptyDescriptionException) {
            System.out.print("☹ OOPS!!! It seems that you have not enter a description.\n");
        } catch (EmptyDateException emptyDateException) {
            System.out.print("☹ OOPS!!! It seems that you have not enter a date.\n");
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
