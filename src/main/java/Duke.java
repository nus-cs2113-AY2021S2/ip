import java.util.Scanner;

public class Duke {

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void printLineWithNewLine(){
        System.out.println("____________________________________________________________\n");
    }

    public static void printLogo(){
        printLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLineWithNewLine();
    }

    public static void printHello() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLineWithNewLine();
    }

    public static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLineWithNewLine();
    }

    public static void printList(String[] tasks, int count) {
        printLine();
        for (int i = 0; i < count; i++) {
            System.out.println(i+1 + ". " + tasks[i]);
        }
        printLineWithNewLine();
    }

    public static void echoMessage(String line){
        printLine();
        System.out.println("added: " + line);
        printLineWithNewLine();
    }

    public static void main(String[] args) {

        printLogo();
        printHello();

        String line;
        Scanner in = new Scanner(System.in);
        int count = 0;
        String[] tasks = new String[100];

        while(true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                printBye();
                break;
            }
            else if (line.equals("list")) {
                printList(tasks, count);
                continue;
            }
            tasks[count] = line;
            count++;
            echoMessage(line);
        }
    }
}
