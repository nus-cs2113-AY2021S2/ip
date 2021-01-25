import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Soul, your truly soul mate");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        TaskManager tasks = new TaskManager(99);

        while (!line.equals("bye")) {
            if(line.equals("list")) {
                tasks.listAllTasks();
                line = in.nextLine();
            } else {
                tasks.addTask(line);
                line = in.nextLine();
            }

        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");



    }
}
