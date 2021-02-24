package commands;
import myexceptions.BlankDescriptionException;
import parser.Parser;
import tasklist.Task;
import tasklist.Tasklist;
import static common.Messages.BLANK_EXCEPTION_MESSAGE;
public class AddTodo {
    public static void execute(String userInput) throws BlankDescriptionException {
        try{
            Task todo = Parser.parsingTodo(userInput);
            Tasklist.addTask(todo);
        } catch (BlankDescriptionException e) {
            System.out.println(BLANK_EXCEPTION_MESSAGE);
        }

    }
}

