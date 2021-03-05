import java.util.Scanner;

public class Greet {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");

        while (true) {
            line = in.nextLine();

            if (line.equals("bye")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
                System.exit(0);
            }

            System.out.println("   ____________________________________________________________\n" +
                    "   " + line + "\n" +
                    "   ____________________________________________________________\n");
        }
    }
}
