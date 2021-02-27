package parser;

import command.AddTask;
import command.DeleteTask;
import command.DoneTask;
import command.FindTask;
import constant.Constants;
import storage.DukeReader;
import task.TaskList;
import ui.Printer;

import java.util.Scanner;

/**
 * Represents a class that parses the input by the user so that a command can be performed.
 */
public class CommandParser implements Parser{
    /**
     * Runs the command parser indefinitely, calls on other classes to perform the
     * command, ends when the command is bye.
     * @param scanner The Scanner object to be used for all user input.
     */
    public static void parse(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(Constants.STRING_COMMAND_BYE)) {
                return;
            } else if (input.equals(Constants.STRING_COMMAND_LIST)) {
                Printer.printTaskList(TaskList.getTasks());
            } else if (input.contains(Constants.STRING_COMMAND_DONE)) {
                DoneTask.markTaskDone(input);
            } else if (input.contains(Constants.STRING_COMMAND_DELETE)) {
                DeleteTask.deleteTask(input);
            } else if (input.contains(Constants.STRING_COMMAND_FIND)) {
                FindTask.findTask(input);
            } else {
                AddTask.addNewTask(input);
            }
        }
    }
}
