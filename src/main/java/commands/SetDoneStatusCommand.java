package commands;

import java.util.TreeSet;

public class SetDoneStatusCommand extends Command {

    private final String input;
    private final Boolean isDone;

    public SetDoneStatusCommand(String input, Boolean isDone) {
        if (isDone) {
            this.input = input.substring(constants.COMMAND_DONE.length());
        } else {
            this.input = input.substring(constants.COMMAND_UNDO.length());
        }
        this.isDone = isDone;
    }


    /**
     * Marks specified task as done.
     * If position is invalid, print error message.
     */
    @Override
    public void execute() {
        try {
            TreeSet<Integer> validIndices = processMultipleIndices(input);
            if (validIndices.size() < 1) {
                //No valid index identified
                throw new IndexOutOfBoundsException();
            }
            if (isDone) {
                System.out.println(constants.MESSAGE_MARK_DONE);
            } else {
                System.out.println(constants.MESSAGE_MARK_UNDONE);
            }
            for (int index : validIndices) {
                taskManager.getTask(index).setDone(isDone);
                taskManager.getTask(index).printStatus();
                System.out.println();
            }
            System.out.println(constants.LINE);
            updateFile();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(constants.MESSAGE_TASK_NOT_FOUND);
        }
    }

}
