import java.util.Scanner;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Searching for saved data...");
        try {
            Storage.readFile();
            System.out.println("Loading saved data...");
        } catch (IOException e) {
            System.out.println("No data found - please ensure that a directory " +
                    "called 'data' is in your project directory");
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello from Duke!");
        System.out.println("What can I do for you?");

        String taskInput;
        Scanner sc = new Scanner(System.in);
        while (!(taskInput = sc.nextLine()).equalsIgnoreCase("bye")){
            Command.findCommand(taskInput);
            System.out.println("What can I do for you?");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}