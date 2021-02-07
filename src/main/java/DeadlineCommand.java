public class DeadlineCommand extends Command {

    public DeadlineCommand(String commandArgs) {
        super(CommandType.DEADLINE, commandArgs);
    }

    @Override
    public void execute(Task[] tasks) throws MissingDescriptionException {
        if (commandArgs.length() == 0) {
            throw new MissingDescriptionException(commandType);
        }
        String[] deadlineArgs = commandArgs.split("\\s+/by\\s+", 2);
        Task task = new Deadline(deadlineArgs[0], deadlineArgs[1]);
        tasks[Task.getNumberOfTasks() - 1] = task;
        Menu.printAddedTask(task);
    }
}
