package parser;

import command.*;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses the user input into useful data for easy handling
 */
public class Parser {
    /**Parses user input into command for execution
     * @param userInput processed for Uppercase full user input string
     * @return the command based on the user input
     */
    public static Command parseCommand(String userInput) throws DukeException{
        Command cmd = null;
        if (userInput.contains("LIST")) {
            cmd = new PrintCommand();
        } else if (userInput.contains("BYE")) {
            cmd = new ByeCommand();
        } else if (userInput.contains("DONE")) {
            cmd = new DoneCommand();
        } else if (userInput.contains("DELETE")) {
            cmd = new DeleteCommand();
        } else if(userInput.contains("FIND")){
            cmd = new FindCommand();
        }else if(userInput.contains("MENU")){
            cmd = new HelpCommand();
        }
        else if (!userInput.contains("BYE")) {
            cmd = new AddCommand();
        }
        else{
            throw new DukeException("Please use the appropriate commands");
        }
        return cmd;
    }

    /**
     * Extracts the task number in the current list that the user wants to mark as done
     * @param userInput full userInput String
     * @return task number to be marked done
     */
    public static int getTaskNoToBeMarkDone(String userInput) throws DukeException{
        String[] inputSplit = userInput.split("DONE ");
        int taskNoDone = 0;
        try {
            taskNoDone = Integer.parseInt(inputSplit[1]);
        } catch (NumberFormatException e){
            throw new DukeException("Please enter a valid number");
        }
        return taskNoDone;
    }


    /**
     * Extracts the task number in the current list that the user wants to delete
     * @param userInput full userInput String
     * @return task number to be deleted
     */
    public static int getTaskNoToBeMarkDelete(String userInput) throws DukeException{
        int taskNoDelete;
        String[] inputSplit = userInput.split("DELETE ");
        System.out.println(inputSplit[1]);
        try {
            taskNoDelete = Integer.parseInt(inputSplit[1]);
        }catch (NumberFormatException e)
        {
            throw new DukeException("Please enter a valid number!");
        }
        return taskNoDelete;
    }

    /**
     * parses through <code>userInput</code> to instantiates Tasks to be added to the Arraylist of Tasks
     * @param userInput full userInput String
     * @return taskToAdd Task object to be added
     */
    public static Task getTask(String userInput) throws DukeException{
        Task taskToAdd = null;
        if (userInput.contains("TODO")) {
            taskToAdd = getToDo(userInput);
        } else if (userInput.contains("DEADLINE")) {
            taskToAdd = getDeadline(userInput);
        } else if (userInput.contains("EVENT")) {
            taskToAdd = getEvent(userInput);
        } else{
            throw new DukeException("Please use the appropriate commands");
        }
        return taskToAdd;
    }

    /***
     * Creates new Event task
     * @param userInput full userInput String
     * @return a new Event task
     */
    private static Event getEvent(String userInput) {
        String removeKeyword = userInput.replaceAll("EVENT", "");
        String[] inputSplit = removeKeyword.split("/AT");
        return new Event(inputSplit[0],inputSplit[1]);
    }

    /***
     * Creates new ToDo task
     * @param userInput full userInput String
     * @return a new Todo task
     */
    private static Todo getToDo(String userInput) {
        String removeKeyword = userInput.replaceAll("TODO", "");
        //create task.Todo
        return new Todo(removeKeyword);
    }

    /***
     * Creates new Deadline task
     * @param userInput full userInput String
     * @return a new Deadline task
     */
    private static Deadline getDeadline(String userInput) throws DukeException {
        String removeKeyword = userInput.replaceAll("DEADLINE", "");
        String[] inputSplit = removeKeyword.split("/BY");
        LocalDate finalDate = parseTime(inputSplit[1]);
        return new Deadline(inputSplit[0], finalDate);
    }

    /***
     * Parses arguments in the context of the find person command
     * @param userInput full userInput String
     * @return the keywords set that the users wants to find
     */
    public static String getFindKeyword(String userInput){
        String keywordSet = userInput.replaceAll("FIND", "").strip();
        return keywordSet;
    }

    public static LocalDate parseTime(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(inputDate.strip(), formatter);
            return date;
        }catch (DateTimeParseException|StringIndexOutOfBoundsException e){
            throw new DukeException("\"Date format should be \"YYYY-MM-DD\"");
        }
    }

}
