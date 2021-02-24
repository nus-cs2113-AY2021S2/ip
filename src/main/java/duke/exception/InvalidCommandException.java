package duke.exception;

import duke.util.Ui;

public class InvalidCommandException extends Exception {
    private String command;
    private static String invalidCommandResponse = " is not a valid command!\n";
    public InvalidCommandException(String command) {
        this.command = command;
    }
    
    public String getMessage() {
        return "'" + this.command + "'" + invalidCommandResponse + Ui.COMMANDS;
    }
}
