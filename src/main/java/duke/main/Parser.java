package duke.main;

import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.exceptions.*;
import duke.items.Task;

import static duke.main.UI.convertDateToStringFormat;

/** Class in charge of understanding input commands */
public class Parser {

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
     * Returns the command keyword of a an input line
     *
     * @param line  entire input command
     * @return command keyword
     */
    public static String getCommand(String line) {
        return line.split(" ")[0];
    }

    /**
     * Returns the description of a todo task input
     *
     * @param line  entire input command
     * @return String description of the todo task
     */
    public static String extractTodo(String line) {
        return line.substring(line.indexOf(" ") + 1);
    }

    /**
     * Returns the description and date of an Deadline/Event task input
     *
     * @param line  entire input command
     * @return String array containing description and date of the Deadline/Event task
     * @throws DeadlineParameterExceptions  If /by is missing for deadline task
     * @throws EventParameterExceptions  If /at is missing for event task
     * @throws InvalidParameterLengthExceptions  If number of parameters < 4
     */
    public static String[] extractDeadlineAndEvent(String line) throws InvalidParameterLengthExceptions,
            DeadlineParameterExceptions, EventParameterExceptions {

        int indexOfSlash;
        if (getCommand(line).equals("deadline")) {
            indexOfSlash = validateDeadlineCommand(line);
        } else {
            indexOfSlash = validateEventCommand(line);
        }
        String[] returnList = new String[2];
        returnList[0] =  line.substring(line.indexOf(" ") + 1, indexOfSlash - 1);
        returnList[1] =  line.substring(indexOfSlash + 4);
        return returnList;
    }

    /**
     * Decides which functions to run based on the input command
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

        switch (getCommand(line)) {
        case ("bye"):
            Storage.writeToFile();
            return -1;

        case ("list"):
            UI.displayListPreamble();
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
            Task.deleteTask(indexToDelete);
            UI.displayListUpdate();
            break;
        case ("find"):
            UI.displayFindPreamble();
            Task.findTask(line.split(" ")[1]);
            break;
        case ("findAt"):
            UI.displayFindPreamble();
            Task.findAt(convertDateToStringFormat(line.split(" ")[1]));
            break;
        case ("findBy"):
            UI.displayFindPreamble();
            Task.findBy(line.split(" ")[1]);
            break;
        case ("todo"):
            Task.addTodo(line);
            break;
        case ("deadline"):
            Task.addDeadline(line);
            break;
        case ("event"):
            Task.addEvent(line);
            break;
        default:
            throw new InvalidCommandExceptions();
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

