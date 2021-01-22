import java.util.Scanner;

public class Duke {
    private static void showDivider() {
        System.out.println("-----------------------------------------------------");
    }

    private static void showGreeting() {
        System.out.println("Hello! I'm Duke :)");
        System.out.println("What can I do for you?");
    }

    private static void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        showDivider();
        showGreeting();
        showDivider();

        while (true) {
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();
            showDivider();
            if (command.toLowerCase().equals("bye")) {
                break;
            }
            System.out.println(command);
            showDivider();
        }

        showExit();
        showDivider();
    }
}
