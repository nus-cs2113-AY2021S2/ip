package commandParser;

import command.*;

import java.util.Locale;

/**
 * Represent the parser to handle the use input command
 */
public class CommandParser {
    public CommandParser(){}

    /**
     * Classifies command based on the command word.
     *
     * @param input string of user input
     * @return command object corresponding to user input
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
     * Checks validity of a find command.
     *
     * @param inputParts
     * @return a Command object
     */
    private Command prepareFind(String[] inputParts){
        if(inputParts.length < 2){
            return new InvalidCommand(FindCommand.ERROR_MESSAGE);
        }
        return new FindCommand(inputParts[1]);
    }

    /**
     * Checks validity of a done command.
     *
     * @param inputParts
     * @return a Command object
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
     * Checks validity of a delete command.
     *
     * @param inputParts
     * @return a Command object
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
     * Checks validity of adding a new ToDo task command.
     *
     * @param inputParts
     * @return a Command object
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
     * Checks validity of adding a new deadline task command.
     *
     * @param inputParts
     * @return a Command object
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
     * Checks validity of adding a new event task command.
     *
     * @param inputParts
     * @return a Command object
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
