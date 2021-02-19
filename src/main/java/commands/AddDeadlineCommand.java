package commands;

import errors.DescriptionSplitException;
import errors.ListFullException;
import errors.MissingKeywordException;
import tasks.DeadlineTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {

    private final String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
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
            String[] inputSplit = input.substring(constants.COMMAND_DEADLINE.length()).split(constants.KEYWORD_BY);
            if (taskManager.getTaskCount() >= constants.MAX_SIZE) {
                //Array full
                throw new ListFullException();
            } else if (inputSplit.length < constants.MIN_SPLIT_SUCCESS_COUNT) {
                //Invalid input
                throw new DescriptionSplitException();
            }
            LocalDate dueDate = extractDate(inputSplit[1]);
            taskManager.addTask(new DeadlineTask(inputSplit[0], dueDate));
            taskManager.setTaskCount(taskManager.getTaskCount()+1);
            printAddedContent(inputSplit[0]);
            updateFile();
        } catch (ListFullException e) {
            System.out.println(constants.MESSAGE_LIST_FULL);
        } catch (MissingKeywordException e) {
            System.out.println(constants.MESSAGE_MISSING_BY_KEYWORD);
        } catch (DateTimeParseException e) {
            System.out.println(constants.MESSAGE_INVALID_DATE);
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }


    private LocalDate extractDate(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(constants.DATE_IO_FORMAT);
        return LocalDate.parse(input, formatter);
    }
}
