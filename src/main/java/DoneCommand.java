public class DoneCommand extends Command {

    public DoneCommand(String commandArgs) {
        super(CommandType.DONE, commandArgs);
    }

    @Override
    public void execute(Task[] tasks) throws InvalidTaskNumberException {
        if (!isValidNumber(commandArgs)) {
            throw new InvalidTaskNumberException(commandArgs);
        }
        int taskNumber = Integer.parseInt(commandArgs);

        if (!isValidTaskNumber(taskNumber)) {
            throw new InvalidTaskNumberException(commandArgs);
        }
        Task task = tasks[taskNumber - 1];

        if (task.isDone) {
            Menu.printText("Task already marked as done!");
            return;
        }
        task.setDone(true);
        Menu.printText("Nice! I've marked this task as done:"
                + System.lineSeparator()
                + "\t"
                + task);
    }

    private static boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber >= 1 && taskNumber <= Task.getNumberOfTasks());
    }

    private static boolean isValidNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
