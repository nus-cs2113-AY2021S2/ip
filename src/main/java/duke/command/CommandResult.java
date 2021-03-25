package duke.command;

/**
 * Represent the results of the command executed.
 */
public class CommandResult {

    public String messageToUser;

    public CommandResult(String messageToUser) {
        this.messageToUser = messageToUser;
    }
}
