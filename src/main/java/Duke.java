import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I am\n" + logo);

        System.out.println("What can I do for you today?");

        Scanner sc = new Scanner(System.in);

        String[] tasks = new String[100];
        int numTasks = 0;

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                if (numTasks == 0) {
                    System.out.println("No tasks yet!");
                } else {
                    for (int i = 0; i < numTasks; i++) {
                        System.out.printf("%d. %s\n", i+1, tasks[i]);
                    }
                }

            } else {
                tasks[numTasks] = input;
                numTasks++;
                System.out.println("added: "+ input);
            }
            input = sc.nextLine();


        }

        System.out.println("Goodbye！See you again soon :)");

    }
}
