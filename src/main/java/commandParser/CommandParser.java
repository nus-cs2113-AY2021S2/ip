package commandParser;

import command.*;
import exception.*;

import java.util.Locale;

/**
 * Represent the parser to handle the use input command
 * the parser will check the validity of the parameters for different commands
 */
public class CommandParser {
    public CommandParser(){}
    private final String LINE_SEPERATOR = "    ________________________________________________\n";

    /**
     * classify command based on the command word
     * @param input user input
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
            return new InvalidCommand(new InvalidCommandException());
        }
    }


    /**
     * Check validity of parameters for done command. The parameter should be in Integer format.
     * The index should be within the bound.
     * @param inputParts
     * @return a DoneCommand object if the parameter is valid, an InvalidCommand object if the second parameter is invalid
     */
    private Command prepareFind(String[] inputParts){
        try{
            if(inputParts.length < 2){
                throw new FindFormatException();
            }
            return new FindCommand(inputParts[1]);
        } catch (FindFormatException e){
            return new InvalidCommand(e);
        }
    }


    private Command prepareDone(String[] inputParts){
        try{
            if(inputParts.length < 2){
                throw new DoneFormatException();
            }
            String index = inputParts[1];
            int indexInt = Integer.parseInt(index) - 1;
            return new DoneCommand(indexInt);
        } catch (NumberFormatException e){
            return new InvalidCommand(new DoneFormatException());
        } catch (DoneFormatException e){
            return new InvalidCommand(e);
        }
    }

    /**
     * Check validity of parameters for delete command. The parameter should be in Integer format.
     * The index should be within the bound.
     * @param inputParts
     * @return a DeleteCommand object if the parameter is valid, an InvalidCommand object if the second parameter is invalid,
     */
    private Command prepareDelete(String[] inputParts){
        try{
            if(inputParts.length < 2){
                throw new DeleteFormatException();
            }
            String index = inputParts[1];
            int indexInt = Integer.parseInt(index) - 1;
            return new DeleteCommand(indexInt);
        } catch (DeleteFormatException e){
            return new InvalidCommand(e);
        } catch (NumberFormatException e){
            return new InvalidCommand(new DeleteFormatException());
        }
    }

    /**
     * Check validity of parameters for adding a new to do task command.
     * The parameter should be the name of the to do.
     * @param inputParts
     * @return a AddTodoCommand object if the parameter is valid, an InvalidCommand object if the parameter is invalid,
     */
    private Command prepareAddTodo(String[] inputParts) {
        try{
            if(inputParts.length < 2){
                throw new TodoFormatException();
            }
            String taskInfo = inputParts[1];
            if (taskInfo.equals("")) {
                throw new TodoFormatException();
            }
            return new AddTodoCommand(taskInfo);
        } catch (TodoFormatException e){
            return new InvalidCommand(e);
        }
    }

    /**
     * Check validity of parameters for adding a new deadline task command.
     * The parameter should be the task name and time separated by "/by".
     * @param inputParts
     * @return a AddDeadlineCommand object if the parameter is valid, an InvalidCommand object if the parameter is invalid,
     */
    private Command prepareAddDeadline(String[] inputParts){
        try{
            if(inputParts.length < 2){
                throw new DeadlineFormatException();
            }
            String taskInfo = inputParts[1];
            if (taskInfo.equals("")) {
                throw new DeadlineFormatException();
            }
            String[] deadlineNameDoby = taskInfo.split("/by");
            boolean hasFewerPatrs = deadlineNameDoby.length < 2;
            if (hasFewerPatrs) {
                throw new DeadlineFormatException();
            }
            boolean hasEmptyName = deadlineNameDoby[0].equals("") || deadlineNameDoby[0].equals("\\s+");
            boolean hasEmptyTime = deadlineNameDoby[1].equals("") || deadlineNameDoby[1].equals("\\s+");
            if(hasEmptyName || hasEmptyTime){
                throw new DeadlineFormatException();
            }
            String deadlineName = deadlineNameDoby[0].trim();
            String deadlineDoby = deadlineNameDoby[1].trim();
            return new AddDeadlineCommand(deadlineName, deadlineDoby);
        } catch (DeadlineFormatException e){
            return new InvalidCommand(e);
        }
    }

    /**
     * Check validity of parameters for adding a new event task command.
     * The parameter should be the task name and time separated by "/at".
     * @param inputParts
     * @return a AddEventCommand object if the parameter is valid, an InvalidCommand object if the parameter is invalid,
     */
    private Command prepareAddEvent(String[] inputParts){
        try{
            if(inputParts.length < 2){
                throw new EventFormatException();
            }
            String taskInfo = inputParts[1];
            if (taskInfo.equals("")) {
                throw new EventFormatException();
            }
            String[] eventNameTime = taskInfo.split("/at");
            boolean hasFewerPatrs = eventNameTime.length < 2;
            if (hasFewerPatrs) {
                throw new EventFormatException();
            }
            boolean hasEmptyName = eventNameTime[0].equals("") || eventNameTime[0].equals("\\s+");
            boolean hasEmptyTime = eventNameTime[1].equals("") || eventNameTime[1].equals("\\s+");
            if(hasEmptyName || hasEmptyTime){
                throw new EventFormatException();
            }
            String eventName = eventNameTime[0].trim();
            String eventTime = eventNameTime[1].trim();
            return new AddEventCommand(eventName, eventTime);
        } catch (EventFormatException e){
             return new InvalidCommand(e);
        }
    }
}
