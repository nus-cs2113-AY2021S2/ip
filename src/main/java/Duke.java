import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String helloMessage = "Hello! I\'m Diuk \n" + "What can I do for you?\n";
        String byeMessage = "Bye. Hope to see you again soon!\n";
        String line = "____________________________________________________________\n";

        // Start - Greet user
        System.out.print(line);
        System.out.print(helloMessage);
        System.out.print(line);

        Scanner in = new Scanner(System.in);

        // Loop to receive response
        while (true){
            String input = in.nextLine();

            // check if exit
            if(input.toUpperCase().equals("BYE")){
                break;
            }

            // echo user command
            System.out.println(input + '\n');
        }


        System.out.print(line);
        System.out.print(byeMessage);
        System.out.print(line);
    }

}
