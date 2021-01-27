import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        System.out.println("\t------------------------------------------");
        System.out.println("\tHello there! I'm Duke.");
        System.out.println("\tWhat can I help you with?");
        System.out.println("\t__________________________________________\n");

        ArrayList<String> tasks = new ArrayList<String>();

        String userInput = sc.nextLine();
        while(!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("\t------------------------------------------");
                for (int i=1; i<=tasks.size(); i++) {
                    System.out.println("\t" + i + ". " + tasks.get(i-1));
                }
                System.out.println("\t__________________________________________\n");
            }
            else {
                System.out.println("\t------------------------------------------");
                System.out.println("\tadded: " + userInput);
                tasks.add(userInput);
                System.out.println("\t__________________________________________\n");
            }
            userInput = sc.nextLine();
        }
        System.out.println("\t------------------------------------------");
        System.out.println("\tSee you soon! Goodbye! ^.^");
        System.out.println("\t__________________________________________");    }
}
