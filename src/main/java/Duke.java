import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private static final String horizontalLine = "    ___________________________________________";
    private static final String helloMessage = "Hello! I'm Duke";
    private static final String assistMessage = "What can I do for you?";
    private static final String byeMessage = "Bye. Hope to see you again soon!";
    private static final String doneMessage = "Nice! I've marked this task as done:";
    private static final String addedMessage = "Got it. I've added this task:";
    private static final String errorMessage = "Invalid input, what task is this?";
    private static final String doneErrorMessage = "Done what? Input the task number please!";
    private static final String todoErrorMessage = "What is the task? Please add one!";
    private static final String deadlineErrorMessage = "When is the deadline? Please add one!";
    private static final String eventErrorMessage = "When is the event? Please add one!";

    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private static int itemIndex = 0;
    private static HashMap<Integer,Task> storage = new HashMap<Integer,Task>();
    private static List<String> messages = new ArrayList<>();

    /**
     * Main function.
     */
    public static void main(String[] args) {
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
            if (message.isEmpty()) {
                continue;
            }
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
        String command = words[0];
        String task = "";
        String taskMessage = "";
        String tasksLeft = "";
        List<Integer> tasksToComplete = new ArrayList<>();
        
        if (words.length > 1) {
            for (int i = 1; i < words.length; i++) {
                String currentWord = words[i];
                if (isInteger(currentWord)) {
                    int taskIndex = Integer.parseInt(currentWord);
                    if (isValidTask(taskIndex)) {
                        tasksToComplete.add(taskIndex);
                    }
                } else {
                    task += currentWord + " ";
                }
            }
        }

        switch (command) {
            case BYE:
                messages.add(byeMessage);
                reply();
                scanner.close();
                break;
            case LIST:
                messages.addAll(fetchTasks());
                reply();
                handleMessage(scanner);
                break;
            case DONE:
                if (!tasksToComplete.isEmpty()) {
                    List<String> completedTasks = completeTasks(tasksToComplete);
                    messages.add(doneMessage);
                    messages.addAll(completedTasks);
                    tasksLeft = String.format("Tasks left: %d",
                            getIncompleteTasksCount()
                    );
                    messages.add(tasksLeft);
                } else {
                    messages.add(doneErrorMessage);
                }
                reply();
                handleMessage(scanner);
                break;
            case TODO:
                taskMessage = addTask(TODO, task);
                if (taskMessage.isEmpty()) {
                    messages.add(todoErrorMessage);
                    reply();
                    handleMessage(scanner);
                    break;
                }
                messages.add(addedMessage);
                messages.add(taskMessage);
                tasksLeft = String.format("Tasks left: %d",
                        getIncompleteTasksCount()
                );
                messages.add(tasksLeft);
                reply();
                handleMessage(scanner);
                break;
            case DEADLINE:
                taskMessage = addTask(DEADLINE, task);
                if (taskMessage.isEmpty()) {
                    messages.add(deadlineErrorMessage);
                    reply();
                    handleMessage(scanner);
                    break;
                }
                messages.add(addedMessage);
                messages.add(taskMessage);
                tasksLeft = String.format("Tasks left: %d",
                        getIncompleteTasksCount()
                );
                messages.add(tasksLeft);
                reply();
                handleMessage(scanner);
                break;
            case EVENT:
                taskMessage = addTask(EVENT, task);
                if (taskMessage.isEmpty()) {
                    messages.add(eventErrorMessage);
                    reply();
                    handleMessage(scanner);
                    break;
                }
                messages.add(addedMessage);
                messages.add(taskMessage);
                tasksLeft = String.format("Tasks left: %d",
                        getIncompleteTasksCount()
                );
                messages.add(tasksLeft);
                reply();
                handleMessage(scanner);
                break;
            default:
                messages.add(errorMessage);
                reply();
                handleMessage(scanner);
        }
    }

    /**
     * Saves current task.
     */
    public static String addTask(String taskType, String message) {
        itemIndex++;
        Task task;
        switch (taskType) {
            case TODO:
                task = new Todo(itemIndex, message, false);
                break;
            case DEADLINE:
                String[] deadlineTask = message.split("/by");
                if (deadlineTask.length <= 1) {
                    return "";
                }
                String details = deadlineTask[0].trim();
                String time = deadlineTask[1].trim();
                task = new Deadline(itemIndex, details, false, time);
                break;
            case EVENT:
                String[] eventTask = message.split("/at");
                if (eventTask.length <= 1) {
                    return "";
                }
                details = eventTask[0].trim();
                time = eventTask[1].trim();
                task = new Event(itemIndex, details, false, time);
                break;
            default:
                return "";
        }
        storage.put(itemIndex, task);
        return task.getMessage();
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
     * Checks if tasks exist.
     */
    public static boolean isValidTask(int taskIndex) {
        if (storage.containsKey(taskIndex)) {
            return true;
        }
        return false;
    }

    /**
     * Marks the tasks as completed by their given task numbers.
     */
    public static List<String> completeTasks(List<Integer> indexes) {
        List<String> output = new ArrayList<>();
        for (int index : indexes) {
            Task task = storage.get(index);
            output.add(String.format(" [X] %s", task.getDescription()));
            Todo completedTask = new Todo(index, task.getDescription(), true);
            storage.put(index, completedTask);
        }
        return output;
    }

    /**
     * Get number of undone tasks in storage.
     */
    public static int getIncompleteTasksCount() {
        int count = 0;
        for (Task task : storage.values()) {
            if (!task.isTaskDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Checks if the given string is an integer. 
     */
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch (NumberFormatException e) { 
            return false; 
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}