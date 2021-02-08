public class InvalidCommand extends Exception {
    private String command;
    private static String invalidCommandResponse = " is not a valid command!\n";
    private static final String COMMANDS = "Commands:\n    todo taskName\n    deadline deadlineName /by time\n" 
            + "    event eventName /at time\n    list\n    done taskNumber\n    help\n    bye\n";

    public InvalidCommand(String command) {
        this.command = command;
    }
    
    public String getMessage() {
        return "'" + this.command + "'" + invalidCommandResponse + COMMANDS;
    }
}
