import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);

        //Level-1
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        //Level-2
        ArrayList<String> inputs = new ArrayList<String>();

        while (!input.equals("bye")) {
            if (input.equals("list") && inputs.size() > 0) {
                System.out.println(line);
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println(i + 1 + ". " + inputs.get(i));
                }
                System.out.println(line);
                input = in.nextLine();
                continue;
            }

            System.out.println(line);
            System.out.println("added: " + input);
            inputs.add(input);
            System.out.println(line);

            input = in.nextLine();
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);


    }
}
