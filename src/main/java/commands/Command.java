package commands;

import common.Constants;
import storage.Storage;
import tasks.TaskManager;

import java.util.TreeSet;

public abstract class Command {

    protected static final TaskManager taskManager = new TaskManager();
    protected static final Constants constants = new Constants();
    private static final Storage storage = new Storage();

    public void execute(){}


    /**
     * Prints successfully added message.
     *
     * @param input Input value by user.
     */
    protected static void printAddedContent(String input) {
        System.out.println(constants.LINE);
        System.out.println("8K: Added \"" + input + "\" to list.");
        System.out.println(constants.LINE);
    }


    /**
     * Updates file.
     */
    protected void updateFile() {
        try {
            storage.saveFile();
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_SAVE_ERROR);
        }
    }


    /**
     * Processes index task from list.
     * Returns INVALID_INDEX if invalid.
     *
     * @param stringIndex Index of task to remove.
     */
    public static int processIndex(String stringIndex) {
        try {
            int intIndex = Integer.parseInt(stringIndex) - 1;
            if (intIndex >= taskManager.getTaskCount() || intIndex < 0) {
                //Out of bounds
                throw new IndexOutOfBoundsException();
            }
            return intIndex;
        } catch (Exception e) {
            //Invalid index
            return constants.INVALID_INDEX;
        }
    }


    /**
     * Returns valid index set.
     */
    protected static TreeSet<Integer> processMultipleIndices(String input) {
        String[] indices = input.split(" ");
        TreeSet<Integer> validIndices = new TreeSet<>();
        for (String rawIndex : indices) {
            int index = processIndex(rawIndex);
            if (index != constants.INVALID_INDEX) {
                validIndices.add(index);
            }
        }
        return validIndices;
    }

}
