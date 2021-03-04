package commands;

import myexceptions.BlankDescriptionException;
import myexceptions.OutOfRangeException;
import parser.Parser;
import tasklist.Task;
import tasklist.Tasklist;

import static common.Messages.MARKED_AS_DONE_MESSAGE;

/**
 * Method that marks a specified task as done
 *
 * @param userInput This is the task number that the user wants to
 *                  mark as completed. Task number can be accessed using
 *                  list method.
 */
public class MarkAsDone {
    public static void execute(String userInput) throws BlankDescriptionException, OutOfRangeException {
        int index = Parser.parsingMarkAsDone(userInput);

        Task task = Tasklist.getIndex(index);
        if (Tasklist.isCorrupted(task)) {
            System.out.println("Task is corrupted. Please delete.");
        } else {
            Tasklist.markTaskAsDone(index);
            System.out.println(MARKED_AS_DONE_MESSAGE);
        }

    }
}
