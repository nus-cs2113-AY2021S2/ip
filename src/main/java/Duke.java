import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeText = "Welcome to the Kwik-e-mart, my name is Apu\n"
                +   "What can I get you?\n";
        System.out.println(welcomeText);
        Scanner scanner = new Scanner(System.in);
        String nextCommand = scanner.nextLine();
        while (!nextCommand.toLowerCase(Locale.ROOT).contains("bye")) {
            System.out.println(">>> You said: " + nextCommand);
            System.out.println("What else can I get you?");
            nextCommand = scanner.nextLine();
        }
        String goodbyeText = "Goodbye, please come again!";
        System.out.println(goodbyeText);
    }
}
