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

        running:
        while (true) {
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
            String[] strings = userInput.split(" ",2);
            String taskType = strings[0].toLowerCase();
            String taskDescription = null;
            if (strings.length > 1){
                taskDescription = strings[1];
            }

            switch (taskType) {
            case ("todo"):
                tasks[itemNo] = new Todo(taskDescription);
                System.out.println("\t____________________________________________________________");
                System.out.println("Got it! I've added this task:");
                System.out.println("\t" + tasks[itemNo].toString());
                itemNo++;
                System.out.printf("Now you have %d tasks in the list.\n", itemNo);
                System.out.println("\t____________________________________________________________");
                break;

            case ("deadline"):
                strings = taskDescription.split(" /by ",2);
                taskDescription = strings[0];
                String by = strings[1];
                tasks[itemNo] = new Deadline(taskDescription, by);
                System.out.println("\t____________________________________________________________");
                System.out.println("Got it! I've added this task:");
                System.out.println("\t" + tasks[itemNo].toString());
                itemNo++;
                System.out.printf("Now you have %d tasks in the list.\n", itemNo);
                System.out.println("\t____________________________________________________________");
                break;

            case ("event"):
                strings = taskDescription.split(" /at ",2);
                taskDescription = strings[0];
                String at = strings[1];
                tasks[itemNo] = new Event(taskDescription,at);
                System.out.println("\t____________________________________________________________");
                System.out.println("Got it! I've added this task:");
                System.out.println("\t" + tasks[itemNo].toString());
                itemNo++;
                System.out.printf("Now you have %d tasks in the list.\n", itemNo);
                System.out.println("\t____________________________________________________________");
                break;

            case ("list"):
                System.out.println("\t____________________________________________________________");
                System.out.println("Here are the tasks in your tasks:");
                for (int i = 0; i < itemNo; i++) {
                    System.out.printf("\t%d. %s\n", i + 1, tasks[i].toString());
                }
                System.out.println("\t____________________________________________________________");
                itemNo++;
                break;

            case ("done"):
                int taskIndex = Integer.parseInt(taskDescription) - 1;
                tasks[taskIndex].setDone();
                System.out.println("\t____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t" + tasks[taskIndex].toString());
                System.out.println("\t____________________________________________________________");
                break;

            case ("bye"):
                break running;

            default:
                System.out.println("Sorry! Input not recognised, please try again.");
            }
        }
    }
}
