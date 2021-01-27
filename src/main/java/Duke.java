import java.util.Scanner;

public class Duke {

    public static String[] list = new String[100];
    public static int index = 0;

    public static void echo(String input) {
        System.out.println(input);
    }

    public static void addToList(String input) {
        list[index] = input;
        index++;
    }

    public static void printList() {
        for (int i = 0; i < index; i++) {
            System.out.println(String.valueOf(i + 1) + ". " + list[i]);
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
            } else {
                if (input.compareTo("list") == 0) {
                    printList();
                } else {
                    addToList(input);
                    System.out.println("Added: " + input);
                }
            }
        } while (running);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
