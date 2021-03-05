package Baggie.Commands;

import Baggie.Baggie;
import Baggie.UI.PrintMessages;

import static Baggie.UI.TEXT.*;

public class PrintListCommand extends Baggie {
    /**
     * Prints list from start index to end index
     * Prints both list and a single task
     * @param startIndex
     * @param endIndex
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
