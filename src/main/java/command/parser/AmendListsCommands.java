package command.parser;

import file.storage.Storage;
import task.list.Task;
import ui.UI;

import java.util.ArrayList;

/**
 * Represents a parser that deals with commands that accesses and changes data within the list
 */
public class AmendListsCommands extends Command {

    public static final String DONE_COMMAND = "done";
    public static final String FIND_COMMAND = "find";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";
    public static final String SEARCH_COMMAND = "search";
    public static final String COMMAND_DELIMITER = " ";

    /**
     * parses commands that need to manipulate data in the list
     *
     * @param line  is the inputted line
     * @param tasks is the list of tasks
     */
    public static void amendList(String line, ArrayList<Task> tasks) {
        String[] sentence = line.split(COMMAND_DELIMITER);
        if (sentence.length < NUMBER_OF_COMMAND_ARGUMENTS) {
            UI.printCommandDoesNotExist();
            UI.printDottedLines();
            return;
        }
        switch (sentence[COMMAND_KEYWORD_POSITION]) {
        case DONE_COMMAND:
            CommandsHandler.handleMarkAsDone(tasks, sentence);
            break;
        case FIND_COMMAND:
            CommandsHandler.handleFind(line, tasks);
            break;
        case TODO_COMMAND: {
            AddCommand.addTaskInTodoList(line, tasks);
            break;
        }
        case DEADLINE_COMMAND: {
            AddCommand.addTaskInDeadlineList(line, tasks);
            break;
        }
        case EVENT_COMMAND: {
            AddCommand.addTaskInEventList(line, tasks);
            break;
        }
        case DELETE_COMMAND: {
            CommandsHandler.handleDelete(tasks, sentence);
            break;
        }
        case SEARCH_COMMAND: {
            CommandsHandler.handleSearch(tasks, sentence);
            break;
        }
        default:
            UI.printCommandDoesNotExist();
        }
        Storage.saveToFile(tasks);
        UI.printDottedLines();
    }
}