import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String command;
        String horizontalLine = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(horizontalLine);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(horizontalLine);

        while (!(command = userInput.nextLine()).equals("bye")) {
            System.out.println(" " + command + "\n" + horizontalLine);
        }
        System.out.println(" Bye. Hope to see you again soon!\n" + horizontalLine);
    }
}
