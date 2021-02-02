import java.util.Arrays;

public class AddCommand implements Command{
    public AddCommand(String input) {
    }

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    public static Task[] tasks = new Task[100];
    public static int numOfTasks = 0;

    public void execute(String input) {
        String[] command = input.trim().split(" ");
        String action = command[0];

        switch (action) {
            case TODO:
                Todo todoTask = new Todo(getDescription(input));
                addTask(todoTask);
                break;
            case DEADLINE:
                Deadline deadlineTask = new Deadline(getDescription(input), getBy(input));
                addTask(deadlineTask);
                break;
            case EVENT:
                Event eventTask = new Event(getDescription(input), getOn(input));
                addTask(eventTask);
                break;
        }
        end();
    }

    public String getDescription(String input) {
        if (input.contains("/")) {
            String[] split = input.trim().split("/");
            String[] commandPlusDescription = split[0].trim().split(" ");
            String[] description = new String[commandPlusDescription.length - 1];
            for (int i = 1; i < commandPlusDescription.length; i++) {
                description[i - 1] = commandPlusDescription[i];
            }

            return Arrays.toString(description).replace(",", "").replace("[", "").replace("]", "").trim();
        }
        else {
            String[] commandPlusDescription = input.trim().split(" ");
            String[] description = new String[commandPlusDescription.length - 1];
            for (int i = 1; i < commandPlusDescription.length; i++) {
                description[i - 1] = commandPlusDescription[i];
            }
            return Arrays.toString(description).replace(",", "").replace("[", "").replace("]", "").trim();
        }

    }

    public String getBy(String input) {
        String[] split = input.trim().split("/by");
        return split[1];
    }

    public String getOn(String input) {
        String[] split = input.trim().split("/at");
        return split[1];
    }

    private void addTask(Task t) {
       TaskStorer.tasks[TaskStorer.numOfTasks] = t;
        TaskStorer.numOfTasks++;
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + t.toString());
        System.out.println("Now you have " + TaskStorer.numOfTasks + " task in the list.");
    }
    public static void end() {
        System.out.println("____________________________________________________________" + System.lineSeparator());
    }
}
