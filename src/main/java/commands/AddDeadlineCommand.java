package commands;

import errors.DescriptionSplitException;
import errors.ListFullException;
import errors.MissingKeywordException;
import tasks.DeadlineTask;

public class AddDeadlineCommand extends Command {

    private final String input;

    public AddDeadlineCommand(String input) {
        this.input = input.substring(constants.COMMAND_DEADLINE.length());
    }


    /**
     * Creates new DeadlineTask.
     * Prints error message if invalid.
     */
    @Override
    public void execute() {
        try {
            if (!input.contains(constants.KEYWORD_BY)) {
                throw new MissingKeywordException();
            }
            String[] inputSplit = input.split(constants.KEYWORD_BY);
            if (taskManager.getTaskCount() >= constants.MAX_SIZE) {
                //Array full
                throw new ListFullException();
            } else if (inputSplit.length < constants.MIN_SPLIT_SUCCESS_COUNT) {
                //Invalid input
                throw new DescriptionSplitException();
            }
            taskManager.addTask(new DeadlineTask(inputSplit[0], inputSplit[1]));
            taskManager.setTaskCount(taskManager.getTaskCount()+1);
            printAddedContent(inputSplit[0]);
            updateFile();
        } catch (ListFullException e) {
            System.out.println(constants.MESSAGE_LIST_FULL);
        } catch (MissingKeywordException e) {
            System.out.println(constants.MESSAGE_MISSING_BY_KEYWORD);
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }
}
