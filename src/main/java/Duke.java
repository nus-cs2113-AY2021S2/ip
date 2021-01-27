import java.util.Scanner;

public class Duke {

    public static Task[] list = new Task[100];
    public static int index = 0;

    public static void addToList(String input) {
        Task newTask = new Task(input);
        list[index] = newTask;
        index++;
    }

    public static void printList() {
        for (int i = 0; i < index; i++) {
            System.out.println(String.valueOf(i + 1) + ". " + list[i].representAsString());
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input;
        boolean running = true;
        do {
            input = in.nextLine();
            if (input.compareTo("bye") == 0) {
                running = false;
            } else if (input.compareTo("list") == 0) {
                printList();
            } else {
                String[] separated = input.split(" ");
                if (separated[0].compareTo("done") == 0) {
                    list[Integer.parseInt(separated[1]) - 1].markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list[Integer.parseInt(separated[1]) - 1].representAsString().replace("\u2713", "\u2718"));
                } else {
                    addToList(input);
                    System.out.println("Added: " + input);
                }
            }

        } while (running);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
