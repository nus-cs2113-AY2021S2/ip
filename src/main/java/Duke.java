import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String BREAKLINE = "------------------------------------------------------------";
    private static List<Task> userList = new ArrayList<Task>();

    public static void showLogo() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(BREAKLINE + "\n" + logo + "\n" + BREAKLINE);
    }
    public static void greetMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(BREAKLINE);
    }
    public static String getUserInput() {
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(BREAKLINE);
        return input;
    }
    public static void showList() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task i: userList) {
            System.out.println(count + ".[" + i.getStatusIcon() + "] " + i.description);
            count++;
        }
        System.out.println(BREAKLINE);
    }
    public static void addToList(String input){
        Task newTask = new Task(input);
        userList.add(newTask);
        System.out.println("Added: " + input);
        System.out.println(BREAKLINE);
    }
    public static void markDone(int idNum) {
        Task item = userList.get(idNum - 1);
        item.setAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + item.getStatusIcon() + "] " + item.description);
        System.out.println(BREAKLINE);
    }
    public static void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BREAKLINE);
    }

    public static void main(String[] args) {
        showLogo();
        greetMessage();
        while(true) {
            String input = getUserInput();
            String[] split = input.split("\\s+");
            String command = split[0];
            switch (command) {
            case "bye":
                byeMessage();
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
