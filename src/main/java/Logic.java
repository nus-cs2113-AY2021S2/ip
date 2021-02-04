import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Logic {

    private static Logic instance = null;
    private static List<String> messages;
    private static Scanner scanner;
    private static TaskManager taskManager;

    private Logic() {
        messages = new ArrayList<>();
        messages.add(Constants.helloMessage);
        messages.add(Constants.assistMessage);
        Utils.reply(messages);
        scanner = new Scanner(System.in);
        taskManager = TaskManager.getInstance();
    }

    /**
     * New logic instance. Greets user when called.
     */
    public static Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }

    /**
     * Fetches all valid tasks user wants to complete, by task index number.
     */
    private List<Integer> tasksToComplete(String[] indexes) {
        List<Integer> tasks = new ArrayList<>();
        for (String index : indexes) {
            if (Utils.isInteger(index)) {
                int taskIndex = Integer.parseInt(index);
                if (taskManager.isTaskPresent(taskIndex)) {
                    tasks.add(taskIndex);
                }
            }
        }
        return tasks;
    }

    /**
     * If message if "list", lists all messages previously mentioned.
     * If message is "bye", exits program.
     * If message is "done", proceeds to mark tasks as completed.
     * Else, adds the message as a task.
     */
    public void handleMessage() {
        String sentence = scanner.nextLine();
        String[] words = sentence.split(" ");
        String command = words[0];
        String task = String.join(" ", Arrays.copyOfRange(words, 1, words.length));

        switch (command) {
            case Constants.BYE:
                messages.add(Constants.byeMessage);
                Utils.reply(messages);
                scanner.close();
                return;
            case Constants.LIST:
                List<String> response = taskManager.fetchTasks();
                messages.addAll(response);
                break;
            case Constants.DONE:
                List<Integer> indexes = tasksToComplete(Arrays.copyOfRange(words, 1, words.length));
                if (!indexes.isEmpty()) {
                    response = taskManager.completeTasks(indexes);
                    messages.addAll(response);
                } else {
                    messages.add(Constants.doneErrorMessage);
                }
                break;
            case Constants.TODO:
            case Constants.DEADLINE:
            case Constants.EVENT:
                if (task.isEmpty()) {
                    messages.add(Constants.errorMessage);
                } else {
                    response = taskManager.addTask(command, task);
                    messages.addAll(response);
                }
                break;
            default:
                messages.add(Constants.errorMessage);
        }
        Utils.reply(messages);
        handleMessage();
    }
}