import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String horizontalLine = "    ___________________________________________";
    
    private static final String helloMessage = "Hello! I'm Duke";
    private static final String assistMessage = "What can I do for you?";
    private static final String byeMessage = "Bye. Hope to see you again soon!";
    private static final String doneMessage = "Nice! I've marked this task as done:";

    private static int itemIndex = 0;
    private static HashMap<Integer,Task> storage = new HashMap<Integer,Task>();
    private static List<String> messages = new ArrayList<>();

    /**
     * Main function.
     */
    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        messages.add(helloMessage);
        messages.add(assistMessage);
        reply();
        Scanner in = new Scanner(System.in);
        handleMessage(in);
    }

    /**
     * Wraps replies with horizontal lines and indentation.
     */
    public static void reply() {
        String combined = "";
        String indentation = "     ";
        for (String message : messages) {
            combined += indentation;
            combined += message;
            combined += "\n";
        }
        System.out.println(String.format("%s\n%s%s\n", horizontalLine, combined, horizontalLine));
        messages.clear();
    }

    /**
     * If message if "list", lists all messages previously mentioned.
     * If message is "bye", exits program.
     * If message is "done", proceeds to mark tasks as completed.
     * Else, adds the message as a task.
     */
    public static void handleMessage(Scanner scanner) {
        String sentence = scanner.nextLine();
        String[] words = sentence.split(" ");

        switch (words[0]) {
            case "bye":
                messages.add(byeMessage);
                reply();
                scanner.close();
                break;
            case "list":
                messages.addAll(fetchTasks());
                reply();
                handleMessage(scanner);
                break;
            case "done":
                messages.add(doneMessage);
                if (words.length > 1) {
                    String[] inputIndexes = Arrays.copyOfRange(words, 1, words.length);
                    List<Integer> validIndexes = validateIndexes(inputIndexes);
                    if (validIndexes != null) {
                        List<String> completedTasks = completeTasks(validIndexes);
                        messages.addAll(completedTasks);
                    }
                }
                reply();
                handleMessage(scanner);
                break;
            default:
                addTask(sentence);
                messages.add("added: " + sentence);
                reply();
                handleMessage(scanner);
        }
    }

    /**
     * Saves current task.
     */
    public static void addTask(String message) {
        itemIndex++;
        Task task = new Task(itemIndex, message, false);
        storage.put(itemIndex, task);
    }

    /**
     * Fetch all previously mentioned messages.
     */
    public static List<String> fetchTasks() {
        List<String> output = new ArrayList<>();
        for (int index = 0; index < storage.size(); index++) {
            Task task = storage.get(index + 1);
            output.add(task.toString());
        }
        return output;
    }

    /**
     * Checks if task number is present and is an actual number.
     */
    public static List<Integer> validateIndexes(String[] indexes) {
        List<Integer> validIndexes = new ArrayList<>();
        for (String i : indexes) {
            try {
                int index = Integer.parseInt(i);
                if (storage.containsKey(index)) {
                    validIndexes.add(index);
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return validIndexes;
    }

    /**
     * Marks the tasks as completed by their given task numbers.
     */
    public static List<String> completeTasks(List<Integer> indexes) {
        List<String> output = new ArrayList<>();
        for (int index : indexes) {
            Task task = storage.get(index);
            output.add(String.format(" [X] %s", task.getMessage()));
            Task completedTask = new Task(index, task.getMessage(), true);
            storage.put(index, completedTask);
        }
        return output;
    }
}