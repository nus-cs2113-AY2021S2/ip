import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final String line = "____________________________________________________________";

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greet = "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                "____________________________________________________________";
        String exit = "Bye. Hope to see you again!\n" +
                "____________________________________________________________\n";
        System.out.println(logo);
        System.out.println(greet);
        System.out.print(" What can I do for you?\n");
        System.out.print(line+"\n");

        String input = "";
        Scanner userInput = new Scanner(System.in);
        input = userInput.nextLine();
        ArrayList<Task> tasks = new ArrayList<>();

        while (!input.equals("bye")) {

            if (input.contains("todo")) {
                int dividerPosition = input.indexOf(" ");
                String t = input.substring(dividerPosition + 1);
                tasks.add(new Todo(t));

                System.out.print("____________________________________________________________\n" + "Got it. I've added this task:" + "\n" + "[T]" + "[ ] " + t + "\n" + "Now you have " + tasks.size() + " tasks in the list." + "\n" + "____________________________________________________________\n");

            } else if (input.contains("deadline")) {
                int dividerPosition = input.indexOf(" ");
                int dividerPosition_1 = input.indexOf("/");
                String d = input.substring(dividerPosition + 1, dividerPosition_1);
                String by = input.substring(dividerPosition_1 + 4);
                tasks.add(new Deadline(d, by));

                System.out.print("____________________________________________________________\n" + "Got it. I've added this task:" + "\n" + "[D]" + "[ ] " + d + "(" + "by: " + by + ")" + "\n" + "Now you have " + tasks.size() + " tasks in the list." + "\n" + "____________________________________________________________\n");

            } else if (input.contains("event")) {
                int dividerPosition = input.indexOf(" ");
                int dividerPosition_1 = input.indexOf("/");
                String e = input.substring(dividerPosition + 1, dividerPosition_1);
                String at = input.substring(dividerPosition_1 + 4);
                tasks.add(new Event(e, at));
                System.out.print("____________________________________________________________\n" + "Got it. I've added this task:" + "\n" + "[E]" + "[ ] " + e + "(" + "at: " + at + ")" + "\n" + "Now you have " + tasks.size() + " tasks in the list." + "\n" + "____________________________________________________________\n");

            } else if (input.equals("list")) {
                if (tasks.size() == 0) {
                    System.out.print(line + "\n");
                    System.out.print("You have zero task at the moment." + "\n");
                    System.out.print(line + "\n");
                } else {

                    System.out.print(line + "\n");
                    System.out.print("Here are the tasks in your list: " + "\n");

                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.print(
                                Integer.toString(i + 1) + "." + "[" + tasks.get(i).getType() + "]" + "[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription() + "\n");
                    }
                    System.out.print(line + "\n");
                }

            } else if (input.contains("done")) {

                int dividerPosition = input.indexOf(" ");
                String taskNo = input.substring(dividerPosition + 1);
                System.out.print(taskNo);
                int taskIndex = Integer.parseInt(taskNo) - 1;
                if (taskIndex >=tasks.size()) {
                    System.out.print(line + "\n");
                    System.out.print("You have no such task." + "\n");
                    System.out.print(line + "\n");
                } else {
                    System.out.print(taskIndex);
                    tasks.get(taskIndex).markAsDone();
                    System.out.print(line + "\n");
                    System.out.print("Nice! I've marked this task as done:" + "\n" + "[" + tasks.get(taskIndex).getType() + "]" + "[" + tasks.get(taskIndex).getStatusIcon() + "] " + tasks.get(taskIndex).getDescription() + "\n");
                    System.out.print(line + "\n");
                }
            } else {
                System.out.print(line + "\n");
                System.out.print("Sorry, I didnt get you." + "\n" + "Please enter the command again." + "\n");
                System.out.print(line + "\n");
            }
            input = userInput.nextLine();
        }
        System.out.println(exit);

    }
}