import java.util.Scanner;

public class Duke {
    static Scanner scanner = new Scanner(System.in);
    static String LINEBREAK = "____________________________________________________________\n";

    public static void printLine() {
        System.out.print(LINEBREAK);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");

        String text = scanner.nextLine();
        List list = new List();

        while (!text.equals("bye")) {
            String[] stringTokens = text.split(" ");
            printLine();
            if (text.equals("list")) {
                list.printList();
            } else if (stringTokens[0].equals("done")) {
                int taskNumber = Integer.parseInt(stringTokens[1]);
                list.markDone(taskNumber);
            } else {
                System.out.println("added: " + text);
                list.addTask(text);
            }
            printLine();
            text = scanner.nextLine();
        }

        System.out.println(LINEBREAK + "Bye, Hope to see you again soon!\n" + LINEBREAK);
    }
}
