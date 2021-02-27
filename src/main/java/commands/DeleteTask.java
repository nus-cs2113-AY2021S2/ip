package commands;

import myexceptions.BlankDescriptionException;
import myexceptions.EmptyListException;
import myexceptions.OutOfRangeException;
import parser.Parser;
import tasklist.Task;
import tasklist.Tasklist;
import static common.Messages.DELETE_TASK_MESSAGE;
public class DeleteTask {

    public static void execute(String userInput) throws BlankDescriptionException, OutOfRangeException, EmptyListException {
        int number = Parser.parsingDeleteCommand(userInput);
        Task task = Tasklist.getIndex(number);
        Tasklist.removeTask(task);
        System.out.println(DELETE_TASK_MESSAGE);
    }
}
