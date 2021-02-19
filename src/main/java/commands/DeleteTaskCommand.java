package commands;

import errors.DescriptionSplitException;

import java.util.NavigableSet;
import java.util.TreeSet;

public class DeleteTaskCommand extends Command {

    private final String input;

    public DeleteTaskCommand(String input) {
        this.input = input.substring(constants.COMMAND_DELETE.length());
    }


    /**
     * Deletes multiple tasks from list.
     */
    @Override
    public void execute() {
        try {
            String[] indices = input.split(" ");
            if (indices.length < constants.MIN_SPLIT_SUCCESS_COUNT) {
                throw new DescriptionSplitException();
            }
            TreeSet<Integer> validIndices = new TreeSet<>();
            for (String rawIndex : indices) {
                int index = processIndex(rawIndex);
                if (index != constants.INVALID_INDEX) {
                    validIndices.add(index);
                }
            }
            if (validIndices.size() < 1) {
                throw new IndexOutOfBoundsException();
            }
            NavigableSet<Integer> sortedIndices = validIndices.descendingSet();
            System.out.println(constants.MESSAGE_DELETED);
            for (int index : sortedIndices) {
                taskManager.getTask(index).printStatus();
                System.out.println();
                taskManager.deleteTask(index);
                taskManager.setTaskCount(taskManager.getTaskCount()-1);
            }
            System.out.println(constants.LINE);
            updateFile();
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }
}
