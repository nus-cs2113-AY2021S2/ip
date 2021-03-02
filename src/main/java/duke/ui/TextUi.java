package duke.ui;

import duke.Command;
import duke.TaskList;
import duke.parser.Parser;

import java.util.Scanner;

public class TextUi {
    private final Scanner in;

    public TextUi() {
        in = initialiseInput();
    }

    public void printInitialMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public String scanInput() {
        return in.nextLine();
    }

    public void printReaction(TaskList taskList, Command command, String userCommandText) {
        switch (command) {
        case BYE:
            System.out.println("Bye. Hope to see you again soon!");
            break;
        case LIST:
            System.out.println(taskList);
            break;
        case DONE:
            int taskNum = Parser.getTaskNum(userCommandText, Command.DONE);
            System.out.println("Nice! I've marked this task as done:" + System.lineSeparator() +
                    taskList.getTask(taskNum - 1));
            break;
        case DELETE:
            taskNum = Parser.getTaskNum(userCommandText, Command.DELETE);
            System.out.println("Noted. I've removed this task:"+ System.lineSeparator() +
                    taskList.getDeletedTask(taskNum - 1));
        case ERROR:
            break;
        default:
            System.out.println("Added: " + taskList.getLastTask());
        }
    }

    public Scanner initialiseInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }

    public boolean isExit(Command command) {
        return command == Command.BYE;
    }
}
