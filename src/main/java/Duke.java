import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        String inputCommand = sc.nextLine();

        while (!inputCommand.equals("bye")){
            if (inputCommand.equals("list")){
                System.out.println(inputCommand);
            } else if (inputCommand.equals("blah")){
                System.out.println(inputCommand);
            }else if (inputCommand.equals("bye")){
                break;
            }
            inputCommand = sc.nextLine();
        }
            System.out.println("Bye. Hope to see you again soon!");
    }
}
