package CommandClasses;

import ExceptionClasses.EmptyByOrAtWhenException;
import ExceptionClasses.EmptyTaskDescriptionException;
import ExceptionClasses.TooManyArgumentsException;

public class Command {
    private CommandEnum commandType;
    private String description;
    private String when;
    private Integer taskNum;

    /**
     * Constructor for the Command class.
     * @param userInput the input received from the user in the terminal.
     */
    public Command(String userInput) {
        try {
            setCommandFromInput(userInput);
        } catch(EmptyByOrAtWhenException e) {
            System.out.println("Empty 'by' or 'at' when field");
        }
    }

    /**
     * Getter for the command type.
     * @return the command type which of type CommandEnum
     */
    public CommandEnum getCommandType() {
        return commandType;
    }

    /**
     * Getter for the task description.
     * @return the task description.
     * @throws EmptyTaskDescriptionException when the task description is left empty by the user.
     */
    public String getDescription() throws EmptyTaskDescriptionException {
        if (description.equals("")) {
            throw new EmptyTaskDescriptionException();
        }
        return description;
    }

    /**
     * Getter for the 'by when' or 'at when' details for deadline and event tasks.
     * @return the 'by when' or 'at when' details.
     * @throws EmptyByOrAtWhenException when the 'by when' or 'at when' details is left empty by the user.
     */
    public String getWhen() throws EmptyByOrAtWhenException {
        if (when.equals("")) {
            throw new EmptyByOrAtWhenException();
        }
        return when;
    }

    /**
     * Getter for the task number that the user specified for the done or delete commands.
     * @return the task number that the user specified.
     */
    public Integer getTaskNum() {
        return taskNum;
    }

    /**
     * Converts the raw user input from String format to obtain the necessary data according to the specific user
     * command.
     * @param input the raw user input in String format.
     * @throws EmptyByOrAtWhenException when the 'by when' or 'at when' details is left empty by the user.
     */
    private void setCommandFromInput(String input) throws EmptyByOrAtWhenException {
        // Remove whitespaces
        input = input.trim();
        commandType = getCommandTypeFromInput(input);

        // Split user input by the first "/" to separate description and second argument (at or by, if applicable).
        String[] arguments = input.split("/", 2);

        // Remove the command word (eg. deadline, event, etc.) from the description.
        description = arguments[0].substring(arguments[0].indexOf(' ')+1);
        description = description.trim();

        if (commandType == CommandEnum.DEADLINE || commandType == CommandEnum.EVENT) {
            // Extract and remove the '/by' or '/at' from the 'when' argument
            when = arguments[1].substring(arguments[1].indexOf(' ')+1);
            if (when.equals("by") || when.equals("at")) {
                when = null;
            }
        }
        if (commandType == CommandEnum.DONE || commandType == CommandEnum.DELETE) {
            // Split user input by " " to separate 'done' and task number according to the format.
            try {
                String[] args = input.split(" ");
                if (args.length > 2) {
                    throw new TooManyArgumentsException();
                }
                String taskNumArgument = args[1];
                taskNum = Integer.parseInt(taskNumArgument);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error -> Task number cannot be left empty.");
            } catch (NumberFormatException e) {
                System.out.println("Error -> Task number cannot be in words");
            } catch (TooManyArgumentsException e) {
                System.out.println("Error -> Too many arguments for done command");
            }
        }
    }

    /**
     * Parses the raw user input and identifies the type of command that was passed in it.
     * @param input the raw user input in String format.
     * @return the user command in the CommandEnum format.
     */
    private CommandEnum getCommandTypeFromInput(String input) {
        String[] args = input.split(" ");
        if (input.equals("bye")) {
            return CommandEnum.BYE;
        } else if (input.equals("list")) {
            return CommandEnum.LIST;
        } else if (args[0].equals("done")) {
            return CommandEnum.DONE;
        } else if (args[0].equals("deadline") && input.contains("/by")) {
            return CommandEnum.DEADLINE;
        } else if (args[0].equals("event") && input.contains("/at")) {
            return CommandEnum.EVENT;
        } else if (args[0].equals("todo")) {
            return CommandEnum.TODO;
        } else if (args[0].equals("delete")) {
            return CommandEnum.DELETE;
        }
        return CommandEnum.INVALID;
    }
}
