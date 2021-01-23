import java.util.Scanner;

public class Duke {

    static String LINE = "──────────────────────────────";
    static String HELLO_MESSAGE = LINE + "\n"
            + "8K: Hi there! 8K here.\n"
            + "    How can I help you?\n"
            + LINE;
    static String BYE_MESSAGE = LINE + "\n"
            + "8K: Bye bye! Have a nice day.\n"
            + LINE;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String input;

        System.out.println(HELLO_MESSAGE);
        input = in.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            System.out.println(LINE);
            System.out.println("8K: " + input);
            System.out.println(LINE);
            input = in.nextLine();
        }

        System.out.println(BYE_MESSAGE);
    }
}
