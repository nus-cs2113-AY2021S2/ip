package duke;

public class DukeException extends Exception{
    private CommandType commandType;

    public DukeException(CommandType commandType) {
        this.commandType = commandType;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    /**
     * Show duke exception message according what command type is the exception occurs.
     * @param ui the exception message interact with users
     */
    public void showMessage(Ui ui) {
        switch (commandType) {
        case DONE:
        case DELETE:
            ui.showInvalidIDMessage(this.commandType);
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
        case FIND:
            ui.showEmptyFieldMessage(this.commandType);
            break;
        default:
            return;
        }
    }

}
