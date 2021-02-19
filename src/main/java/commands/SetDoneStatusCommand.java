package commands;

public class SetDoneStatusCommand extends Command {

    private final String input;
    private final Boolean isDone;

    public SetDoneStatusCommand(String input, Boolean isDone) {
        this.input = input;
        this.isDone = isDone;
    }


    /**
     * Marks specified task as done.
     * If position is invalid, print error message.
     */
    @Override
    public void execute() {
        try {
            int position = Integer.parseInt(input.split(" ")[1]) - 1;
            if (position >= taskManager.getTaskCount() || position < 0) {
                //Out of bounds
                throw new IndexOutOfBoundsException();
            }
            taskManager.getTask(position).setDone(isDone);
            if (isDone) {
                System.out.println(constants.MESSAGE_MARK_DONE);
            } else {
                System.out.println(constants.MESSAGE_MARK_UNDONE);
            }
            taskManager.getTask(position).printStatus();
            System.out.println("\n" + constants.LINE);
            updateFile();
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }

}
