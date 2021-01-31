import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] list = new Task[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
            while (true) {
                String line;
                line = in.nextLine();
                if (line.equals("bye")) {
                    break;
                } else if (line.equals("list")) {
                    int i = 0;
                    while(list[i] != null) {
                        if (list[i].isDone()) {
                            System.out.println(i + 1 + ".[X] " + list[i].getDescription());
                        } else {
                            System.out.println(i + 1 + ".[ ] " + list[i].getDescription());
                        }
                        i++;
                    }
                } else if (line.split(" ")[0].equals("done")) {
                    int index = Integer.parseInt(line.split(" ")[1]) - 1;
                    list[index].setDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("[X] " + list[index].getDescription());

                } else {
                    int i = 0;
                    while (list[i] != null) {
                        i++;
                    }
                    list[i] = new Task(line);
                    System.out.println("added: " + line);
                }
            }

        System.out.println("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("____________________________________________________________\n");
    }
}
