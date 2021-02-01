import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        toDoList();
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void toDoList() {
        String[] list = new String[100];
        int itemNo = 0;
        String userInput;
        while (true) {
            Scanner sc = new Scanner(System.in);

            userInput = sc.nextLine();

            if (userInput.toLowerCase().equals("bye")) break;

            else if (userInput.toLowerCase().equals("list")){
                System.out.println("\t____________________________________________________________");
                for (int i = 0; i < itemNo; i++){
                    System.out.printf("%d. %s\n", i+1, list[i]);
                }
                System.out.println("\t____________________________________________________________");
            }

            else {
                list[itemNo] = userInput;
                System.out.println("\t____________________________________________________________");
                System.out.println("\t added:read " + userInput);
                System.out.println("\t____________________________________________________________");
                itemNo++;
            }
        }
    }
}
