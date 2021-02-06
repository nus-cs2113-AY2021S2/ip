import java.util.*;
import java.util.stream.Collectors;

public class Duke {
    private static final String lineSpacing = "\t----------------------------------";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        initialGreetings();
        echo(); // Method contains main while loop
        finalGreetings();
    }

    public static void initialGreetings() {
        String greetings = "\tHello! I'm Duke\n" + "\tWhat can I do for you?\n\n";
        System.out.println(lineSpacing);
        System.out.println(greetings);
        System.out.println(lineSpacing);
    }

    public static void finalGreetings() {
        String exitStatements = "\tBye. Hope to see you again soon!\n";
        System.out.println(lineSpacing);
        System.out.println(exitStatements);
        System.out.println(lineSpacing);
    }

    public static void echo() {
        ArrayList<Task> tasks = new ArrayList<>();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            List<String> userCommands = Arrays.asList(line.split(" "));
            if (userCommands.get(0).equals("list")) {
                displayTaskList(tasks);
            } else if (userCommands.get(0).equals("done")) {
                markCompletedTasks(tasks, userCommands.subList(1, userCommands.size()));
            } else {
                appendNewTask(tasks, userCommands);
            }
            line = in.nextLine();
        }
    }

    public static void displayTaskList(ArrayList<Task> tasks) {
        int counter = 0;
        System.out.println(lineSpacing);
        System.out.println("\tHere are the tasks in your list:");
        while (counter < tasks.size()) {
            Task task = tasks.get(counter);
            System.out.print("\t" + (counter+1) + ".");
            task.printTask();
            counter ++;
        }
        System.out.println(lineSpacing);
    }

    public static void markCompletedTasks(ArrayList<Task> tasks, List<String> taskIndexes) {
        System.out.println(lineSpacing);
        System.out.println("\tNice! I've marked this task as done:");

        for (String index: taskIndexes) {
            int indexInt;
            try {
                indexInt = Integer.parseInt(index) - 1;
            } catch (NumberFormatException e) {
                continue;
            }
            if (indexInt < tasks.size()) {
                tasks.get(indexInt).setCompleted();
            }
            System.out.print("\t");
            tasks.get(indexInt).printTask();
        }
        System.out.println(lineSpacing);
    }

    public static void appendNewTask(ArrayList<Task> tasks, List<String> instructions) {
        System.out.println("\t-------------------------------------");
        switch (instructions.get(0)) {
        case "todo":
            List<String> taskNameTodo = instructions.subList(1, instructions.size());
            tasks.add(new ToDo(
                    String.join(" ", taskNameTodo),
                    instructions.get(0)
            ));
            System.out.println("\tGot it. I've added this task: ");
            break;
        case "deadline":
            int indexDeadline = instructions.indexOf("/by");
            List<String> taskNameDeadline = instructions.subList(1, indexDeadline);
            List<String> timeConstraintDeadline = instructions.subList(indexDeadline+1, instructions.size());
            tasks.add(new Deadline(
                    String.join(" ", taskNameDeadline),
                    instructions.get(0),
                    String.join(" ", timeConstraintDeadline)
            ));
            System.out.println("\tGot it. I've added this task: ");
            break;
        case "event":
            int indexEvent = instructions.indexOf("/at");
            List<String> taskNameEvent = instructions.subList(1, indexEvent);
            List<String> timeConstraintEvent = instructions.subList(indexEvent+1, instructions.size());
            tasks.add(new Event(
                    String.join(" ", taskNameEvent),
                    instructions.get(0),
                    String.join(" ", timeConstraintEvent)
            ));
            System.out.println("\tGot it. I've added this task: ");
            break;
        default:
            System.out.println("\tInvalid instruction submitted.");
            return;
        }
        System.out.print("\t  ");
        tasks.get(tasks.size()-1).printTask();
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
        System.out.println("\t-------------------------------------");
    }
}
