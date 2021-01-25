import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /**String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         **/
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        String input;
        String[] tasks = new String[100];
        int numTasks = 0;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equals("bye")){
            if (input.equals("list")){
                for (int i = 1; i <= numTasks; i++){
                    System.out.println(i + ". " + tasks[i]);
                }
                System.out.println();

            }
            else {
                numTasks++;
                System.out.println("added: " + input + "\n");
                tasks[numTasks] = input;

            }

            input = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n");

    }
}
