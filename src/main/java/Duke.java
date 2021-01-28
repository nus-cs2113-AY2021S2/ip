import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<String> myItems = new ArrayList<String>();
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
            if(userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.startsWith("list")) {
                for(int i=0; i< myItems.size(); i++) {
                    System.out.println(i+1 + ". " + myItems.get(i));
                }
            } else {
                myItems.add(userInput);
                System.out.println("added: " + userInput);
            }
            System.out.println("------------------------------------");
        }
     /*   do {
            userInput = SCANNER.nextLine();

            System.out.println(userInput);
            System.out.println("------------------------------------");
        } while (!userInput.equals("bye"));
*/


    }
}
