import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
public static ArrayList<String> myItems = new ArrayList<String>();
public static ArrayList<Boolean> itemStatuses = new ArrayList<Boolean>();
public static final Scanner SCANNER = new Scanner(System.in);

public static void main(String[] args) {
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);

    //skeletal version of Duke
    String userInput = "";

    System.out.println("Hello! I'm Duke");
    System.out.println("What can I do for you?");

    while(true) {
        userInput = SCANNER.nextLine();
        System.out.println("------------------------------------");
        if(userInput.trim().equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            break;
        } else if (userInput.startsWith("list")) {
            System.out.println("Here are the tasks in your list: ");
            for(int i=0; i< myItems.size(); i++) {
                System.out.print(i+1 + ".[");
                if(itemStatuses.get(i) == true) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
                System.out.println("] " + myItems.get(i));
            }
        } else if (userInput.trim().startsWith("done")) {
            //String = userInput.substring(4);
            int processedInput;
            processedInput = Integer.parseInt(userInput.replaceAll("[^0-9]","")) -1;
            itemStatuses.set(processedInput,true);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("[X] " + myItems.get(processedInput));
        } else {
            myItems.add(userInput);
            System.out.println("added: " + userInput);
            itemStatuses.add(false);
        }
        System.out.println("------------------------------------");
    }
 /*   do {
        userInput = SCANNER.nextLine();

        System.out.println(userInput);
        System.out.println("------------------------------------");
    } while (!userInput.equals("bye"));*/


}
}
