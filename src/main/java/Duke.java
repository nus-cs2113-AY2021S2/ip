import java.util.Scanner;

public class Duke {

    public static void printIntroMessage(){
        System.out.println("________________________________");
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
        System.out.println("________________________________");
    }

    public static void printExitMessage(){
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
        System.exit(0);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printIntroMessage();

        while (true){
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equals("bye")){
                printExitMessage();
            }
            switch (input) {
            case "list":
                System.out.println("________________________________");
                System.out.println("list");
                System.out.println("________________________________");
                break;
            case "blah":
                System.out.println("________________________________");
                System.out.println("blah");
                System.out.println("________________________________");
                break;
            default:
                System.out.println("Try again!");
                break;
            }
        }
    }
}
