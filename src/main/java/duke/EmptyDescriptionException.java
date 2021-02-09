package duke;

public class EmptyDescriptionException extends Exception{
    private CommandType commandType;

    public EmptyDescriptionException(CommandType commandType) {
        this.commandType = commandType;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public void showMessage() {
        System.out.println("____________________________________________________________");
        switch (commandType) {
        case DONE:
            System.out.println("OOPS!!! The task ID that you " + this.commandType + " is invalid");
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            System.out.println("OOPS!!! The description of a " + this.commandType + " cannot be empty.");
            break;
        default:
            return;
        }
        System.out.println("____________________________________________________________");
    }

}
