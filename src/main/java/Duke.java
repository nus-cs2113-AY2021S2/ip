import java.util.Scanner;

public class Duke {
    private static final String hLine = "\u2500".repeat(60);
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        Duke.greet();
        Duke.readUserInput();
        Duke.bye();
    }

    public static void greet() {
        System.out.println(Duke.hLine);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(Duke.hLine);
    }

    public static void bye() {
        System.out.println(Duke.hLine);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(Duke.hLine);
    }

    // Reads user input, does an action and stops reading when user inputs "bye"
    public static void readUserInput() {
        Scanner in = new Scanner(System.in);
        String userInput;
        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            Duke.echo(userInput);
            userInput = in.nextLine();
        }
    }

    public static void echo(String text) {
        System.out.println(Duke.hLine);
        System.out.println(text);
        System.out.println(Duke.hLine);
    }
}
