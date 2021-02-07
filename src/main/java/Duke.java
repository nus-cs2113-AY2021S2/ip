import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "\t_______________________________\n";
    private static final String LOGO =
            "▒▒▒▒▒▒▒█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n"
                    + "▒▒▒▒▒▒▒█░▒▒▒▒▒▒▒▓▒▒▓▒▒▒▒▒▒▒░█\n"
                    + "▒▒▒▒▒▒▒█░▒▒▓▒▒▒▒▒▒▒▒▒▄▄▒▓▒▒░█░▄▄\n"
                    + "▒▒▄▀▀▄▄█░▒▒▒▒▒▒▓▒▒▒▒█░░▀▄▄▄▄▄▀░░█\n"
                    + "▒▒█░░░░█░▒▒▒▒▒▒▒▒▒▒▒█░░░░░░░░░░░█\n"
                    + "▒▒▒▀▀▄▄█░▒▒▒▒▓▒▒▒▓▒█░░░█▒░░░░█▒░░█\n"
                    + "▒▒▒▒▒▒▒█░▒▓▒▒▒▒▓▒▒▒█░░░░░░░▀░░░░░█\n"
                    + "▒▒▒▒▒▄▄█░▒▒▒▓▒▒▒▒▒▒▒█░░█▄▄█▄▄█░░█\n"
                    + "▒▒▒▒█░░░█▄▄▄▄▄▄▄▄▄▄█░█▄▄▄▄▄▄▄▄▄█\n"
                    + "▒▒▒▒█▄▄█░░█▄▄█░░░░░░█▄▄█░░█▄▄█\n"
                    + " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";


    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static String checkSingular() {
        if (tasks.size() > 1) {
            return " tasks ";
        }
        return " task ";
    }

    private static void addTask(String taskName) {
        Task currentTask = new Task(taskName);
        tasks.add(currentTask);
        System.out.print(DIVIDER + "\tadded " + currentTask.taskDescription + "\n" + DIVIDER);
    }

    private static void listTask() {
        System.out.print(DIVIDER);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println("\t" + (i + 1) + "." +
                    tasks.get(i).toString());
        }
        System.out.print(DIVIDER);
    }

    private static void markTaskDone(int taskNumber) {
        Task currentTask = tasks.get(taskNumber - 1);
        currentTask.markAsDone();
        System.out.print(DIVIDER + "\tNice! I've marked this task as done:" +
                "\n\t" + currentTask.toString() + "\n" + DIVIDER);
    }

    public static void addToDo(String input) {
        String[] splitCommand = input.split(" ", 2);
        String taskDescription = splitCommand[1].trim();
        Task currentTask = new ToDo(taskDescription);
        tasks.add(currentTask);
        System.out.println(DIVIDER + "\tGot it. I've added this task:\n" +
                "\t\t" + currentTask.toString());
        System.out.print("\tNow you have " + tasks.size() + checkSingular() + "in your list." + "\n" + DIVIDER);
    }

    public static void addDeadline(String input) {
        String[] splitCommand = input.split(" ", 2);
        String[] splitDescription = splitCommand[1].split("/by", 2);
        String taskDescription = splitDescription[0].trim();
        String deadline = splitDescription[1].trim();
        Task currentTask = new Deadline(taskDescription, deadline);
        tasks.add(currentTask);
        System.out.println(DIVIDER + "\tGot it. I've added this task:\n\t\t" +
                currentTask.toString());
        System.out.print("\tNow you have " + tasks.size() + checkSingular() + "in your list.\n" + DIVIDER);
    }

    public static void addEvent(String input) {
        String[] splitCommand = input.split(" ", 2);
        String[] splitDescription = splitCommand[1].split("/at", 2);
        String taskDescription = splitDescription[0].trim();
        String timeDetails = splitDescription[1].trim();
        Task currentTask = new Event(taskDescription, timeDetails);
        tasks.add(currentTask);
        System.out.println(DIVIDER + "\tGot it. I've added this task:\n\t\t" +
                currentTask.toString());
        System.out.print("\tNow you have " + tasks.size() + checkSingular() + "in your list.\n" + DIVIDER);

    }

    private static void exitDuke() {
        System.out.print("Bye. Hope to see you again soon!\n");
    }


    public static void main(String[] args) {
        System.out.print(LOGO);
        System.out.print("Hello! I'm Duke\nWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String input;

        while (in.hasNext()) {
            input = in.nextLine();
            if (input.equals("list")) {
                listTask();
            } else if (input.contains("done")) {
                //task number can be found on 5th index of input
                int taskNumber = Integer.parseInt(input.substring(5));
                markTaskDone(taskNumber);
            } else if (input.contains("todo")) {
                addToDo(input);
            } else if (input.contains("deadline")) {
                addDeadline(input);
            } else if (input.contains("event")) {
                addEvent(input);
            } else if (input.equals("bye")) {
                exitDuke();
                break;
            } else {
                addTask(input);
            }
        }
        in.close();

    }
}
