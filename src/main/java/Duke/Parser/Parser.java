package Duke.Parser;

import Duke.Command.*;
import Duke.Constant.Message;
import Duke.Constant.Number;
import Duke.Constant.TaskType;
import Duke.Exception.DukeException;
import Duke.TaskList.TaskList;

/**
 * Parser calls respective command function to process different user inputs.
 */
public class Parser {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_DATE = "date";

    /**
     * Calls the correct command to process different user inputs.
     *
     * @param userInput user input received
     * @param tasks tasks in the list
     * @throws DukeException includes all exceptions may happen when parsing the input
     */
    public Command processInput(String userInput, TaskList tasks) throws DukeException {
        String command = userInput.split(" ")[0];

        switch (command) {
        case COMMAND_TODO:
            String description = userInput.substring(Number.TODO_COMMAND_LENGTH);
            description = description.replace(" ", "");

            if (description.isEmpty()) {
                throw new DukeException(Message.MISSING_TODO_CONTENT_ERROR_MESSAGE);
            }

            return new AddCommand(TaskType.TODO_TYPE, userInput.substring(Number.TODO_COMMAND_LENGTH_ADD_ONE));
        case COMMAND_DEADLINE:
            String deadline = userInput.substring((Number.DEADLINE_COMMAND_LENGTH));
            deadline = deadline.replace(" ", "");

            if (deadline.isEmpty()) {
                throw new DukeException(Message.MISSING_DEADLINE_CONTENT_ERROR_MESSAGE);
            }

            return new AddCommand(TaskType.DEADLINE_TYPE, userInput.substring(Number.DEADLINE_COMMAND_LENGTH_ADD_ONE));
        case COMMAND_EVENT:
            String event = userInput.substring((Number.EVENT_COMMAND_LENGTH));
            event = event.replace(" ", "");

            if (event.isEmpty()) {
                throw new DukeException(Message.MISSING_EVENT_CONTENT_ERROR_MESSAGE);
            }

            return new AddCommand(TaskType.EVENT_TYPE, userInput.substring(Number.EVENT_COMMAND_LENGTH_ADD_ONE));
        case COMMAND_DONE:
            String doneIndex = userInput.substring(Number.DONE_COMMAND_LENGTH);
            doneIndex = doneIndex.replace(" ", "");

            if (doneIndex.isEmpty()) {
                throw new DukeException(Message.MISSING_MARK_AS_DONE_INDEX_ERROR_MESSAGE);
            }

            int doneCommandIndexNumber = Integer.parseInt(doneIndex);
            if (doneCommandIndexNumber < 0 || doneCommandIndexNumber > tasks.getSize()) {
                throw new DukeException(Message.WRONG_MARK_AS_DONE_INDEX_ERROR_MESSAGE);
            }

            return new DoneCommand(Integer.parseInt(userInput.substring(Number.DONE_COMMAND_LENGTH_ADD_ONE)) - 1);
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_DELETE:
            String deleteIndex = userInput.substring(Number.DELETE_COMMAND_LENGTH);
            deleteIndex = deleteIndex.replace(" ", "");

            if (deleteIndex.isEmpty()) {
                throw new DukeException(Message.MISSING_DELETE_INDEX_ERROR_MESSAGE);
            }

            int deleteCommandIndexNumber = Integer.parseInt(deleteIndex);
            if (deleteCommandIndexNumber < 0 || deleteCommandIndexNumber > tasks.getSize()) {
                throw new DukeException(Message.WRONG_DELETE_INDEX_ERROR_MESSAGE);
            }

            return new DeleteCommand
                    (Integer.parseInt(userInput.substring(Number.DELETE_COMMAND_LENGTH_ADD_ONE)) - 1);
        case COMMAND_FIND:
            String keyword = userInput.substring(Number.FIND_COMMAND_LENGTH);
            keyword = keyword.replace(" ", "");

            if (keyword.isEmpty()) {
                throw new DukeException(Message.MISSING_KEYWORD_ERROR_MESSAGE);
            }

            return new FindCommand(userInput.substring(Number.FIND_COMMAND_LENGTH_ADD_ONE));
        case COMMAND_DATE:
            String date = userInput.substring(Number.DATE_COMMAND_LENGTH);
            String dateDuplicate = userInput.substring(Number.DATE_COMMAND_LENGTH_ADD_ONE);;
            date = date.replace(" ", "");

            if (date.isEmpty()) {
                throw new DukeException(Message.MISSING_DATE_ERROR_MESSAGE);
            }

            if (dateDuplicate.length() < Number.MINIMUM_DATE_LENGTH ||
                    dateDuplicate.charAt(Number.FIRST_DASH_INDEX) != '-' ||
                    dateDuplicate.charAt(Number.SECOND_DASH_INDEX) != '-') {
                throw new DukeException(Message.WRONG_DATE_FORMAT_ERROR_MESSAGE);
            }
            return new DateCommand(userInput.substring(Number.DATE_COMMAND_LENGTH_ADD_ONE));
        case COMMAND_BYE:
            return new ExitCommand();
        default:
            throw new DukeException(Message.WRONG_COMMAND_ERROR_MESSAGE);
        }
    }
}
