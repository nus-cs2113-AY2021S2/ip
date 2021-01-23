import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        System.out.println("~____________________________________________________________~");
        System.out.println("What's up! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("~____________________________________________________________~\n");

        boolean isRunning = true;
        while (isRunning) {
            String phrase = sc.next();
            if (phrase.equals("bye")) {
                isRunning = false;
            } else {
                System.out.println("~____________________________________________________________~");
                System.out.println(phrase);
                System.out.println("~____________________________________________________________~\n");
            }
        }
        System.out.println("\n~____________________________________________________________~");
        System.out.println("Alright cheers mate!");
        System.out.println("~____________________________________________________________~");
    }
}
