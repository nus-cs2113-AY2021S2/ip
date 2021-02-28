package duke.parser;

import duke.error.ListFullException;
import duke.error.WrongFormatException;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.commands.Commands;
import duke.task.Task;
import java.util.ArrayList;


public class Parser {
    private TaskList list;
    private Ui ui;

    /**
     * Parses the command user wants to execute and executes said command
     *
     * @param input input from Duke.txt
     * @param list The ArrayList to store all existing tasks from Duke.txt
     */
    public static void parseCommand(String input, ArrayList<Task> list) {
        try{
            if (list.size()>=100) {
                throw new ListFullException();
            }
            if (input.equalsIgnoreCase("bye")) {
                return;
            } else if (input.equalsIgnoreCase("help")) {
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
            } else if (input.toLowerCase().startsWith("find")){
                Commands.findTask(input,list);
            } else {
                Ui.checkError("INVALID_COMMAND");
            }
        } catch (ListFullException | WrongFormatException e) {
            Ui.checkError("LIST_FULL");
        }
    }
}

