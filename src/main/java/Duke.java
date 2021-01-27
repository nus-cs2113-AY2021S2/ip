import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________";

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */
        String greet = "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                "____________________________________________________________";
        String exit = "Bye. Hope to see you again!\n" +
                "____________________________________________________________\n";

        System.out.println(greet);
        System.out.print(" What can I do for you?\n");
        System.out.print(line);

        String input = "";
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        ArrayList<String> tasks = new ArrayList<>();

        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                tasks.add(input);
                System.out.print("____________________________________________________________\n" +
                        "added:" + input + "\n" +
                        "____________________________________________________________\n");

            } else if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.print("____________________________________________________________\n" +
                            Integer.toString(i+1) + "." + tasks.get(i) + "\n" +
                            "____________________________________________________________\n");
                }
            }
            input = in.nextLine();
        }
        System.out.println(exit);

    }
}