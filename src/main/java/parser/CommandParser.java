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

public class CommandParser implements Parser{

    public static void parse(Scanner scanner) {
        TaskList.tasks = DukeReader.getTaskListFromFile();
        TaskList.taskCount = TaskList.tasks.size();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(Constants.STRING_COMMAND_BYE)) {
                return;
            } else if (input.equals(Constants.STRING_COMMAND_LIST)) {
                Printer.printTaskList(TaskList.tasks);
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
