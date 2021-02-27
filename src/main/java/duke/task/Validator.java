package duke.task;

public class Validator {
    private static final int INDEX_OUT_OF_BOUNDS = -1;
    private static final int INVALID_INDEX_TYPE = -2;

    /**
     * Validates the index that is parsed into the program. Should
     * the index be out of bound or not an integer, an error code
     * will be returned.
     *
     * @param index the raw input from the user or loaded file.
     * @return the validated index of the Task object in the ArrayList, or
     * the error codes for invalid inputs.
     */
    public static int validateIndex(String index) {
        try {
            int indexOfTask = Integer.parseInt(index) - 1;
            if (indexOfTask < 0 || TaskList.taskArray.size() == 0) {
                return INDEX_OUT_OF_BOUNDS;
            } else if (indexOfTask >= TaskList.taskArray.size()) {
                return INDEX_OUT_OF_BOUNDS;
            }
            return indexOfTask;
        } catch (NumberFormatException e) {
            return INVALID_INDEX_TYPE;
        }
    }
}
