package dukchess.ui;

import java.util.List;

import dukchess.entity.Task;

/**
 * Class that contains utilites for enhanced user greeting and instructions
 */
public final class Ui {
    /**
     * Utility for printing all unexpected exceptions
     * @param e - any exception
     */
    public static void printErrorMessage(Exception e) {
        System.err.println("An unexpected error occurred:");
        e.printStackTrace();
    }

    /**
     * Utility for printing an error message
     * @param customErrorMessage - any error message
     */
    public static void printErrorMessage(String customErrorMessage) {
        System.out.println(customErrorMessage);
    }
    /**
     * Utility for printing a success message
     * @param successMessage - any success message
     */
    public static void printSuccessMessage(String successMessage) {
        System.out.println(successMessage);
    }

    /**
     * Utility function to print a list of tasks
     * @param tasksToPrint a list of tasks to print
     */
    public static void printListOfTasks(List<Task> tasksToPrint) {
        for (int i = 0; i < tasksToPrint.size(); i++) {
            Task taskToPrint = tasksToPrint.get(i);
            System.out.printf(">>> %d. %s\n", i + 1, taskToPrint.toString());
        }
    }

    /**
     * Prints out the initial greeting.
     */
    public static void printGreeting() {
        String welcomeText = "Hello, I am a PC assistant\n"
                + "How do you want to set your tasks today?";
        System.out.println(welcomeText);
    }

    /**
     * Prints out the goodbye message.
     */
    public static void sayGoodbye() {
        String goodbyeText = "Goodbye, hope to see you soon!";
        System.out.println(goodbyeText);
    }

    /**
     * Prints out subsequent input prompts.
     */
    public static void promptInputAgain() {
        System.out.println("What other tasks do you want to do?");
    }

}
