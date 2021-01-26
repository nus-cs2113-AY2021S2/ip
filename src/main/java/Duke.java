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

        String [] list = new String[100];
        int index = 0;
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                printTasks(list, index);
            } else {
                recordTasks(list, index, command);
                index++;
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

    public static void recordTasks(String[] list, int index, String command) {
        list[index] = command;
        printLine();
        System.out.println("added: " + command);
        printLine();
    }

    public static void printTasks(String[] list, int index) {
        for (int i = 1; i <= index; i++) {
            System.out.println(i + ". " + list[i-1]);
        }
        printLine();
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
        printLine();
    }

    public static void printLine() {
        System.out.println("-----------------------------------------");
    }
}

