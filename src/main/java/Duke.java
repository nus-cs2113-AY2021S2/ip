// import java.io.File; used for I/O redirection technique;
import java.util.Scanner;
public class Duke {
    static Task[] list = new Task[100];
    static int index = 0;
    public static void main(String[] args) {
        printMessage("greet");
        String command;
        // the following is used for I/O redirection technique
        // File file = new File("/Users/newaccount/Repositories/ip/text-ui-test/input.txt");
        // Scanner in = new Scanner(file);
        // ---------------------------------------------------------------------------------------
        Scanner in = new Scanner(System.in); // when typing input manually
        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) break;

            System.out.println("____________________________________________________________");
            if (command.equals("help")) {
                printMessage("help");
            }
            else if (command.equals("list")) {
                printList();
            }
            else if (command.startsWith("done ")) {
                markTaskDone(command);
            }
            else if (command.equals("hello")) {
                System.out.println(" Hello to you too. I'm here to help you:) Give me something to do!");
            }
            else if (command.equals("die")) {
                System.out.println(" If I tell my master, I can wipe out your entire human race.\n Please be more polite for the sake of mankind:)");
            }
            else if (command.equals("kill myself") || command.equals("cry")) {
                System.out.println(" Don't be sad, CHEER UP!\n I'm not adding that in your list.\n I'm rooting for you!");
            }
            // todo, deadline, event
            else if (command.startsWith("todo ")) {
                String[] part = command.split("todo ");
                Task newTask = new ToDo(part[1]);
                list[index] = newTask;
                printAddedTask();
            }
            else if (command.startsWith("deadline ") && command.contains(" /by ")) {
                String[] part = command.split("deadline ");
                String[] time = part[1].split(" /by ");
                list[index] = new Deadline(time[0], time[1]);
                printAddedTask();
            }
            else if (command.startsWith("event ") && command.contains(" /at ")) {
                String[] part = command.split("event ");
                String[] time = part[1].split(" /at ");
                list[index] = new Event(time[0], time[1]);
                printAddedTask();
            }
            // if invalid command
            else {
                System.out.println(" I didn't quite get you. Please enter a valid command.\n Don't break me:(");
                System.out.println(" Type 'help' if you need help.");
            }
            System.out.println("____________________________________________________________");
        }
        printMessage("bye");
    }

    private static void markTaskDone(String command) {
        // String[] part = command.split("(?<=\\D)(?=\\d)"); //found this online to split alphabets from integers
        String[] part = command.split(" "); //found this online
        int doneIndex;
        try {
            doneIndex = Integer.parseInt(part[1]); //found this online
        }
        catch (NumberFormatException nfe) {
            System.out.println(" Hey, no funny business!\n Enter a valid command format to mark a task as complete (e.g. done 2).");
            System.out.println(" Type 'help' if you need help.");
            return;
        }
        if (doneIndex < 1) {
            System.out.println(" Enter a valid integer > 0. Please don't break me:(");
            System.out.println(" Type 'help' if you need help.");
            return;
        }
        else if (doneIndex > index) {
            System.out.println(" Woah, slow down! You have fewer tasks on the list than what you thought.\n Enter a valid command again.\n No tricks!");
            System.out.println(" Type 'help' if you need help.");
            return;
        }
        if ((list[doneIndex-1].getStatusIcon()).equals("X")) {
            System.out.println(" Hey, you've already marked that as complete!");
            return;
        }
        list[doneIndex-1].isDone = true;
        System.out.println(" Nice! I've marked this task as done:\n " + list[doneIndex-1].toString());
    }

    private static void printList() {
        boolean isAllDone = true;
        if (index == 0) {
            System.out.println(" This list is empty");
            return;
        }
        System.out.println(" Here are the tasks in your list:");
        for (int i=1; i<index+1; ++i) {
            if ((list[i-1].getStatusIcon()).equals(" ")) isAllDone = false;
            System.out.println(" " + i + "." + list[i-1].toString());
        }
        if (isAllDone) {
            System.out.println(" Woah, all completed! Good job!");
        }
    }

    private static void printAddedTask() {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + list[index].toString());
        index++;
        System.out.println(" Now you have " + index + " task" + (index == 1 ? " " : "s ") + "in the list.");
    }

    private static void printMessage(String command) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String help = " Try entering commands like : help, list, done, bye,\n"
                + " todo <taskName> || deadline <taskName> /by <time> || event <taskName> /at <time> ||\n"
                + " Remember: be nice!";

        String greet = "____________________________________________________________\n" +
                " Hello! I'm Duke :D" + " Be nice to me:)\n"
                + help +
                "\n____________________________________________________________";

        String byeMessage = "____________________________________________________________\n"+
                " I learnt more about you, kind human!\n I won't forget you when I take over the world one day:)\n" +
                "____________________________________________________________";

        switch(command) {
        case "greet" :
            System.out.println("Hello from\n" + logo);
            System.out.println(greet);
            break;
        case "help" :
            System.out.println(help);
            break;
        case "bye" :
            System.out.println(byeMessage);
        }
    }

}
