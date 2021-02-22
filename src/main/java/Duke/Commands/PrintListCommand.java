package Duke.Commands;

import Duke.Duke;

public class PrintListCommand extends Duke {
    /**
     * Print list from start index to end index
     * Used to print both list and a single task
     * @param startIndex
     * @param endIndex
     */
    public static void printList(int startIndex, int endIndex) {
        if (endIndex == 0) {
            System.out.println("List is empty :o\n");
        } else {
            for(int i = startIndex; i < endIndex; ++i) {
                System.out.println(" " + (i + 1) + ": " + lists.get(i).toString());
            }
        }
    }
}
