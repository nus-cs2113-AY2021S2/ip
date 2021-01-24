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
            System.out.println(id + ".[" + task.getStatusIcon() + "] " + task.description);
            id++;
        }
        System.out.println(BREAK_LINE);
    }
    public static void addToList(String input){
        Task newTask = new Task(input);
        tasks.add(newTask);
        System.out.println("Added: " + input);
        System.out.println(BREAK_LINE);
    }
    public static void markDone(int idNum) {
        Task task = tasks.get(idNum - 1);
        task.setAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.description);
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
