import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();

            //print bye statement and exit program
            if (command.toLowerCase().equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            
            //echos commands
            System.out.println("____________________________________________________________");
            System.out.println(" " + command);
            System.out.println("____________________________________________________________\n");
        }
    }
}
