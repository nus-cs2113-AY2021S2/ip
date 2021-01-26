import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);
        System.out.println("\t____________________________________________________________\n" +
                "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("\t____________________________________________________________\n" +
                        "\tHere are the tasks in your list:");
                for(int i=0; i<taskCount; i++) {
                    if(tasks[i].getTaskDone()) {
                        System.out.println("\t" + (i + 1) + ".[X]" + tasks[i].getTaskName());
                    }
                    else {
                        System.out.println("\t" + (i + 1) + ".[ ]" + tasks[i].getTaskName());
                    }
                }
                System.out.println("\t____________________________________________________________\n");
            } else if (userInput.startsWith("done")) {
                int taskIndex = (Integer.parseInt(userInput.split(" ")[1])) - 1;
                tasks[taskIndex].setTaskDone(true);
                System.out.println("\t____________________________________________________________\n" +
                        "\tNice! I've marked this task as done:\n\t\t[X] " + tasks[taskIndex].getTaskName() +
                        "\n\t____________________________________________________________\n");
            } else {
                tasks[taskCount] = new Task(userInput, false);
                System.out.println("\t____________________________________________________________\n" +
                        "\tadded: " + userInput +
                        "\n\t____________________________________________________________\n");
                taskCount++;
            }
            userInput = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n " +
                "\tBye. Hope to see you again soon!" +
                "\n\t____________________________________________________________");
    }
}
