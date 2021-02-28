package Duke.Parser;

import Duke.Command.Command;
import Duke.Exceptions.DukeException;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

import java.io.IOException;

public class Parser {

    public static String parse(String inputs, TaskList taskList) {

        try {
            if (inputs.equals("bye")) {
                Command.exitCommand();

            } else if (inputs.equals("list")) {
                Ui.printTasksLists(taskList);

            } else if (inputs.contains("done")) {
                String numStr = inputs.substring(inputs.indexOf(" ") + 1);
                int num = Integer.parseInt(numStr);
                taskList.markAsDone(num);

            } else if (inputs.contains("deadline")) {
                taskList.addDeadline(inputs);

            } else if (inputs.contains("todo")) {
                taskList.addTodo(inputs);

            } else if (inputs.contains("event")) {
                taskList.addEvent(inputs);

            } else if (inputs.contains("delete")) {
                String numStr = inputs.substring(inputs.indexOf(" ") + 1);
                int num = Integer.parseInt(numStr);
                taskList.deleteTask(num);

            } else if (inputs.contains("find")) {
                taskList.findTasks(inputs);

            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! That task number does not exist");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! That input is in the wrong format!");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Sorry, something wrong with file");
        } catch (NullPointerException e) {
            System.out.println("Null pointer cannot pass");
        }
        return "";
    }
}
