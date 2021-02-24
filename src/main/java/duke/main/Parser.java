package duke.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import duke.exceptions.*;
import duke.items.Deadline;
import duke.items.Event;
import duke.items.Task;
import duke.items.Todo;

import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

import static duke.main.UI.convertDateToStringFormat;

/**
 * Class in charge of understanding input commands
 */
public class Parser {
    private static final String[] VALID_COMMANDS = {"findBy", "findAt", "find", "bye", "done", "list", "todo", "event", "deadline", "delete"};

    /**
     * Check that command word (1st word of input) is valid
     *
     * @param line  command used
     * @return integer 1 if valid, else 0
     */
    public static int validateCommand(String line){
        if ((Arrays.asList(VALID_COMMANDS).contains(line))) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Check number of parameters in input command is valid
     *
     * @param line  entire input command
     * @throws InvalidParameterLengthExceptions  If length of entire todo command is < 2
     */
    public static void validateTodoCommand(String line) throws InvalidParameterLengthExceptions {
        if (line.split(" ").length < 2){
            throw new InvalidParameterLengthExceptions();
        }
    }

    /**
     * Returns the index of the character "/" in the input command
     * while checking for the validity of the command
     *
     * @param line  entire input command
     * @return index of "/" in the /by indicator
     * @throws DeadlineParameterExceptions  If /by is missing
     * @throws InvalidParameterLengthExceptions  If number of parameters < 4
     */
    public static int validateDeadlineCommand(String line) throws DeadlineParameterExceptions, InvalidParameterLengthExceptions {
        int indexOfSlash;
        if (line.split(" ").length < 4){
            throw new InvalidParameterLengthExceptions();
        }
        indexOfSlash = line.indexOf("/by");
        if (indexOfSlash == -1) {
            throw new DeadlineParameterExceptions();
        }
        return indexOfSlash;
    }

    /**
     * Returns the index of the character "/" in the input command
     * while checking for the validity of the command
     *
     * @param line  entire input command
     * @return index of "/" in the /at indicator
     * @throws EventParameterExceptions  If /at is missing
     * @throws InvalidParameterLengthExceptions  If number of parameters < 4
     */
    public static int validateEventCommand(String line) throws EventParameterExceptions, InvalidParameterLengthExceptions {
        int indexOfSlash;
        if (line.split(" ").length < 4){
            throw new InvalidParameterLengthExceptions();
        }
        indexOfSlash = line.indexOf("/at");
        if (indexOfSlash == -1){
            throw new EventParameterExceptions();
        }
        return indexOfSlash;
    }

    /**
     * Main parser function that decides the respective functions
     * to run based on the input command
     *
     * @param line  entire input command
     * @return integer -1 when input request to exit, else 1
     * @throws InvalidCommandExceptions  If unrecognised command is used.
     * @throws InvalidParameterLengthExceptions  If number of parameters does not match command used.
     * @throws DeadlineParameterExceptions  If Deadline command format is not adhered to.
     * @throws EventParameterExceptions  If Event command format is not adhered to.
     * @throws InvalidIndexExceptions  If index out of range.
     * @throws IOException  If input output error occurs.
     * @throws ParseException If date conversion error occurs
     */
    public static int commandHandler(String line) throws InvalidCommandExceptions, InvalidParameterLengthExceptions,
            DeadlineParameterExceptions, EventParameterExceptions, InvalidIndexExceptions, IOException, ParseException {

        String[] arr;
        arr = line.split(" ");
        if (validateCommand(arr[0]) == 0){
            throw new InvalidCommandExceptions();
        }
        switch (arr[0]) {

        case ("bye"):
            Storage.writeToFile();
            return -1;

        case ("list"):
            UI.listPreamble();
            Task.printList();
            break;
        case ("done"):
            Task.setDone(Integer.parseInt(line.split(" ")[1]) - 1);
            break;
        case ("delete"):
            int indexToDelete = Integer.parseInt(line.split(" ")[1]) - 1;
            if (indexToDelete >= Task.getNumOfTasks()){
                throw new InvalidIndexExceptions();
            }
            Task.deleteTask(Integer.parseInt(line.split(" ")[1]) - 1);
            UI.listUpdate();
            break;
        case ("find"):
            UI.findPreamble();
            Task.findTask(line.split(" ")[1]);
            break;
        case ("findAt"):
            UI.findPreamble();
            Task.findAt(convertDateToStringFormat(line.split(" ")[1]));
            break;
        case ("findBy"):
            UI.findPreamble();
            Task.findBy(line.split(" ")[1]);
            break;
        case ("todo"):

            validateTodoCommand(line);
            System.out.println("Got it. I've added this task: ");
            Todo todo = new Todo(line.substring(line.indexOf(" ") + 1));
            Task.addTask(todo);
            System.out.print("  ");
            todo.print();
            UI.listUpdate();
            break;

        case ("deadline"):
            int indexOfSlash = validateDeadlineCommand(line);
            System.out.println("Got it. I've added this task: ");
            String item = line.substring(line.indexOf(" ") + 1, indexOfSlash - 1);
            String extra = line.substring(indexOfSlash + 4);
            Deadline deadline = new Deadline(item, convertDateToStringFormat(extra));
            Task.addTask(deadline);
            System.out.print("  ");
            deadline.print();
            UI.listUpdate();
            break;

        case ("event"):
            indexOfSlash = validateEventCommand(line);
            System.out.println("Got it. I've added this task: ");
            item = line.substring(line.indexOf(" ") + 1, indexOfSlash - 1);
            extra = line.substring(indexOfSlash + 4);
            Event event = new Event(item, convertDateToStringFormat(extra));
            Task.addTask(event);
            System.out.print("  ");
            event.print();
            UI.listUpdate();
        }
        return 1;
    }

    /**
     * Runs loop that repeatedly calls for input command
     * Runs commandHandler for each input command
     * Catches error and decides error message
     */
    public static void run(){
        Scanner in = new Scanner(System.in);
        while (true) {
            String line;
            line = in.nextLine();
            UI.printLine();
            try {
                if (commandHandler(line) == -1) {
                    break;
                }
            } catch (InvalidCommandExceptions e) {
                UI.InvalidCommandErrorMessage();
            } catch (InvalidParameterLengthExceptions e){
                UI.InvalidParameterLengthErrorMessage(line);
            } catch (EventParameterExceptions e){
                UI.EventParameterErrorMessage();
            } catch (DeadlineParameterExceptions e){
                UI.DeadlineParameterErrorMessage();
            } catch (InvalidIndexExceptions e){
                UI.InvalidIndexErrorMessage();
            } catch (DateTimeParseException e){
                UI.InvalidDateErrorMessage(line);
            } catch (ParseException | IOException e){
                e.printStackTrace();
            }
            UI.printLine();
        }
    }

}

