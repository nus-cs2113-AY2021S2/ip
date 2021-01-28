import java.util.Scanner;

public class Duke {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //skeletal version of Duke
        String userInput;
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        do {
            userInput = SCANNER.nextLine();
            System.out.println(userInput);
            System.out.println("------------------------------------");
        } while (!userInput.equals("bye"));


        System.out.println("Bye. Hope to see you again soon!");
    }
}
