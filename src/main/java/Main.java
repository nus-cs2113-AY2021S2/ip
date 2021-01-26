import java.util.Scanner;

public class Main {
    public static void printLine() {
        System.out.println("_____________________________________________________");
    }
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.toLowerCase().equals("bye")) {
            printLine();
            System.out.println(input);
            printLine();
            input = scan.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
