import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDashes();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printDashes();

        TaskManager taskManager = new TaskManager();
        Scanner in = new Scanner(System.in);
        String line;

        while(true){
            line = in.nextLine();
            printDashes();

            if (line.equals("bye")) {
                break;
            }

            if (line.equals("list")) {
                taskManager.listTask();
            } else if (line.length()>4 && line.substring(0,4).equals("done")) {
                int taskNumber = Integer.parseInt(line.substring(5))-1;
                taskManager.doneTask(taskNumber);
            } else {
                taskManager.addTask(line);
            }

            printDashes();
        }

        System.out.println("Bye. Hope to see you again soon!");
        printDashes();
    }

    private static void printDashes(){
        System.out.println("____________________________________________________________");
    }
}
