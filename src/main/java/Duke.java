import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm \n" + logo );
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scan = new Scanner(System.in);
        String text = "";
        text = scan.nextLine();
        while (!text.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(text);
            System.out.println("____________________________________________________________");
            text = scan.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
