package commandParser;

import command.*;

import java.util.Locale;

/**
 * Represent the parser to handle the use input command
 */
public class CommandParser {
    public CommandParser(){}

    /**
     * Classify command based on the command word
     * @param input string of user input
     * @return return a command object corresponding to user input, return an InvalidCommand object if the command is not recognizable
     */
    public Command parseCommand(String input) {
        if (input.trim().equalsIgnoreCase(ListCommand.COMMAND_WORD)) {
            return new ListCommand();
        } else if(input.trim().equalsIgnoreCase(HelpCommand.COMMAND_WORD)){
            return new HelpCommand();
        } else if(input.trim().equalsIgnoreCase(ClearCommand.COMMAND_WORD)){
            return new ClearCommand();
        }
        String[] inputParts = input.trim().split("\\s+", 2);
        switch (inputParts[0].toLowerCase(Locale.ROOT)) {
        case AddTodoCommand.COMMAND_WORD:
            return prepareAddTodo(inputParts);
        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadline(inputParts);
        case AddEventCommand.COMMAND_WORD:
            return prepareAddEvent(inputParts);
        case DoneCommand.COMMAND_WORD:
            return prepareDone(inputParts);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(inputParts);
        case FindCommand.COMMAND_WORD:
            return prepareFind(inputParts);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Check validity of a find command. The keyword to search must be specified after command word.
     * @param inputParts
     * @return a FindCommand object.  If keyword is not given, return an InvalidCommand object.
     * given.
     */
    private Command prepareFind(String[] inputParts){
        if(inputParts.length < 2){
            return new InvalidCommand(FindCommand.ERROR_MESSAGE);
        }
        return new FindCommand(inputParts[1]);
    }

    /**
     * Check validity of a done command. There index of the task to mark as done must be specified after command
     * word.
     * @param inputParts
     * @return a DoneCommand object. If the index is not given or the String of index can't be converted to integer
     * or the index is out of bound, returns an InvalidCommand object
     */
    private Command prepareDone(String[] inputParts){
        try{
            if(inputParts.length < 2){
                return new InvalidCommand(DoneCommand.ERROR_MESSAGE);
            }
            String index = inputParts[1];
            int indexInt = Integer.parseInt(index) - 1;
            return new DoneCommand(indexInt);
        } catch (NumberFormatException e){
            return new InvalidCommand(DoneCommand.ERROR_MESSAGE);
        }

    }

    /**
     * Check validity of a delete command. There index of the task to delete must be specified after command
     * word.
     * @param inputParts
     * @return a DeleteCommand object. If the index is not given or the String of index can't be converted to
     * integer or the index is out of bound, returns an InvalidCommand object
     */
    private Command prepareDelete(String[] inputParts){
        try{
            if(inputParts.length < 2){
                return new InvalidCommand(DeleteCommand.ERROR_MESSAGE);
            }
            String index = inputParts[1];
            int indexInt = Integer.parseInt(index) - 1;
            return new DeleteCommand(indexInt);
        } catch (NumberFormatException e){
            return new InvalidCommand(DeleteCommand.ERROR_MESSAGE);
        }
    }

    /**
     * Check validity of adding a new ToDo task command. The name of ToDo task must be specified.
     * @param inputParts
     * @return a AddTodoCommand object. If the ToDo name is not given, returns an InvalidCommand object .
     */
    private Command prepareAddTodo(String[] inputParts) {
        if(inputParts.length < 2){
            return new InvalidCommand(AddTodoCommand.ERROR_MESSAGE);
        }
        String todoName = inputParts[1];
        if (todoName.equals("")) {
            return new InvalidCommand(AddTodoCommand.ERROR_MESSAGE);
        }
        return new AddTodoCommand(todoName);
    }

    /**
     * Check validity of adding a new deadline task command. The name and deadline of the task must be given,
     * task name and deadline are separated by "/by".
     * @param inputParts
     * @return a AddDeadlineCommand object. If the input format is invalid or any part is missing, returns an
     * Invalid command object.
     */
    private Command prepareAddDeadline(String[] inputParts){
        if(inputParts.length < 2){
            return new InvalidCommand(AddDeadlineCommand.ERROR_MESSAGE);
        }
        String taskInfo = inputParts[1];
        if (taskInfo.equals("")) {
            return new InvalidCommand(AddDeadlineCommand.ERROR_MESSAGE);
        }
        String[] deadlineNameDoby = taskInfo.split("/by");
        boolean hasFewerPatrs = deadlineNameDoby.length < 2;
        if (hasFewerPatrs) {
            return new InvalidCommand(AddDeadlineCommand.ERROR_MESSAGE);
        }
        boolean hasEmptyName = deadlineNameDoby[0].equals("") || deadlineNameDoby[0].equals("\\s+");
        boolean hasEmptyTime = deadlineNameDoby[1].equals("") || deadlineNameDoby[1].equals("\\s+");
        if(hasEmptyName || hasEmptyTime){
            return new InvalidCommand(AddDeadlineCommand.ERROR_MESSAGE);
        }
        String deadlineName = deadlineNameDoby[0].trim();
        String deadlineDoby = deadlineNameDoby[1].trim();
        return new AddDeadlineCommand(deadlineName, deadlineDoby);
    }

    /**
     * Check validity of adding a new event task command. The name and time of the task must be given, task name
     * and deadline are separated by "/at".
     * @param inputParts
     * @return a AddEventCommand object. If the input format is invalid or any part is missing, returns an
     * InvalidCommand object.
     */
    private Command prepareAddEvent(String[] inputParts){
        if(inputParts.length < 2){
            return new InvalidCommand(AddEventCommand.ERROR_MESSAGE);
        }
        String taskInfo = inputParts[1];
        if (taskInfo.equals("")) {
            return new InvalidCommand(AddEventCommand.ERROR_MESSAGE);
        }
        String[] eventNameTime = taskInfo.split("/at");
        boolean hasFewerPatrs = eventNameTime.length < 2;
        if (hasFewerPatrs) {
            return new InvalidCommand(AddEventCommand.ERROR_MESSAGE);
        }
        boolean hasEmptyName = eventNameTime[0].equals("") || eventNameTime[0].equals("\\s+");
        boolean hasEmptyTime = eventNameTime[1].equals("") || eventNameTime[1].equals("\\s+");
        if(hasEmptyName || hasEmptyTime){
            return new InvalidCommand(AddEventCommand.ERROR_MESSAGE);
        }
        String eventName = eventNameTime[0].trim();
        String eventTime = eventNameTime[1].trim();
        return new AddEventCommand(eventName, eventTime);
    }
}
