package duke.viewmodel;

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
        taskManager = TaskManager.getInstance();
        messages = new ArrayList<>();
        messages.add(Constants.HELLO_MESSAGE);
        messages.add(Constants.ASSIST_MESSAGE);
        Utils.reply(messages);
        scanner = new Scanner(System.in);
    }

    /**
     * New logic instance. Greets user when called.
     * @return The logic of Duke when receiving user input.
     */
    public static Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
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
        String task = String.join(
            " ", Arrays.copyOfRange(words, 1, words.length)
        );
        List<String> response;

        try {
            switch (command) {
            case Constants.BYE:
                messages.add(Constants.BYE_MESSAGE);
                Utils.reply(messages);
                taskManager.saveTasksToDisk();
                scanner.close();
                return;
            case Constants.LIST:
                response = taskManager.fetchTasks();
                break;
            case Constants.DONE:
                response = taskManager.completeTask(task);
                break;
            case Constants.DELETE:
                response = taskManager.deleteTask(task);
                break;
            default:
                response = taskManager.addTask(command, task);
            }
            messages.addAll(response);
            Utils.reply(messages);
            handleMessage();
        } catch (DukeException dukeException) {
            Utils.notifyError(dukeException.getMessage());
            handleMessage();
        }
    }

    /**
     * Checks if the given string is an integer.
     * @param s The string to be checked.
     * @return True if string is an integer.
     */
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}