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
        ArrayList<Task> tasks = new ArrayList<>();

        while (!input.equals("bye")) {
            if (!input.equals("list") && !input.contains("done") ) {
                tasks.add(new Task(input));
                System.out.print("____________________________________________________________\n" +
                        "added:" + input + "\n" +
                        "____________________________________________________________\n");

            } else if (input.equals("list")) {
                System.out.print(line);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.print(
                            Integer.toString(i+1) + "." + "["+tasks.get(i).getStatusIcon()+"]"+ tasks.get(i).getDescription() + "\n");
                }
                System.out.print(line);
            } else if (input.contains("done")){
                int dividerPosition = input.indexOf(" ");
                String taskNo = input.substring(dividerPosition+1);
                int taskIndex = Integer.parseInt(taskNo)-1;
                tasks.get(taskIndex).markAsDone();


            }
            input = in.nextLine();
        }
        System.out.println(exit);

    }
}