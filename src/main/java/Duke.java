import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String command = "";
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        do{
            command = in.nextLine();
            System.out.println(command);
        }while(!command.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}
