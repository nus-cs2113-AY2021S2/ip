import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // the following code is to complete the Level-0 increment
        // this is simply to test committing and pushing via intelliJ
        String divider = "-----------------------------------";
        System.out.println(divider);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(divider);
        // the following is to fulfil the Level-1 increment
        Scanner sc = new Scanner(System.in);

        String input;
        while (true) {
            input = sc.nextLine();
            if (!input.equals("bye")) {
                System.out.println(divider);
                System.out.println("Duke says: " + input);
                System.out.println(divider);
            }
            else {
                System.out.println(divider);
                System.out.println("Bye! Hope to hear from you again soon!");
                System.out.println(divider);
                break;
            }

        }

    }
}
