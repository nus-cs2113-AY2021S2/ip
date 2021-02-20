package commands;

import errors.DescriptionSplitException;
import errors.ListFullException;
import errors.MissingKeywordException;
import tasks.EventTask;

public class AddEventCommand extends Command {

    private final String input;

    public AddEventCommand(String input) {
        this.input = input.substring(constants.COMMAND_EVENT.length());
    }


    /**
     * Creates new EventTask.
     * Prints error message if invalid.
     */
    @Override
    public void execute() {
        try {
            if (!input.contains(constants.KEYWORD_AT)) {
                //Event info not specified in input
                throw new MissingKeywordException();
            }
            String[] inputSplit = input.split(constants.KEYWORD_AT);
            if (taskManager.getTaskCount() >= constants.MAX_SIZE) {
                //Array full
                throw new ListFullException();
            } else if (inputSplit.length < constants.MIN_SPLIT_SUCCESS_COUNT) {
                //Invalid input
                throw new DescriptionSplitException();
            }
            taskManager.addTask(new EventTask(inputSplit[0], inputSplit[1]));
            printAddedContent(inputSplit[0]);
            updateFile();
        } catch (ListFullException e) {
            System.out.println(constants.MESSAGE_LIST_FULL);
        } catch (MissingKeywordException e) {
            System.out.println(constants.MESSAGE_MISSING_AT_KEYWORD);
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }
}
