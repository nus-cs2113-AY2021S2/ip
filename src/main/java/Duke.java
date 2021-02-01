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
        Boolean isContinue = true;

        String[] lists = new String[100];
        int index = 0;

        while (isContinue) {
            text = scan.nextLine();

            if (text.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i=0; i<index; i++) {
                    System.out.println((i+1) + "." + lists[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (text.equals("bye")) {
                isContinue = false;

                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
            } else {
                lists[index++] = text;

                System.out.println("____________________________________________________________");
                System.out.println("added: " + text + " to the list");
                System.out.println("____________________________________________________________");
            }
        }

    }
}
