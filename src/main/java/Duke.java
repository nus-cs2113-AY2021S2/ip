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

        String userInput = sc.next();
        while(!userInput.equals("bye")) {
            System.out.println("\t------------------------------------------");
            System.out.println("\t" + userInput);
            System.out.println("\t__________________________________________\n");
            userInput = sc.next();
        }
        System.out.println("\t------------------------------------------");
        System.out.println("\tSee you soon! Goodbye! ^.^");
        System.out.println("\t__________________________________________");    }
}
