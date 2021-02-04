import java.util.Scanner;

public class Duke {
    static Scanner scanner = new Scanner(System.in);
    static String LINEBREAK = "____________________________________________________________\n";

    public static void printLine() {
        System.out.print(LINEBREAK);
    }

    public static void main(String[] args) {
        printWelcome();

        String text = scanner.nextLine();
        List list = new List();

        while (!text.equals("bye")) {
            String[] stringTokens = text.split(" ", 2);
            printLine();
            switch (stringTokens[0]) {
                case "list":
                    list.printList();
                    break;
                case "done":
                    list.markDone(Integer.parseInt(stringTokens[1]));
                    break;
                case "todo":
                    list.addTask(stringTokens[1]);
                    break;
                case "deadline":
                    list.addDeadline(stringTokens[1]);
                    break;
                case "event":
                    list.addEvent(stringTokens[1]);
                    break;
                default:
                    System.out.println("no such command!");
            }
            printLine();
            text = scanner.nextLine();
        }
        System.out.println(LINEBREAK + "Bye, Hope to see you again soon!\n" + LINEBREAK);
    }

    public static void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINEBREAK +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                LINEBREAK);
    }
}
