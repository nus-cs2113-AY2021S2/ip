
import java.awt.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents the main class Duke.
 */
public class Duke {
    private static Ui ui = new Ui();
    private static TaskList tasks = new TaskList();
    private static Parser parser = new Parser();
    private static Storage storage = new Storage();
    private static String user_input;

    /**
     * Prints out the welcome message.
     */
    public static void show_welcome_msg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +

                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    /**
     * Prints out the bye message.
     */
    public static void show_exit_msg() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Ensembles all methods that each user-command requires.
     * @param input user input
     * @param c class command
     * @throws InvalidCommandException
     * @throws EmptyInputException
     * @throws InvalidEventException
     * @throws InvalidDeadlineException
     */
    public static void executeCommand(String input, Command c) throws InvalidCommandException, EmptyInputException, InvalidEventException, InvalidDeadlineException {
        switch (c) {
            case LIST:
                tasks.listTaskMsg();
                tasks.enumerateTasks();
                break;
            case DONE:
                tasks.markAsDone(input);
                break;
            case TODO:
                parser.checkValidInput(input, c);
                ui.plusNumOfGoals();
                tasks.addNewTodo(input);
                tasks.repeatTaskAdded();
                break;
            case EVENT:
                parser.checkValidInput(input, c);
                ui.plusNumOfGoals();
                tasks.addNewEvent(input);
                tasks.repeatTaskAdded();
                break;
            case DEADLINE:
                parser.checkValidInput(input, c);
                ui.plusNumOfGoals();
                tasks.addNewDeadline(input);
                tasks.repeatTaskAdded();
                break;
            case DELETE:
                tasks.deleteTask(user_input);
                break;
            case FIND:
                tasks.findTasks(user_input);
                break;
            case INVALID:
                throw new InvalidCommandException();

        }
    }

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {

        show_welcome_msg();
        Command command;
        storage.loadFile();
        Scanner in = new Scanner(System.in);
        user_input = ui.getString(in);



        while (!parser.isBye(user_input)) {
            if (parser.isList(user_input)) {
                command = Command.LIST;
            } else if (parser.isDone(user_input)) {
                command = Command.DONE;
            } else if (parser.isTodo(user_input)) {
                command = Command.TODO;
            } else if (parser.isEvent(user_input)) {
                command = Command.EVENT;
            } else if (parser.isDeadline(user_input)) {
                command = Command.DEADLINE;
            } else if (parser.isDelete(user_input)) {
                command = Command.DELETE;
            } else if (parser.isFind(user_input)){
                command =Command.FIND;
            } else {
                command = Command.INVALID;
            }

            try {
                executeCommand(user_input, command);
            } catch (InvalidCommandException e) {
                System.out.println("Oh no! I'm sorry, but I don't know what that means, please try again! ");
            } catch (EmptyInputException e) {
                System.out.println("The description of a new task cannot be empty.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(" Add time for new Event or Deadline with '/at' or '/by'!!");
            } catch (InvalidEventException e) {
                System.out.println(" Add time for new Event with keyword '/at'!!");
            } catch (InvalidDeadlineException e) {
                System.out.println("Add time for new Deadline with keyword '/by'!!");
            }
            user_input = ui.getString(in);
            storage.changeFile(ui.num_of_goals);
        }
        show_exit_msg();
        storage.clearFile();
    }
}