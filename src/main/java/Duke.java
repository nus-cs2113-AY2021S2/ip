import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Bye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________");
                break;
            } else {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t " + command);
                System.out.println("\t____________________________________________________________");
            }
        }
    }
}
