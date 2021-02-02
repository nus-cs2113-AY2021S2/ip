import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
        toDoList();
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void toDoList() {
        Task[] tasks = new Task[100];
        int itemNo = 0;
        String userInput;
        while (true) {
            Scanner sc = new Scanner(System.in);

            userInput = sc.nextLine();

            if (userInput.toLowerCase().equals("bye")) {
                break;
            } else if (userInput.toLowerCase().equals("tasks")){
                System.out.println("\t____________________________________________________________");
                System.out.println("Here are the tasks in your tasks:");
                for (int i = 0; i < itemNo; i++){
                    System.out.printf("\t%d. [%s] %s\n", i+1, tasks[i].getStatusIcon(), tasks[i].description);
                }
                System.out.println("\t____________________________________________________________");
            } else if (userInput.toLowerCase().contains("done")){
                int taskNo = (Integer.parseInt(userInput.replaceAll("[^0-9]",""))) - 1;
                tasks[taskNo].setDone();
                System.out.printf("\tNice! I have marked this task as done:\n");
                System.out.printf("\t[%s] %s\n", tasks[taskNo].getStatusIcon(), tasks[taskNo].description);

            } else {
                tasks[itemNo] = new Task(userInput);
                System.out.println("\t____________________________________________________________");
                System.out.println("\t added: " + userInput);
                System.out.println("\t____________________________________________________________");
                itemNo++;
            }
        }
    }
}
