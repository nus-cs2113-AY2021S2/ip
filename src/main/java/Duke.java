import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String logo = " _                          \n"
                + "| |                         \n"
                + "| |     _   _   ___   _ __  \n"
                + "| |    | | | | / _ \\ | '_ \\ \n"
                + "| |____| |_| || (_) || | | |\n"
                + "\\_____/ \\__, | \\___/ |_| |_|\n"
                + "         __/ |              \n"
                + "        |___/              \n";
        System.out.println("Hello from\n" + logo);


        System.out.println();
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Lyon");
        System.out.println("What can I do for you?");


        Scanner in = new Scanner(System.in);
        String userInput;
        ArrayList<Task> taskList = new ArrayList<Task>();
        int index;

        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            String[] command = userInput.split(" ");
            if (command[0].equals("list") && command.length == 1) {
                index = 1;
                System.out.println("____________________________________________________________");
                for (Task task : taskList) {
                    System.out.println(index + "." + "[" + task.getStatusIcon() + "] " + task.description);
                    index += 1;
                }
                System.out.println("____________________________________________________________");
            } else if (command[0].equals("done")) {
                //If the first/only word is done, do the following check:
                if (command.length == 2 && isInteger(command[1])) {
                    //if the length is exactly 2 and the second value after the space is an integer, valid command
                    if (0 < Integer.parseInt(command[1]) && Integer.parseInt(command[1]) <= taskList.size()) {
                        //if the given value to set as done is an existing index
                        taskList.get(Integer.parseInt(command[1]) - 1).setAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("[" + taskList.get(Integer.parseInt(command[1]) - 1).getStatusIcon() + "] "
                                + taskList.get(Integer.parseInt(command[1]) - 1).description);
                        System.out.println("____________________________________________________________");
                        //TODO
                        // Currently a task can be marked as done repeatedly.
                        // This does not cause any errors, but may be required to fix
                    } else {
                        //if the given value does not match to a range that makes sense
                        System.out.println("____________________________________________________________");
                        System.out.println("The index in the task list that you have selected to indicate as done," +
                                "does not exist!");
                        System.out.println("____________________________________________________________");
                    }

                } else {
                    // for the situation where the user keys in "done", "done 3.21512" or "done done" or something
                    // along these lines
                    //TODO
                    // perhaps can prompt a different message
                    System.out.println("____________________________________________________________");
                    taskList.add(new Task(userInput));
                    System.out.println("added: " + userInput);
                    System.out.println("____________________________________________________________");
                }
            } else {
                System.out.println("____________________________________________________________");
                taskList.add(new Task(userInput));
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            }
            userInput = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
