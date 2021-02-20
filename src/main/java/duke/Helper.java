package duke;

public class Helper {
    // Find the index of a string in a string array
    // Return the index if found and -1 if not found
    protected static int findIndex(String[] haystack, String needle) {
        for (int i = 0; i < haystack.length; i += 1) {
            if (haystack[i].equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
