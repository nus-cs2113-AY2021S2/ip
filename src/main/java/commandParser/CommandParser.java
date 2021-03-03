package commandParser;

import command.*;
import exception.*;

public class CommandParser {
    public CommandParser(){}
    private final String LINE_SEPERATOR = "    ________________________________________________\n";

    public Command parseCommand(String input) {
        if (input.equals("list")) {
            return new ListCommand();
        }
        String[] inputParts = input.trim().split("\\s+", 2);
        switch (inputParts[0]) {
        case "todo":
            return prepareAddTodo(inputParts);
        case "deadline":
            return prepareAddDeadline(inputParts);
        case "event":
            return prepareAddEvent(inputParts);
        case "done":
            return prepareDone(inputParts);
        case "delete":
            return prepareDelete(inputParts);
        case "find":
            return prepareFind(inputParts);
        default:
            return new InvalidCommand(new InvalidCommandException());
        }
    }

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

    private Command prepareAddEvent(String[] inputParts) throws EventFormatException{
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
