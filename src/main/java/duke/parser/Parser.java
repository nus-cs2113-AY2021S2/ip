package duke.parser;

import duke.exception.DukeException;
import duke.task.TaskList;

import java.util.Scanner;

public class Parser {

    public static void determineCommand() {
        Scanner in = new Scanner(System.in);
        String input;

        while (in.hasNext()) {
            input = in.nextLine();
            if (input.equals("list")) {
                TaskList.listTask();
            } else if (input.startsWith("done")) {
                TaskList.markTaskDone(input);
            } else if (input.startsWith("delete")) {
                TaskList.deleteTask(input);
            } else if (input.startsWith("add")) {
                TaskList.addTask(input);
            } else if (input.startsWith("todo")) {
                TaskList.addToDo(input);
            } else if (input.startsWith("deadline")) {
                TaskList.addDeadline(input);
            } else if (input.startsWith("event")) {
                TaskList.addEvent(input);
            } else if (input.equals("bye")) {
                break;
            } else {
                DukeException.invalidCommand();
            }
        }
        in.close();
    }
}
