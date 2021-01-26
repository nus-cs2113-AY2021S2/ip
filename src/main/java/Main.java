import java.util.Scanner;

public class Main {
    public static void printLine() {
        System.out.println("_____________________________________________________");
    }

    public static void echo(String input) {
        printLine();
        System.out.println(input);
        printLine();
    }

    public static void add(String input, String[] list, int index) {
        list[index] = input.trim();
        printLine();
        System.out.println("\"" + list[index] + "\"" + " added to list");
        printLine();
    }

    public static void printList(String[] list, int index) {
        printLine();
        for(int i=0; i<index; i++) {
            System.out.print(i+1 + ") ");
            System.out.println(list[i]);
        }
        printLine();
    }

    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();

        String[] list = new String[100];
        int index = 0;

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            if(input.equalsIgnoreCase(("list"))) {
                printList(list, index);
            }
            else {
                add(input, list, index);
                index++;
            }
            input = scan.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
