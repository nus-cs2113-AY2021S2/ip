import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello I'm Duke\n"
                + "What can I do for you?";
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        String userInput;
        String output;
        do{
            userInput = sc.nextLine();
            switch (userInput) {
                case "bye":
                    output = "Bye. Hope to see you again soon!";
                    break;
                default:
                    output = userInput;
            }
            System.out.println(output);
        } while(!userInput.toLowerCase().equals("bye"));
    }
}
