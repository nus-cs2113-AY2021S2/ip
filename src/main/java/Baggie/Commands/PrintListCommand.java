package Baggie.Commands;

import Baggie.Baggie;
import static Baggie.UI.TEXT.*;

public class PrintListCommand extends Baggie {
    /**
     * Prints list from start index to end index.
     * Prints both list and a single task.
     *
     * @param startIndex Index of the start to print list.
     * @param endIndex Index of the end to print list.
     */
    public static void printList(int startIndex, int endIndex) {
        if (endIndex == 0) {
            System.out.println(LIST_EMPTY);
        } else {
            for(int i = startIndex; i < endIndex; ++i) {
                System.out.println(SPACE + (i + 1) + SEMICOLON + lists.get(i).toString());
            }
        }
    }
}
