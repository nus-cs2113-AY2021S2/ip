import java.util.Scanner;

public class Duke {

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("---------------------------------------------------------");
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------------------");
    }

    public static void bidGoodbye() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------------------");
    }

    public static void request() {
        Task[] tasks = new Task[100];
        int index = 0;

        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println("---------------------------------------------------------");
            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= index; i++){
                    System.out.println(i + ".[" + tasks[i-1].getStatusIcon() + "] " + tasks[i-1].getDescription());
                }
            } else if (line.startsWith("done")) {
                int itemNum = Integer.parseInt(line.substring(5));
                tasks[itemNum - 1].setAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + tasks[itemNum-1].getStatusIcon() + "] " + tasks[itemNum-1].getDescription());
            } else {
                Task t = new Task(line);
                tasks[index] = t;
                index++;
                System.out.println("added: " + line);
            }
            System.out.println("---------------------------------------------------------");
            line = in.nextLine();
        }
        bidGoodbye();
    }

    public static void main(String[] args) {
        greet();
        request();
    }
}
