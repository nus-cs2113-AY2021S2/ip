import java.nio.file.Watchable;
import java.util.Scanner;

public class Duke {

    public static final String LINE = "_____________________________________________________\n";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String WELCOME_MESSAGE = LINE + "Hello from\n" + LOGO
            + "What can I do for you today?\n" + LINE;
    public static final String BYE_MESSAGE = LINE + "Goodbye! Hope to see you around soon!\n" + LINE;
    public static final String HELP_MESSAGE = LINE + "Add task:\n"
            + "Command prefix: NONE\n"
            + "Argument(s): task\n\n"
            + "Show tasks:\n"
            + "Command prefix: list\n\n"
            + "Check task off:\n"
            + "Command prefix: done\n"
            + "Argument(s): task number\n";
    public static final String ADD_MESSAGE = " added to list\n";
    public static final String TASK_COMPLETE_MESSAGE = "Task completed!\n";
    public static final String TASK_ALREADY_COMPLETE_MESSAGE = " is already complete\n";
    public static final String ALL_TASKS_COMPLETE_MESSAGE = "All remaining tasks completed!\n";
    public static final String INVALID_ARGUMENT_MESSAGE = "Please provide a valid argument\n";



    public static void main(String[] args) {
        System.out.print(WELCOME_MESSAGE);

        Task[] list = new Task[100];
        int index = 0;

        Scanner scan = new Scanner(System.in);
        String in = scan.nextLine();


        String[] input = in.split(" ");


        while (!input[0].equalsIgnoreCase("bye")) {
            if(input[0].equalsIgnoreCase("list")) {
                printList(list, index);
            } else if (input[0].equalsIgnoreCase("done")) {
                if (input.length != 2) {
                    System.out.println("Please provide 1 argument");
                } else {
                    try {
                        int ind = Integer.parseInt(input[1]);
                        markAsDone(list, ind, index);
                    } catch (NumberFormatException e) {
                        System.out.print(LINE + INVALID_ARGUMENT_MESSAGE + LINE);
                    }
                }
            } else if(input[0].equalsIgnoreCase("help") && input.length==1) {
                System.out.print(LINE + HELP_MESSAGE + LINE);
            } else {
                add(in, list, index);
                index++;
            }
            in = scan.nextLine();
            input = in.split(" ");
        }

        System.out.print(BYE_MESSAGE);
    }

    public static void add(String input, Task[] list, int index) {
        list[index] = new Task(input);
        System.out.print(LINE + "\"" + list[index].getDesc() + "\"" + ADD_MESSAGE + LINE);
    }

    public static void printList(Task[] list, int index) {
        System.out.print(LINE);
        for(int i=0; i<index; i++) {
            if (i<9) {
                System.out.print(" ");
            }
            System.out.println(i+1 + list[i].getStatusSymbol() + list[i].getDesc());
        }
        System.out.print(LINE);
    }

    public static void markAsDone(Task[] list, int taskNo, int max) {
        if (taskNo <= max && taskNo > 0) { //no. is valid
            if (list[taskNo-1].getStatus()) {
                System.out.print(LINE + "Task \"" + list[taskNo-1].getDesc() + "\""
                        + TASK_ALREADY_COMPLETE_MESSAGE + LINE);
            } else {
                list[taskNo-1].check();
                System.out.print(LINE + TASK_COMPLETE_MESSAGE);
                System.out.println("  " + list[taskNo - 1].getStatusSymbol() + list[taskNo - 1].getDesc());
                if (list[taskNo-1].getTasksRemaining() == 0) {
                    System.out.print(ALL_TASKS_COMPLETE_MESSAGE);
                }
                System.out.print(LINE);
            }
        } else {
            System.out.print(LINE + INVALID_ARGUMENT_MESSAGE+ LINE);
        }
    }
}
