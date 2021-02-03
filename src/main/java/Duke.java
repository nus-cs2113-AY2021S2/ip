import java.util.Scanner;

public class Duke {

    public static Todo[] list = new Todo[100];
    public static int index = 0;

    public static void addToList(Todo task) {
        list[index] = task;
        index++;
    }

    public static void printList() {
        for (int i = 0; i < index; i++) {
            System.out.println(String.valueOf(i + 1) + ". " + list[i].toString());
        }
    }

    public static void printAddMessage(Todo task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + String.valueOf(index) + " tasks in the list.");
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
            String command = "";
            String content = "";
            if (input.indexOf(' ') == -1) {
                command = input;
            } else {
                command = input.substring(0, input.indexOf(' '));
                content = input.substring(input.indexOf(' ') + 1);
            }
            switch (command) {
            case "bye":
                running = false;
                break;
            case "list":
                printList();
                break;
            case "done":
                list[Integer.parseInt(content) - 1].markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list[Integer.parseInt(content) - 1].toString());
                break;
            case "todo":
                Todo todo = new Todo(content);
                addToList(todo);
                printAddMessage(todo);
                break;
            case "event":
                Event event = new Event(content);
                addToList(event);
                printAddMessage(event);
                break;
            case "deadline":
                Deadline deadline = new Deadline(content);
                addToList(deadline);
                printAddMessage(deadline);
                break;
            default:
                System.out.println("Invalid input, please try again");
                break;
            }

        } while (running);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
