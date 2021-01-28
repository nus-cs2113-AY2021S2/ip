import java.util.Scanner;

public class Duke {
    static Scanner myObj = new Scanner(System.in);
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
                "____________________________________________________________\n");
        String text = myObj.nextLine();
        List myList = new List();
        while (!text.equals("bye")) {
            printLine();
            if (text.equals("list")) {
                myList.printList();
            }
            else {
                System.out.println("added: " + text);
                myList.addTask(text);
            }
            printLine();
            text = myObj.nextLine();
        }
        System.out.println(LINEBREAK + "Bye, Hope to see you again soon!\n" + LINEBREAK);
    }
}
