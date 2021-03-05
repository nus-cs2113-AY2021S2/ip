package duke.parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;


/**
 * Handles users' input.
 */
public class Parser {
    private static final int EVENT_LENGTH = 5;
    private static final int DEADLINE_LENGTH = 8;

    /**
     * Determines user's input and carry out the program.
     * @param input User's input.
     * if user's input is not recognised in the program, an error message is printed.
     */
    public static void parse(String input){
        if(input.equalsIgnoreCase("list")){
            Ui.printList();
        } else if(input.startsWith("done")) {
            TaskList.markTaskAsDone(input);
            Storage.writeFile();
        } else if(input.startsWith("todo")){
            TaskList.addTodo(input);
            Storage.writeFile();
        } else if(input.startsWith("deadline")){
            input = input.substring(DEADLINE_LENGTH);
            TaskList.addDeadline(input);
            Storage.writeFile();
        } else if(input.startsWith("event")){
            input = input.substring(EVENT_LENGTH);
            TaskList.addEvent(input);
            Storage.writeFile();
        } else if(input.startsWith("delete")){
            TaskList.deleteTask(input);
            Storage.writeFile();
        } else if (input.startsWith("find")) {
            TaskList.find(input);
            Storage.writeFile();
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }


}
