import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String divider  = "____________________________________________________________";
        String command;
        Scanner scanner = new Scanner(System.in);

        welcomeMsg(divider);
        do {
            command = scanner.nextLine();
            if(!command.equals("bye")) {
                echoCommand(divider, command);
            }
        } while(!command.equals("bye"));
        exitMsg(divider);
        scanner.close();
    }

    public static void welcomeMsg(String divider) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(divider);
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(divider);
    }

    public static void echoCommand(String divider, String command) {
        System.out.println(divider);
        System.out.println(command);
        System.out.println(divider);
    }

    public static void exitMsg(String divider) {
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
