package duke.task;

/**
 * Represents a string manipulator.
 */
public class StringParser {

    public static final int OUT_OF_BOUND_INDEX = -1;
    public static final int DONE_TASK_NUMBER_INDEX = 5;
    public static final int DELETED_TASK_NUMBER_INDEX = 7;

    /**
     * Returns the first word of the string
     * @param input the whole string
     * @return first word of the string
     */
    public String getFirstWord(String input) {
        return input.split(" ")[0];
    }

    /**
     * Returns the whole string after the first slash in the string
     * @param input the whole string
     * @return the string after the slash in the sentence
     */
    public String getStringAfterSlash(String input) {
        return input.substring(input.indexOf("/") + 1 + 2 + 1);
    }

    /**
     * Returns the index after the first whitespace in the string
     * @param input the whole string
     * @return the index after the first whitespace
     */
    public String getIndexOfStringAfterWhiteSpace(String input) {
        int firstWhiteSpaceIndex = input.indexOf(" ");
        if (firstWhiteSpaceIndex == OUT_OF_BOUND_INDEX) {
            return input.substring(input.length());
        }
        return input.substring(firstWhiteSpaceIndex + 1);
    }

    /**
     * Returns the string found in between the first whitespace and the first slash
     * Meant for getting the task description of (for eg: deadline task)
     * @param input the whole string
     * @return the string found in between the first whitespace and the first slash
     */
    public String getStringAfterWhiteSpaceAndBeforeSlash(String input) {
        int firstWhiteSpaceIndex = input.indexOf(" ");
        int firstSlashIndex = input.indexOf("/");
        if (firstWhiteSpaceIndex == OUT_OF_BOUND_INDEX) {
            return input.substring(input.length());
        }
        if (firstSlashIndex == OUT_OF_BOUND_INDEX) {
            return input.substring(firstWhiteSpaceIndex + 1, input.length());
        }
        return input.substring(firstWhiteSpaceIndex + 1, firstSlashIndex);
    }

    /**
     * return the task number that is done
     * @param input whole string
     * @return number of task that is done
     */
    public int getTaskNumberDone(String input) {
        return Integer.parseInt(input.substring(DONE_TASK_NUMBER_INDEX));
    }

    /**
     * return the task number that is deleted
     * @param input the whole string
     * @return number of task that is deleted
     */
    public int getTaskNumberDeleted(String input) {
        return Integer.parseInt(input.substring(DELETED_TASK_NUMBER_INDEX));
    }

    /**
     * return the keyword for finding matches
     * @param input whole string
     * @return keyword to find a match
     */
    public String getKeyword(String input) {
        String keyword = input.split(" ")[1];
        return keyword;
    }

}
