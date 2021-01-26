import java.util.Scanner;

public class Duke {
    public static String line = "\t________________________________________";
    public static void echo() {
        while (true) {
            Scanner scannerObject = new Scanner(System.in);
            String command = scannerObject.nextLine();
            if (command.equals("bye")) {
                System.out.println(line);
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            System.out.println(line);
            System.out.println("\t" + command);
            System.out.println(line);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(line);
        System.out.println();
        echo();
    }
}
