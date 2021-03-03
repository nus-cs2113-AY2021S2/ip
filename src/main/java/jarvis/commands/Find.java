package jarvis.commands;

import jarvis.Duke;
import jarvis.exception.EmptyKeywordException;
import jarvis.exception.NoMatchException;
import jarvis.parser.Parser;
import jarvis.task.Task;
import jarvis.task.TaskList;

public class Find extends Command {

    /**
     * Searches for the tasks in the list that contain the keyword in the description
     *
     * @param command command entered by the user
     * @throws EmptyKeywordException if no keyword is stated by the user
     * @throws NoMatchException if no task that matches the keyword was found
     */
    public static void execute(String command) throws EmptyKeywordException, NoMatchException {
        String keyword = Parser.parseFindCommand(command);
        int matchingTaskCount = 0;
        for (Task task : TaskList.getTaskList()) {
            if (task.getTaskDescription().contains(keyword)) {
                System.out.println(String.format("\t\t%d. ", matchingTaskCount + 1) + task.toString());
                ++matchingTaskCount;
            }
        }
        if (matchingTaskCount == 0) {
            throw new NoMatchException();
        }
        Duke.jarvis.printDivider();
    }
}
