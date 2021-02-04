import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        printGreeting();

        Task [] list = new Task[100];
        int index = 0;
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                printTasks(list, index);
            } else if (command.startsWith("todo")) {
                recordTasks(list, index, command,"todo");
                index++;
            } else if (command.startsWith("deadline")) {
                recordTasks(list, index, command,"deadline");
                index++;
            } else if (command.startsWith("event")) {
                recordTasks(list, index,command,"event");
                index++;
            } else if (command.startsWith("done")) {
                markDone(list, command);
            }
            command = in.nextLine();
        }
        printBye();
    }

    public static void printGreeting() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
        printLine();
    }

    public static void recordTasks(Task[] list, int index, String command, String category) {
        printLine();
        System.out.println("Got it. I've added this task:");
        if (category.equals("todo")) {
            list[index] = new Todo(command.substring(5));
        } else {
            String Time = command.substring(command.indexOf("/") + 4);
            if (category.equals("deadline")) {
                String content = command.substring(9, command.indexOf("/"));
                list[index] = new Deadline(content, Time);
            } else if (category.equals("event")) {
                String content = command.substring(6,command.indexOf("/"));
                list[index] = new Event(content, Time);
            }
        }
        System.out.println(list[index].toString());
        int count = index + 1;
        System.out.println("Now you have " + count + " tasks in the list.");
        printLine();
    }

    public static void markDone(Task[] list, String command) {
        int i;
        i = Integer.parseInt(command.substring(5));
        list[i-1].isDone = true;
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + " " + list[i-1].toString());
        printLine();

    }

    public static void printTasks(Task[] list, int index) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= index; i++) {
            System.out.println(i + "." + list[i-1].toString());
        }
        printLine();
    }

    public static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        printLine();
    }

    public static void printLine() {
        System.out.println("-----------------------------------------");
    }
}

