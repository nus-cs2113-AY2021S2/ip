package commands;

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
            TreeSet<Integer> validIndices = processMultipleIndices(input);
            if (validIndices.size() < 1) {
                //No valid index identified
                throw new IndexOutOfBoundsException();
            }
            NavigableSet<Integer> sortedIndices = validIndices.descendingSet();
            System.out.println(constants.MESSAGE_DELETED);
            for (int index : sortedIndices) {
                taskManager.getTask(index).printStatus();
                System.out.println();
                taskManager.deleteTask(index);
            }
            System.out.println(constants.LINE);
            updateFile();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(constants.MESSAGE_TASK_NOT_FOUND);
        }
    }

}
