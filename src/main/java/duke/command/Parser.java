package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final String lineSpacing = "\t----------------------------------";

    public Parser() { }

    public static Command parse(String line) throws DukeExceptions{
        if (line.equals("")){
            throw new DukeExceptions();
        }
        List<String> userCommands = Arrays.asList(line.split(" "));
        if (userCommands.size() < 1) {
            throw new DukeExceptions();
        }

        Command newCommand;
        if (userCommands.get(0).equals("list")) {
            newCommand = new DisplayCommand();
        } else if (userCommands.get(0).equals("done")) {
            newCommand = new UpdateCommand(userCommands.subList(1, userCommands.size()));
        } else if (userCommands.get(0).equals("delete")) {
            newCommand = new DeleteCommand(userCommands.subList(1, userCommands.size()));
        } else if (userCommands.get(0).equals("bye")) {
            newCommand = new ExitCommand();
        } else if (userCommands.get(0).equals("find")) {
            newCommand = new FindCommand(String.join(" ", userCommands.subList(1, userCommands.size())));
        } else if (userCommands.get(0).equals("todo")) {
            String taskType = userCommands.get(0);
            String taskNameTodo = String.join(" ", userCommands.subList(1, userCommands.size()));
            newCommand = new AddCommand(taskType, taskNameTodo, false, "");
        } else if (userCommands.get(0).equals("event")) {
            String taskType = userCommands.get(0);
            int indexEvent = userCommands.indexOf("/at");
            if (indexEvent == -1) {
                throw new DukeExceptions();
            }
            List<String> taskNameEvent = userCommands.subList(1, indexEvent);
            List<String> timeConstraintEvent = userCommands.subList(indexEvent+1, userCommands.size());
            newCommand = new AddCommand(
                    taskType,
                    String.join(" ", taskNameEvent),
                    false,
                    String.join(" ", timeConstraintEvent)
            );
        } else if (userCommands.get(0).equals("deadline")) {
            String taskType = userCommands.get(0);
            int indexDeadline = userCommands.indexOf("/by");
            if (indexDeadline == -1) {
                throw new DukeExceptions();
            }
            List<String> taskNameDeadline = userCommands.subList(1, indexDeadline);
            List<String> timeConstraintDeadline = userCommands.subList(indexDeadline+1, userCommands.size());
            newCommand = new AddCommand(
                    taskType,
                    String.join(" ", taskNameDeadline),
                    false,
                    String.join(" ", timeConstraintDeadline)
            );
        } else {
            throw new DukeExceptions();
        }
        return newCommand;
    }

}
