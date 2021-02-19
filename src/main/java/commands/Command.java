package commands;

import common.Constants;
import storage.Storage;
import tasks.TaskManager;

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
     * Processes index task from list. Returns -1 if invalid.
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

}
