import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        List list = new List();
        //boolean isExit = false;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String dash = "-";

        // print welcome message
        System.out.println("Hello from\n" + logo);
        System.out.println(dash.repeat(50));
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(dash.repeat(50));

        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();
        //Task[] tasks = new Task[100];
        while (!command.equals("bye")) {
            // prints a checklist
            if (command.equals("list")) {
                list.printList();
            }
            // marks current task as completed
            else if (command.contains("done")) {
                list.taskCompleted(command);
            }
            // adds tasks into list
            else {//(!command.equals("list") && !command.equals("done") && !command.equals("bye"))
                System.out.println("added: " + command);
                list.addTasks(command);
            }
            command = myObj.nextLine();
        }
        // exit program when input=bye
        if (command.equals("bye")) {
            System.out.println(dash.repeat(50));
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(dash.repeat(50));
        }
    }

}
