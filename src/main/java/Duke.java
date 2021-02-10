import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] list = new Task[100];
        int taskInList = 0;
        Utils.welcomeMessage();
        Utils.printLine();
        Scanner in = new Scanner(System.in);
        while (true) {
            String line;
            line = in.nextLine();
            Utils.printLine();
            if (Utils.commandHandler(line) == -1) {
                break;
            }
            Utils.printLine();
        }
        Utils.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        Utils.printLine();
    }
}