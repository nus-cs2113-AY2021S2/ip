import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello, I'm Duke!\nWhat can i do for you?\n");

        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (input != null){
            if (input.equals("bye")){
                System.out.println("Bye, hope to see you again again soon!");
                break;
            }
            else{
                System.out.println(input);
                input = in.nextLine();
            }
        }
    }
}
