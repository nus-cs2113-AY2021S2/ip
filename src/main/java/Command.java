public class Command {
    private CommandEnum commandType;
    private String description;
    private String when;
    private Integer taskNum;

    public Command(String userInput) {
        try {
            setCommandFromInput(userInput);
        } catch(EmptyByOrAtWhenException ignored) {

        }
    }

    public CommandEnum getCommandType() {
        return commandType;
    }

    public String getDescription() throws EmptyTaskDescriptionException {
        if (description.equals("")) {
            throw new EmptyTaskDescriptionException();
        }
        return description;
    }

    public String getWhen() throws EmptyByOrAtWhenException {
        if (when.equals("")) {
            throw new EmptyByOrAtWhenException();
        }
        return when;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    private void setCommandFromInput(String input) throws EmptyByOrAtWhenException {
        // Remove whitespaces
        input = input.trim();
        commandType = getCommandTypeFromInput(input);

        // Split user input by the first "/" to separate description and second argument (at or by, if applicable).
        String[] arguments = input.split("/", 2);

        // Remove the command word (eg. deadline, event, etc.) from the description.
        description = arguments[0].substring(arguments[0].indexOf(' ')+1);

        if (commandType == CommandEnum.DEADLINE || commandType == CommandEnum.EVENT) {
            // Extract and remove the '/by' or '/at' from the 'when' argument
            when = arguments[1].substring(arguments[1].indexOf(' ')+1);
            if (when.equals("by") || when.equals("at")) {
                when = null;
            }
        }
        if (commandType == CommandEnum.DONE) {
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
        }
        return CommandEnum.INVALID;
    }
}
