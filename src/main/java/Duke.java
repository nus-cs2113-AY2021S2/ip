import java.util.Scanner;

public class Duke { //implement toString() next

    public static void main(String[] args) {
        welcomeMessage();

        Task[] taskList = new Task[100];
        int taskCount = 0;
        Scanner in = new Scanner(System.in);
        String commandInput = in.nextLine();

        while (!commandInput.equals("bye")) { //Process commands taken from user
            if (commandInput.equals("list")) {
                printList(taskList, taskCount);
            } else if (commandInput.startsWith("done")) {
                int taskNumber = Integer.parseInt(commandInput.substring(5, 6));
                taskList[taskNumber - 1].setDone();
            } else if (commandInput.startsWith("todo")) {
                taskList[taskCount] = new Todo(commandInput.substring(5));
                taskCount++;
            } else if (commandInput.startsWith("event")) {
                int timeIndex = commandInput.indexOf("/at");
                taskList[taskCount] = new Event(commandInput.substring(6, timeIndex), commandInput.substring(timeIndex + 1));
                taskCount++;
            } else if (commandInput.startsWith("deadline")) {
                int timeIndex = commandInput.indexOf("/by");
                taskList[taskCount] = new Deadline(commandInput.substring(9, timeIndex), commandInput.substring(timeIndex + 1));
                taskCount++;
            }
            commandInput = in.nextLine();
        }

        exitMessage();
    }

    private static void printList(Task[] taskList, int taskCount) {
        System.out.println("************************************************************");
        if (taskCount > 0) {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < taskCount; ++i) {
                Class classType = taskList[i].getClass();
                System.out.print(i + 1 + ". [" + classType.getName().charAt(0) + "]");
                System.out.println("[" + taskList[i].getStatusIcon() + "] " + taskList[i].description + taskList[i].time);
            }
        } else {
            System.out.println("You have not entered any tasks at the moment! :)");
        }
        System.out.println("************************************************************");

    }

    private static void exitMessage() {
        String ENDMESSAGE = "############################################################\n"
                + "Bye. Hope to see you again soon!\n"
                + "############################################################\n";
        System.out.println(ENDMESSAGE);
    }

    private static void welcomeMessage() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + LOGO);
        String STARTMESSAGE = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(STARTMESSAGE);
    }

}
