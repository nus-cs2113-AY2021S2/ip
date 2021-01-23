import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static void printPreviousCommands(List<String> previousCommands) {
        if (previousCommands.size() == 0) {
            System.out.println("No previous commands to list sir :(");
            return;
        }
        for (int i = 0; i < previousCommands.size(); i++) {
            String command = previousCommands.get(i);
            System.out.printf("%d. %s\n", i + 1, command);
        }
    }

    public static void main(String[] args) {
        String welcomeText = "Welcome to the Kwik-e-mart, my name is Apu\n"
                +   "What can I get you?\n";
        System.out.println(welcomeText);
        Scanner scanner = new Scanner(System.in);
        String nextCommand = scanner.nextLine();
        List<String> previousCommands = new ArrayList<String>();
        while (!nextCommand.toLowerCase(Locale.ROOT).contains("bye")) {
            if (nextCommand.contains("list")) {
                printPreviousCommands(previousCommands);
            } else {
                previousCommands.add(nextCommand);
                System.out.println(String.format("Added: %s", nextCommand));
            }
            System.out.println("What else can I get you?");
            nextCommand = scanner.nextLine();
        }
        String goodbyeText = "Goodbye, please come again!";
        System.out.println(goodbyeText);
    }
}
