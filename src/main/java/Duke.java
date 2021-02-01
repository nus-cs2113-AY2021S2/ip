import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        echo();
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void echo() {
        String userInput;
        while (true) {
            Scanner sc = new Scanner(System.in);

            userInput = sc.nextLine();

            if (userInput.toLowerCase().equals("bye")) break;

            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + userInput);
            System.out.println("\t____________________________________________________________");
        }
    }
}
