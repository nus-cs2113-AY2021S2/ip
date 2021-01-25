import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String border = "    ____________________________________________________________\n";
        String line;
        String lineLowerCase;

        System.out.println("Hello there! This is\n" + logo + "How may I help you ?\n");
        Scanner in = new Scanner(System.in);

        do {
            line = in.nextLine();
            lineLowerCase = line.toLowerCase();
            if (lineLowerCase.equals("bye")) {
                System.out.print(border + "     Good Bye. Hope to see you again soon!"+ "\n" + border);
            } else {
                System.out.print(border + "     " + line + "\n" + border);
            }
        } while (!lineLowerCase.equals("bye"));
    }
}
