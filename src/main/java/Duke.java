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
    static String HELP_MESSAGE = LINE + "\n"
            + "8K: Bye - Exit programme.\n"
            + "    Help - Show list of commands.\n"
            + "    List - Show list of saved values.\n"
            + LINE;

    public static void main(String[] args) {
        String[] savedList = new String[100];
        int savedListSize = 0;

        Scanner in = new Scanner(System.in);
        String input = "";

        System.out.println(HELLO_MESSAGE);

        do {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(LINE);
                for (int i = 0; i < savedListSize; i++) {
                    System.out.println(Integer.toString(i+1) + ". " + savedList[i]);
                }
                System.out.println(LINE);
            } else if (input.equalsIgnoreCase("help")) {
                System.out.println(HELP_MESSAGE);
            } else if (savedListSize < 100) {
                savedList[savedListSize] = input;
                savedListSize++;
                System.out.println(LINE);
                System.out.println("8K: Added \"" + input + "\" to list.");
                System.out.println(LINE);
            } else {
                System.out.println(LINE);
                System.out.println("8K: List is full.");
                System.out.println(LINE);
            }
        } while (true);

        System.out.println(BYE_MESSAGE);
    }
}
