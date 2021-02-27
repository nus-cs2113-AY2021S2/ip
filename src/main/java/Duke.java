import exceptions.DukeException;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Duke {

    public static final int COMMAND_TODO = 0;
    public static final int COMMAND_DEADLINE = 1;
    public static final int COMMAND_EVENT = 2;

    public Duke() {
        String userInput;
        Scanner in = new Scanner(System.in);
        TaskList tasks = new TaskList();

        Ui.hello();

        try {
            TaskList.load();
        } catch (FileNotFoundException e) {
            System.out.println(Ui.FILE_NOT_FOUND);
        }

        while(true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                try {
                    tasks.save("duke.txt");
                } catch (IOException e) {
                    System.out.println(Ui.SAVE_ERROR);
                }
                Ui.bye();
                break;
            } else if (userInput.equals("list")) {
                try {
                    tasks.list();
                } catch (DukeException e) {
                    System.out.println(Ui.LIST_EMPTY);
                }
            } else if (userInput.startsWith("done")) {
                try {
                    tasks.mark(userInput);
                } catch (NumberFormatException | DukeException e) {
                    System.out.println(Ui.INVALID_NUMBER);
                }
            } else if (userInput.startsWith("todo")) {
                try {
                    tasks.addTask(userInput, COMMAND_TODO, true);
                } catch (DukeException e) {
                    System.out.println(Ui.INVALID_TODO);
                }
            } else if (userInput.startsWith("deadline")) {
                try {
                    tasks.addTask(userInput, COMMAND_DEADLINE, true);
                } catch (DukeException | StringIndexOutOfBoundsException e) {
                    System.out.println(Ui.INVALID_DEADLINE);
                }
            } else if (userInput.startsWith("event")) {
                try {
                    tasks.addTask(userInput, COMMAND_EVENT, true);
                } catch (DukeException |StringIndexOutOfBoundsException e) {
                    System.out.println(Ui.INVALID_EVENT);
                }
            } else if (userInput.startsWith("delete")) {
                try {
                    tasks.delete(userInput);
                } catch (DukeException e) {
                    System.out.println(Ui.INVALID_NUMBER);
                }
            } else if (userInput.startsWith("find")) {
                tasks.find(userInput);
            } else {
                System.out.println(Ui.UNKNOWN_COMMAND);
            }
        }
    }

    public static void main(String[] args) {
        new Duke();
    }
}
