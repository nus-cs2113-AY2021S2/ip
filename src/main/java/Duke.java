import java.util.Scanner;

public class Duke {

    public static void printStartUpMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void printExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void echo(Scanner scanner){
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                return;
            }
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printStartUpMessage();
        echo(scanner);
        printExitMessage();
    }
}
