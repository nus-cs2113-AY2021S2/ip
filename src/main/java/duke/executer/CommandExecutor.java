package duke.executer;

import duke.Command;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidCommandException;
import duke.list.TaskList;
import duke.parser.Parser;

/**
 * Executes User commands.
 */
public class CommandExecutor {

    /**
     * Given the user input, processes the input into various commands.
     * Then, validates and executes those commands
     *
     * @param taskList ArrayList of tasks.
     * @param input    User input.
     * @return the command based on input if it passes validation. Else, returns error.
     */
    public Command executeCommand(TaskList taskList, String input) {
        Command command;
        try {
            command = Parser.getCommand(input);
            switch (command) {
            case TODO:
                command = taskList.addTask(input.replaceFirst("todo ", ""), Command.TODO);
                break;
            case DEADLINE:
                command = taskList.addTask(input.replaceFirst("deadline ", ""), Command.DEADLINE);
                break;
            case EVENT:
                command = taskList.addTask(input.replaceFirst("event ", ""), Command.EVENT);
                break;
            case DONE:
                try {
                    Parser.validateDescription(input, Command.DONE);
                } catch (EmptyDescriptionException e) {
                    command = Command.DONE_ERROR;
                    break;
                }
                try {
                    int taskNum = Parser.getTaskNum(input, Command.DONE);
                    taskList.finishTask(taskNum - 1);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    command = Command.DONE_ERROR;
                }
                break;
            case DELETE:
                try {
                    Parser.validateDescription(input, Command.DELETE);
                } catch (EmptyDescriptionException e) {
                    command = Command.DELETE_ERROR;
                }
                break;
            case FIND:
                try {
                    Parser.validateDescription(input, Command.FIND);
                } catch (EmptyDescriptionException e) {
                    command = Command.FIND_ERROR;
                }
                break;
            default:
                break;
            }
        } catch (InvalidCommandException e) {
            command = Command.ERROR;
        }
        return command;
    }
}
