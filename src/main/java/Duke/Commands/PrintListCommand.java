package Duke.Commands;

import Duke.Duke;

import static Duke.UI.TEXT.LIST_EMPTY;

public class PrintListCommand extends Duke {
    /**
     * Print list from start index to end index
     * Used to print both list and a single task
     * @param startIndex
     * @param endIndex
     */
    public static void printList(int startIndex, int endIndex) {
        if (endIndex == 0) {
            System.out.println(LIST_EMPTY);
        } else {
            for(int i = startIndex; i < endIndex; ++i) {
                System.out.println(" " + (i + 1) + ": " + lists.get(i).toString());
            }
        }
    }
}
