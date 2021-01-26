import java.util.Scanner;

public class Duke {
    private static final String border = "____________________________________________________________";
    private static final String newline = System.lineSeparator();
    public static void greet() {
        System.out.println(border);
        System.out.println("Hello, I'm Panda!");
        System.out.println("What would you like to do today?");
        System.out.println(border + newline);

    }

    public static void echo(String command) {
        System.out.println(border);
        System.out.println("\t" + command);
        System.out.println(border + newline);
    }

    public static void goodbye() {
        System.out.println(border);
        System.out.println("Alright, goodbye!");
        System.out.println(border);
    }
    public static void main(String[] args) {
        greet();

        Scanner in = new Scanner(System.in);
        String cmd = in.nextLine();
        while (!cmd.equals("bye")) {
            echo(cmd);
            cmd = in.nextLine();
        }

        goodbye();
    }
}
