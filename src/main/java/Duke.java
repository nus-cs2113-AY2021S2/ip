import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // the following code is to complete the Level-0 increment
        // this is simply to test committing and pushing via intelliJ
        String divider = "-----------------------------------";
        System.out.println(divider);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(divider);
        // the following is to fulfil the Level-1 increment
        Scanner sc = new Scanner(System.in);

        Task[] taskManager = new Task[100];
        int taskCounter = 0;
        String input;
        do {
            input = sc.nextLine();
            switch (input) {
            case "list":
                System.out.println("This is your current list:");
                for (int i = 0; i < taskCounter; i++){
                    int j = i + 1;
                    System.out.println(j + ": " + taskManager[i].getTaskName());
                }
                break;
            case "bye":
                System.out.println(divider);
                System.out.println("Bye! Hope to hear from you again soon!");
                System.out.println(divider);
                break;
            default:
                System.out.println(divider);
                taskManager[taskCounter] = new Task(input);
                System.out.println("Duke says: I've added " + input);
                taskCounter++;
                System.out.println(divider);

            }
        } while (!input.equals("bye"));


    }
}
