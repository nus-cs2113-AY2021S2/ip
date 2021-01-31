import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String BREAK_LINE = "------------------------------------------------------------";
    private static final List<Task> tasks = new ArrayList<>();

    public static void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(BREAK_LINE + "\n" + logo + "\n" + BREAK_LINE);
    }
    public static void showGreetMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(BREAK_LINE);
    }
    public static String getUserInput() {
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(BREAK_LINE);
        return input;
    }
    public static void showList() {
        int id = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task: tasks) {
            System.out.println(id + ". " + task.toString());
            id++;
        }
        System.out.println(BREAK_LINE);
    }
    public static void addToList(String input){
        String[] tokens = input.split("/", 2);
        String description = tokens[0].substring(tokens[0].indexOf(' ')+1);
        String command;
        if (input.contains("deadline") && input.contains("/by")) {
            command = "DEADLINE";
        } else if (input.contains("event") && input.contains("/at")) {
            command = "EVENT";
        } else if (input.contains("todo")) {
            command = "TODO";
        } else {
            command = "DEFAULT";
        }
        switch (command) {
        case "DEADLINE":
            String by = tokens[1].substring(tokens[1].indexOf(' ')+1);
            Deadline deadlineTask = new Deadline(description, by);
            tasks.add(deadlineTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(deadlineTask.toString());
            if (tasks.size() == 1) {
                System.out.println("Now you have " + tasks.size() + " task in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
            break;
        case "EVENT":
            String at = tokens[1].substring(tokens[1].indexOf(' ')+1);
            Event eventTask = new Event(description, at);
            tasks.add(eventTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(eventTask.toString());
            if (tasks.size() == 1) {
                System.out.println("Now you have " + tasks.size() + " task in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
            break;
        case "TODO":
            Todo todoTask = new Todo(description);
            tasks.add(todoTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(todoTask.toString());
            if (tasks.size() == 1) {
                System.out.println("Now you have " + tasks.size() + " task in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
            break;
        default:
            System.out.println("Input not recognised, please follow the format:");
            System.out.println("* Add deadline -> 'deadline <description> /by <when>'");
            System.out.println("* Add event -> 'event <description> /at <when>'");
            System.out.println("* Add todo task -> 'todo <description>'");
            System.out.println("* Show list -> 'list'");
            System.out.println("* Mark as done -> 'done <list index number>'");
            System.out.println("* Exit -> 'bye'");
        }
        System.out.println(BREAK_LINE);
    }
    public static void markDone(int idNum) {
        if (idNum > 0 && idNum <= tasks.size()) {
            Task task = tasks.get(idNum - 1);
            task.setAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.toString());
        } else {
            System.out.println("Invalid task number");
        }
        System.out.println(BREAK_LINE);
    }
    public static void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BREAK_LINE);
    }

    public static void main(String[] args) {
        showLogo();
        showGreetMessage();
        while(true) {
            String input = getUserInput();
            String[] split = input.split("\\s+");
            String command = split[0];
            switch (command) {
            case "bye":
                showByeMessage();
                return;
            case "list":
                showList();
                break;
            case "done":
                int idNum = Integer.parseInt(split[1]);
                markDone(idNum);
                break;
            default:
                addToList(input);
                break;
            }
        }
    }
}
