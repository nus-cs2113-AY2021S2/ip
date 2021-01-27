import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm DUke\nWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
            while (true) {
                String line;
                line = in.nextLine();
                System.out.println(line);
                if (line.equals("bye")){
                    break;
                }
            }

        System.out.println("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("____________________________________________________________\n");
    }
}
