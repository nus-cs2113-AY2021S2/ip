import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasksList = new Task[100];
        int counter = 0;
        String line;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t---------------------------------------------------------------------");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t---------------------------------------------------------------------");
        while (true) {
            line = in.nextLine();
            line = line.trim();
            String[] words = line.split(" ");
            if (words[0].equals("done")) {
                int index = Integer.parseInt(words[1]) - 1;
                tasksList[index].markAsDone();
                System.out.println("\t---------------------------------------------------------------------");
                System.out.println("\tNice! I've marked this task as done:");
                System.out.print("\t" + "  ");
                System.out.println(tasksList[index]);
                System.out.println("\t---------------------------------------------------------------------");
            }
            else if (line.equals("bye")) {
                System.out.println("\t---------------------------------------------------------------------");
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t---------------------------------------------------------------------");
                break;
            } else if (line.equals("list")) {
                System.out.println("\t---------------------------------------------------------------------");
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 1; i <= counter; i++) {
                    System.out.print("\t" + i + ". ");
                    System.out.println(tasksList[i-1]);
                }
                System.out.println("\t---------------------------------------------------------------------");
            } else {
                Task newTask = new Task(line);
                tasksList[counter] = newTask;
                counter++;
                System.out.println("\t---------------------------------------------------------------------");
                System.out.println("\tadded: " + line);
                System.out.println("\t---------------------------------------------------------------------");
            }
        }
    }
}
