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

        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        ArrayList<Task> tasks = new ArrayList<Task>();

        while (!input.equals("bye")) {
            if (input.equals("list") && tasks.size() > 0) {
                System.out.println(line);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).getStatusIcon() + tasks.get(i).getDescription());
                }
                System.out.println(line);
                input = in.nextLine();
                continue;
            } else if (input.startsWith("done ")) {
                int index = Integer.parseInt(input.replace("done ", ""));
                tasks.get(index - 1).setDone();
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:" + System.lineSeparator()
                        + tasks.get(index - 1).getStatusIcon() + tasks.get(index - 1).getDescription());
                System.out.println(line);
                input = in.nextLine();
                continue;
            }


            System.out.println(line);
            System.out.println("added: " + input);
            Task task = new Task(input);
            tasks.add(task);
            System.out.println(line);

            input = in.nextLine();
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);


    }
}
