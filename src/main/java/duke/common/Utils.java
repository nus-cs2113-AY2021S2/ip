package duke.common;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Utility methods
 */
public class Utils {

    /**
     * Takes in user input and splits it into 2 as long as there is whitespace
     * character in the middle.
     * Reused from Lecture Week 4 Contacts program.
     * @param userInput
     * @return Array of command type and command arguments
     */
    public static String[] getCommandAndArgs(String userInput) {
        String[] split = userInput.split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0], "" };
    }

    /**
     * Checks if input task number is valid.
     * @param tasks, number
     * @return validity of input.
     */
    public static boolean isValidTaskNumber(ArrayList<Task> tasks, String number) {
        try {
            tasks.get(Integer.parseInt(number) - 1);
        } catch (NumberFormatException e){
            return false;
        } catch (IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }
}
