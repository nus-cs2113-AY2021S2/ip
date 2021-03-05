package duke.parser;


import duke.commands.*;
import duke.exception.DukeException;

/*
Class Parser for making sense of user input
*/
public class Parser {

    /*
    Static Function that create a class object based on user command
    Returns the requested Command Object
    */
    public static Command parse(String fullCommand) throws DukeException {
        String activity = null;
        String action = null;
        try {
            String[] individualWords = fullCommand.split(" ", 2);
            activity = individualWords[0].toLowerCase().strip();
            action = individualWords[1].toLowerCase().strip();
            return commandCreation(activity, action);
        } catch (ArrayIndexOutOfBoundsException e) {
            return commandCreation(activity, null);
        }
    }

    /*
    No break statement for each cases because of the return statement
    Else will produce syntax errors
     */
    private static Command commandCreation(String activity, String action) throws DukeException{
        switch (activity) {
        case "list":
            if(action != null){
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }else{
                return new ListCommand(activity, null);
            }
        case "todo":
        case "deadline":
        case "event":
            if(action == null || action.equals("")){
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }else{
                return new AddCommand(activity, action);
            }
        case "done":
            return new DoneCommand(activity, action);
        case "delete":
            return new DeleteCommand(activity, action);
        case "find":
            return new FindCommand(activity, action);
        case "bye":
            if(action != null){
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }else{
                return new ExitCommand(activity, null);
            }
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
