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

    public static void echoMessage(String line){
        printLine();
        System.out.println(line);
        printLineWithNewLine();
    }

    public static void main(String[] args) {

        printLogo();
        printHello();

        String line;
        Scanner in = new Scanner(System.in);

        while(true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                printBye();
                break;
            }
            echoMessage(line);
        }
    }
}
