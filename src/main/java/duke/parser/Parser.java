package duke.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import duke.taskActions.AddTask;
import duke.taskActions.CompleteTask;
import duke.taskActions.DeleteTask;
import duke.taskActions.FetchTasks;
import duke.ui.Ui;
import duke.common.Strings;
import duke.data.exception.DukeException;
import duke.taskActions.TaskList;

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
                case Strings.BYE:
                    messages.add(Strings.BYE_MESSAGE);
                    Ui.reply(messages);
                    TaskList.saveTasksToTextFile();
                    scanner.close();
                    return;
                case Strings.LIST:
                    response = FetchTasks.fetchTasks();
                    break;
                case Strings.DONE:
                    response = CompleteTask.completeTask(task);
                    TaskList.saveTasksToTextFile();
                    break;
                case Strings.DELETE:
                    response = DeleteTask.deleteTask(task);
                    TaskList.saveTasksToTextFile();
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
