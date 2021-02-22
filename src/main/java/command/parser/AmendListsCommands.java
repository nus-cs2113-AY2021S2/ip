package command.parser;

import file.storage.Storage;
import task.list.TaskList;
import ui.UI;

import java.util.ArrayList;

public class AmendListsCommands extends Command {

    public static void amendList(String line, ArrayList<TaskList> tasks) {
        String[] sentence = line.split(" ");
        if (sentence.length < NUMBER_OF_COMMAND_ARGUMENTS) {
            UI.printCommandDoesNotExist();
            UI.printDottedLines();
            return;
        }
        switch (sentence[COMMAND_KEYWORD_POSITION]) {
        case "done":
            CommandsHandler.handleMarkAsDone(tasks, sentence);
            break;
        case "find":
            CommandsHandler.handleFind(line, tasks);
            break;
        case "todo": {
            AddCommand.addTaskInTodoList(line, tasks);
            break;
        }
        case "deadline": {
            AddCommand.addTaskInDeadlineList(line, tasks);
            break;
        }
        case "event": {
            AddCommand.addTaskInEventList(line, tasks);
            break;
        }
        case "delete": {
            CommandsHandler.handleDelete(tasks, sentence);
            break;
        }
        default:
            UI.printCommandDoesNotExist();
        }
        Storage.saveToFile(tasks);
        UI.printDottedLines();
    }
}