import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];

    public static void main(String[] args) {
        Menu.printGreeting();
        while (true) {
            String userInput = Menu.readUserInput();
            try {
                Command command = Parser.parseUserInput(userInput);
                command.execute(tasks);
            } catch (DukeException e) {
                Menu.printError(e);
            }
        }
    }
}
