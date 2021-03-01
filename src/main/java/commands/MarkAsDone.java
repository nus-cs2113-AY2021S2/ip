package commands;

import myexceptions.BlankDescriptionException;
import myexceptions.OutOfRangeException;
import parser.Parser;
import tasklist.Task;
import tasklist.Tasklist;

import static common.Messages.MARKED_AS_DONE_MESSAGE;

public class MarkAsDone {
    public static void execute(String userInput) throws BlankDescriptionException, OutOfRangeException {
        int index = Parser.parsingMarkAsDone(userInput);
        Tasklist.markTaskAsDone(index);
        System.out.println(MARKED_AS_DONE_MESSAGE);
    }
}
