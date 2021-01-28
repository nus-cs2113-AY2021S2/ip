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
                "____________________________________________________________");
        String text = myObj.nextLine();
        List myList = new List();
        while (!text.equals("bye")) {
            String[] sentenceWords = text.split(" ");
            printLine();
            if (text.equals("list")) {
                myList.printList();
            }
            else if (sentenceWords[0].equals("done")) {
                int markTask = Integer.parseInt(sentenceWords[1]);
                myList.markDone(markTask);
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
