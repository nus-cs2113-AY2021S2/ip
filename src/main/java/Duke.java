import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String DECO_LINE = "____________________________________________________________";
        String HELLO_MESSAGE = " Hello! I'm Duke";
        String ASK_MESSAGE = " What can I do for you?";
        String BYE_MESSAGE = " Bye. Hope to see you again soon!";

        System.out.println(DECO_LINE);
        System.out.println(HELLO_MESSAGE);
        System.out.println(ASK_MESSAGE);
        System.out.println(DECO_LINE);
        System.out.println();

        Scanner readinput = new Scanner(System.in);
        String input = readinput.nextLine();

        while (!input.equals("bye")) {
            System.out.println(DECO_LINE);
            System.out.println(input);
            System.out.println(DECO_LINE);
            System.out.println();

            input = readinput.nextLine();
        }

        System.out.println(DECO_LINE);
        System.out.println(BYE_MESSAGE);
        System.out.println(DECO_LINE);
    }
}
