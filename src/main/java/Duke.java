import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello, I'm Duke!\nWhat can i do for you?\n");

        String task;
        Scanner in = new Scanner(System.in);
        int index = 0;
        task = in.nextLine();
        String[] tasks = new String[100];

        while (task != null){
            if (task.equals("bye")){
                System.out.println("Bye, hope to see you again again soon!");
                break;
            }
            else if(task.equals("list")){
                for(int i=0; i<index; i++)
                {
                    System.out.println((i+1) + ": " + tasks[i]);
                }
                task = in.nextLine();
            }
            else{
                tasks[index] = task;
                System.out.println("Added: " + task);
                index++;
                task = in.nextLine();
            }
        }
    }
}
