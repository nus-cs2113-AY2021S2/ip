import java.util.Scanner;

public class Duke {
    private static Scanner SCANNER = new Scanner(System.in);

    public static String getUserInput () {
        return SCANNER.nextLine();
    }

    public static void showGreetings () {
        System.out.print("Hello there! This is Jack. Welcome to Task Tracker!\n");
    }

    public static void main(String[] args) {
        showGreetings();

        TaskManager taskManager = new TaskManager();

        while (true) {
            String userInput = getUserInput();
            CommandManager.executeCommand(userInput, taskManager);
        }
    }
}
