import java.util.ArrayList;
import java.util.List;
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
        List<String> tasks = new ArrayList<>();
        do{
            userInput = sc.nextLine();
            switch (userInput) {
                case "bye":
                    output = "Bye. Hope to see you again soon!";
                    System.out.println(output);
                    break;
                case "list":
                    int i = 1;
                    for(String task : tasks){
                        System.out.println(i + ". " + task);
                        i++;
                    }
                    break;
                default:
                    tasks.add(userInput);
                    System.out.println("added: " + userInput);
            }
        } while(!userInput.toLowerCase().equals("bye"));
    }
}
