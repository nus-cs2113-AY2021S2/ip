public abstract class Command {
    protected CommandType commandType;
    protected String commandArgs;

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public Command(CommandType commandType, String commandArgs) {
        this.commandType = commandType;
        this.commandArgs = commandArgs;
    }

    public abstract void execute(Task[] tasks) throws DukeException;
}
