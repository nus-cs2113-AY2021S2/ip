import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("_____________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.print("What can I do for you?\n");
        System.out.println("_____________________________________________________");

        Scanner userInputScanner = new Scanner(System.in);
        String userInput = userInputScanner.nextLine();
        while(!userInput.equals("bye")) {
            switch (userInput) {
            case "list":
                System.out.println("_____________________________________________________");
                System.out.println("list");
                System.out.println("_____________________________________________________");
                break;
            case "blah":
                System.out.println("_____________________________________________________");
                System.out.println("blah");
                System.out.println("_____________________________________________________");
                break;
            default:
                System.out.println("_____________________________________________________");
                System.out.println("Uh oh, I don't understand what you have just typed :(");
                System.out.println("The commands supported are: list, blah, bye");
                System.out.println("_____________________________________________________");
            }
            userInput = userInputScanner.nextLine();
        }
        System.out.println("_____________________________________________________");
        System.out.println("Bye. Hope to see you again soon! :3");
        System.out.println("_____________________________________________________");
    }
}
