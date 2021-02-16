package duke;

import duke.command.Command;
import duke.dao.TaskDaoImpl;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.ui.Menu;

import java.nio.file.Paths;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = Task.loadAllTasks();
    private static boolean isRunning = true;

    public static void main(String[] args) {
        Menu.printGreeting();
        while (isRunning) {
            String userInput = Menu.readUserInput();
            Menu.printLine();
            try {
                Command command = Parser.parseUserInput(userInput);
                command.execute(tasks);
            } catch (DukeException e) {
                Menu.printError(e);
            }
            Menu.printLine();
        }
    }

    public static void setIsRunning(boolean isRunning) {
        Duke.isRunning = isRunning;
    }

    public static int getTaskSize() {
        return tasks.size();
    }
}
