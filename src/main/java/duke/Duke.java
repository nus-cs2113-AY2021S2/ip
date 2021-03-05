package duke;

import duke.myExceptions.InvalidInput;
import duke.myExceptions.NoContent;
import duke.myExceptions.NoTime;
import duke.myTasks.Deadline;
import duke.myTasks.Event;
import duke.myTasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Duke {

    public static TaskList list = new TaskList();
    public static boolean isRunning = true;
    public static String filePath = "savefile.txt";
    public static Save save = new Save();

    /**
     * @param command the name if the instruction
     * @param content additional information about the instruction, may or may not be needed
     * @throws InvalidInput thrown if comman is incorrect
     * @throws NoContent thrown if name is not given when initialising a Task
     * @throws NoTime thrown if time is not given when initialising a time-dependant task.
     * @throws NullPointerException thrown if the user asks for an index outside of the current range of the list
     * @throws NumberFormatException thrown if the user enters something else when the program expects a number
     * @throws ArrayIndexOutOfBoundsException thrown if the user asks for an index outside of the current range of the list
     */
    public static void executeInstruction(String command, String content) throws InvalidInput, NoContent, NoTime, NullPointerException, NumberFormatException, ArrayIndexOutOfBoundsException{
        switch (command) {
        case "bye":
            isRunning = false;
            save.save(list);
            break;
        case "list":
            list.printList();
            break;
        case "find":
            list.find(content);
            break;
        case "done":
            list.get(Integer.parseInt(content) - 1).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(Integer.parseInt(content) - 1).toString());
            break;
        case "todo":
            Todo todo = new Todo(content);
            list.addToList(todo);
            list.printAddMessage(todo);
            break;
        case "event":
            Event event = new Event(content);
            list.addToList(event);
            list.printAddMessage(event);
            break;
        case "deadline":
            Deadline deadline = new Deadline(content);
            list.addToList(deadline);
            list.printAddMessage(deadline);
            break;
        default:
            throw new InvalidInput();
        }
    }

    /**
     * the main function handles reading user input and formatting it for
     * use by the executeInstruction method.
     * Exception handling is also done in the main fucntion.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        save.checkSave();
        Scanner in = new Scanner(System.in);
        String input;
        do {
            input = in.nextLine();
            String command;
            String content = "";
            if (input.indexOf(' ') == -1) {
                command = input;
            } else {
                command = input.substring(0, input.indexOf(' '));
                content = input.substring(input.indexOf(' ') + 1);
            }
            try {
                executeInstruction(command, content);
            } catch (InvalidInput e) {
                System.out.println("Invalid command used");
            } catch (NoContent | StringIndexOutOfBoundsException e) {
                System.out.println("Needs a name");
            } catch (NoTime e) {
                System.out.println("Needs time. Add \"/by <time>\" to command.");
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                System.out.println("You have inputted an invalid index number");
            } catch (NumberFormatException e) {
                System.out.println("Specify which task to mark as done");
            }
        } while (isRunning);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
