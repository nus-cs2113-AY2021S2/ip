package duke.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import duke.common.Commands;
import duke.util.actions.AddTask;
import duke.util.actions.CompleteTask;
import duke.util.actions.DeleteTask;
import duke.util.actions.FetchTasks;
import duke.util.actions.FindTask;
import duke.ui.Ui;
import duke.common.Messages;
import duke.data.exception.DukeException;
import duke.util.TaskList;

public class Parser {

    /**
     * Parses user input into command for execution.
     * @param scanner Retrieves user commands.
     */
    public static void handleCommand(Scanner scanner) {
        List<String> messages = new ArrayList<>();
        String sentence = scanner.nextLine();
        String[] words = sentence.split(" ");
        String command = words[0];
        String task = String.join(
                " ", Arrays.copyOfRange(words, 1, words.length)
        );
        List<String> response;

        try {
            switch (command) {
            case Commands.BYE:
                messages.add(Messages.BYE_MESSAGE);
                Ui.reply(messages);
                TaskList.saveTasksToTextFile();
                scanner.close();
                return;
            case Commands.LIST:
                response = FetchTasks.fetchTasks();
                break;
            case Commands.DONE:
                response = CompleteTask.completeTask(task);
                TaskList.saveTasksToTextFile();
                break;
            case Commands.DELETE:
                response = DeleteTask.deleteTask(task);
                TaskList.saveTasksToTextFile();
                break;
            case Commands.FIND:
                response = FindTask.findTask(task);
                break;
            default:
                response = AddTask.addTask(command, task);
                TaskList.saveTasksToTextFile();
            }
            messages.addAll(response);
            Ui.reply(messages);
            handleCommand(scanner);
        } catch (DukeException dukeException) {
            Ui.notifyError(dukeException.getMessage());
            handleCommand(scanner);
        }
    }
}
