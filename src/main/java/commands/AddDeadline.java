package commands;

import myexceptions.BlankDescriptionException;
import myexceptions.MissingDateException;
import tasklist.Task;
import parser.Parser;
import tasklist.Tasklist;
import static common.Messages.BLANK_EXCEPTION_MESSAGE;
import static common.Messages.MISSING_DATE_EXCEPTION_MESSAGE;

/**
 * Method that adds deadline to the tasklist
 * @param userInput This is the string input by user
 */
public class AddDeadline {

    public static void execute(String userInput) throws BlankDescriptionException, MissingDateException {
        try{
            Task deadline = Parser.parsingDeadline(userInput);
            Tasklist.addTask(deadline);
        } catch (BlankDescriptionException e) {
            System.out.println(BLANK_EXCEPTION_MESSAGE);
        }
        catch (MissingDateException e) {
            System.out.println(MISSING_DATE_EXCEPTION_MESSAGE);
        }

    }
}
