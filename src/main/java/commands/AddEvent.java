package commands;

import myexceptions.BlankDescriptionException;
import myexceptions.MissingDateException;
import parser.Parser;
import tasklist.Task;
import tasklist.Tasklist;
import static common.Messages.BLANK_EXCEPTION_MESSAGE;
import static common.Messages.MISSING_DATE_EXCEPTION_MESSAGE;

/**
 * Method that adds an event to the tasklist
 * @param userInput This is the string input by the user.
 */
public class AddEvent {

    public static void execute(String userInput) throws BlankDescriptionException, MissingDateException {
        try {
            Task event = Parser.parsingEvent(userInput);
            Tasklist.addTask(event);
        } catch (BlankDescriptionException e) {
            System.out.println(BLANK_EXCEPTION_MESSAGE);
        } catch (MissingDateException e) {
            System.out.println(MISSING_DATE_EXCEPTION_MESSAGE);
        }

    }
}
