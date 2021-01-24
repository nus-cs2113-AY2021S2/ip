import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        startGreetings();
        echo();
        endGreetings();
    }

    public static void startGreetings() {
        String greetings = "-------------------------------------\n"
                         + "Hello! I'm Duke\n"
                         + "What can I do for you?\n\n"
                         + "-------------------------------------";
        System.out.println(greetings);
    }

    public static void endGreetings() {
        String exitStatements = "-------------------------------------\n"
                              + "Bye. Hope to see you again soon!\n"
                              + "-------------------------------------";
        System.out.println(exitStatements);
    }

    public static void echo() {
        ArrayList<Task> tasks = new ArrayList<>();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                displayTextList(tasks);
            }
            else if (line.contains("done")) {
                markCompletedTasks(tasks, line);
            }
            else {
                System.out.println("-------------------------------------");
                System.out.println("added: " + line);
                System.out.println("-------------------------------------");
                tasks.add(new Task(line));
            }
            line = in.nextLine();
        }
    }

    public static void displayTextList(ArrayList<Task> tasks) {
        int counter = 0;
        System.out.println("-------------------------------------");
        System.out.println("Here are the tasks in your list");
        while (counter < tasks.size()) {
            Task task = tasks.get(counter);
            System.out.print((counter+1) + ".");

            if (task.getCompleted()) { System.out.print("[X] "); }
            else { System.out.print("[ ] "); }

            System.out.println(task.getTaskName());
            counter ++;
        }
        System.out.println("-------------------------------------");
    }

    public static void markCompletedTasks(ArrayList<Task> tasks, String line) {
        System.out.println("-------------------------------------");
        System.out.println("Nice! I've marked this task as done:");

        List<String> indexesStrings = Arrays.stream(line.trim().split(" "))
                .distinct()
                .filter(x -> x.matches("\\d+"))
                .collect(Collectors.toList());
        for (String index: indexesStrings) {
            int indexInt = Integer.parseInt(index) - 1;
            if (indexInt < tasks.size()) {
                tasks.get(indexInt).setCompleted(true);
            }
            System.out.println("[X] " + tasks.get(indexInt).getTaskName());
        }
        System.out.println("-------------------------------------");
    }
}
