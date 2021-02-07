public class EventCommand extends Command {

    public EventCommand(String commandArgs) {
        super(CommandType.EVENT, commandArgs);
    }

    @Override
    public void execute(Task[] tasks) throws MissingDescriptionException {
        if (commandArgs.length() == 0) {
            throw new MissingDescriptionException(commandType);
        }
        String[] eventArgs = commandArgs.split("\\s+/at\\s+", 2);
        Task task = new Event(eventArgs[0], eventArgs[1]);
        tasks[Task.getNumberOfTasks() - 1] = task;
        Menu.printAddedTask(task);
    }
}
