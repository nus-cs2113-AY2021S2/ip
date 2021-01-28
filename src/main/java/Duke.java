import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Duke {
    public static void printTasks(List<String> inputs){
        int i = 1;
        for(String input:inputs){
            System.out.println(i + ". " + input);
            ++i;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String command = "";
        List<String> tasks = new Vector<String>();

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        do{
            command = in.nextLine();
            switch(command){
            case "list":
                printTasks(tasks);
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            default:
                tasks.add(command);
                System.out.println("added: " + command);
                break;
            }
        }while(!command.equals("bye"));
    }
}
