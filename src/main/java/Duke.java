import java.util.Scanner;

public class Duke {
    public static void echo (String value){
        System.out.println("**********************************************************");
        System.out.println("You said: " + value);
        System.out.println("**********************************************************");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String startText = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(startText);

        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equals("bye") ) {
            echo(input);
            input = in.nextLine();
        }
        System.out.println("#########################################################");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("#########################################################");
    }
}
