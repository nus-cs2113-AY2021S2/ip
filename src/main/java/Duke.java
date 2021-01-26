import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What do you have to do today?");

        int taskCount = 0;
        Task[] taskList = new Task[100];
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        
        while (true) {
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                int count = 1;
                String stringToPrint = "Here are the tasks in your list:";
                for (Task task : taskList) {
                    if (task == null) {
                        break;
                    }
                    stringToPrint += ("\n" + Integer.toString(count) + ": [" + task.getStatusIcon() + "] "
                            + task.getDescription());
                    count += 1;
                }
                printWithBorder(stringToPrint);
            } else if (line.substring(0, 4).equals("done")) {
                int taskNumber = Integer.parseInt(line.substring(5));
                Task task = taskList[taskNumber - 1];
                task.setIsDone();
                printWithBorder("Very nice! I've marked this task as done:\n" + "[" + task.getStatusIcon() + "] "
                        + task.getDescription());
            } else {
                taskList[taskCount] = new Task(line);
                taskCount += 1;
                printWithBorder("I have added: " + line);
            }
            line = in.nextLine();
        }
        printWithBorder("Sad to see you go! ): See you soon!");
    }

    public static void printWithBorder(String line) {
        System.out.print("___________________________________________________\n");
        System.out.print(line + "\n");
        System.out.print("___________________________________________________\n");
    }
}