package duke.parser;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.commands.Commands;
import duke.task.Task;

import java.util.ArrayList;


public class Parser {
    private TaskList list;
    private Ui ui;

    /**
     * Loop through all possible commands
     * Possible Commands:
     * Todo, event, Deadline, help, list, done, bye
     */
    public static void parseCommand(String input, ArrayList<Task> list) {
        try{
            if (list.size()>=100) {
                throw new IndexOutOfBoundsException();
            }
            if (input.equalsIgnoreCase("help")) {
                System.out.println(Ui.HELP_MESSAGE);
            } else if (input.toLowerCase().startsWith("done")) {
                Commands.doneTask(input,list);
            } else if (input.equalsIgnoreCase("list")) {
                Commands.printList(list);
            } else if (input.toLowerCase().startsWith("todo")) {
                Commands.addTodo(input,list);
            } else if (input.toLowerCase().startsWith("deadline")) {
                Commands.addDeadline(input,list);
            } else if (input.toLowerCase().startsWith("event")) {
                Commands.addEvent(input,list);
            } else if (input.toLowerCase().startsWith("delete")){
                Commands.deleteTask(input,list);
            } else {
                Ui.checkError("INVALID_COMMAND");
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.checkError("LIST_FULL");
        }
    }
}

