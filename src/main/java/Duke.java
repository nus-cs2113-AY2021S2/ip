import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Prints welcome message
        System.out.println("_____________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.print("What can I do for you?\n");
        System.out.println("_____________________________________________________");

        Scanner userInputScanner = new Scanner(System.in);
        String userInput = userInputScanner.nextLine();
        String[] userInputSplitted = userInput.split(" ");

        while(!userInputSplitted[0].equals("bye")) {
            switch (userInputSplitted[0]) {
            case "list":
                System.out.println("_____________________________________________________");
                Task.listTasks();
                System.out.println("_____________________________________________________");
                break;
            case "done":
                Task.markAsDone(userInputSplitted[1]);
                break;
            default:
                Task t = new Task(userInput);
                t.addTask(userInput);
                System.out.println("added: " + userInput);
                break;
            }
            userInput = userInputScanner.nextLine();
            userInputSplitted = userInput.split(" ");
        }

        System.out.println("_____________________________________________________");
        System.out.println("Bye. Hope to see you again soon! :3");
        System.out.println("_____________________________________________________");
    }
}
