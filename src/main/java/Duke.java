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
        taskTracker();
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void taskTracker() {
        Task[] tasks = new Task[100];
        int indexOfTask = 0;
        String userInput;
        Scanner sc = new Scanner(System.in);

        running:
        while (sc.hasNextLine()) {
            userInput = sc.nextLine();
            //Split user input to retrieve task type (i.e. event, deadline) and task description.
            String[] strings = userInput.split(" ",2);
            String taskType = strings[0].toLowerCase();
            String taskDescription = null;
            if (strings.length > 1){
                taskDescription = strings[1];
            }

            switch (taskType) {
            case ("todo"):
                tasks[indexOfTask] = new Todo(taskDescription);
                System.out.println("\t____________________________________________________________");
                System.out.println("\tGot it! I've added this task:");
                System.out.println("\t" + tasks[indexOfTask].toString());
                indexOfTask++;
                System.out.printf("\tNow you have %d tasks in the list.\n", indexOfTask);
                System.out.println("\t____________________________________________________________");
                break;

            case ("deadline"):
                // taskDescription now contains task description and it's deadline date and/or time
                // Split task description and deadline date and/or time
                strings = taskDescription.split(" /by ",2);
                taskDescription = strings[0];
                String deadlineDate = strings[1];
                tasks[indexOfTask] = new Deadline(taskDescription, deadlineDate);
                System.out.println("\t____________________________________________________________");
                System.out.println("\tGot it! I've added this task:");
                System.out.println("\t" + tasks[indexOfTask].toString());
                indexOfTask++;
                System.out.printf("\tNow you have %d tasks in the list.\n", indexOfTask);
                System.out.println("\t____________________________________________________________");
                break;

            case ("event"):
                // taskDescription now contains task description and it's event date and/or timeslot
                // Split task description and event's date and/or timeslot
                strings = taskDescription.split(" /at ",2);
                taskDescription = strings[0];
                String eventDate = strings[1];
                tasks[indexOfTask] = new Event(taskDescription,eventDate);
                System.out.println("\t____________________________________________________________");
                System.out.println("Got it! I've added this task:");
                System.out.println("\t" + tasks[indexOfTask].toString());
                indexOfTask++;
                System.out.printf("Now you have %d tasks in the list.\n", indexOfTask);
                System.out.println("\t____________________________________________________________");
                break;

            case ("list"):
                System.out.println("\t____________________________________________________________");
                System.out.println("Here are the tasks in your tasks:");
                for (int i = 0; i < indexOfTask; i++) {
                    System.out.printf("\t%d. %s\n", i + 1, tasks[i].toString());
                }
                System.out.println("\t____________________________________________________________");
                indexOfTask++;
                break;

            case ("done"):
                int indexOfTaskDone = Integer.parseInt(taskDescription) - 1;
                tasks[indexOfTaskDone].setDone();
                System.out.println("\t____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t" + tasks[indexOfTaskDone].toString());
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
