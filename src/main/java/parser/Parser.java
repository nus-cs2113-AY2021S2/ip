package parser;

import myexceptions.*;
import tasklist.*;

import java.util.Locale;

/**
 * Parser which parses the user input before being used for
 * adding various tasks or accessing other method calls
 */
public class Parser {

    /**
     * Method that splits user input into 2 sections.
     * First section contains desired command
     * Second section contains the description of the task
     *
     * @param text This is the user input. It is a string
     * @return String[] This returns an array, containing 2 fields
     */
    public static String[] splitTextIntoTwoFields(String text) {
        String[] textArray = text.split(" ", 2);
        return textArray;
    }


    /**
     * This method parses the user input and splits the description
     * portion of the todo task
     *
     * @param userInput This refers to the string input by the user
     * @return Todo(description) This returns an object of the Todo class
     * @throws BlankDescriptionException If no description is given
     */
    public static Todo parsingTodo(String userInput) throws BlankDescriptionException {
        String[] textArray = splitTextIntoTwoFields(userInput);
        if (textArray.length <= 1 || textArray[1].isBlank()) {
            throw new BlankDescriptionException();
        }
        String description = textArray[1].trim();
        return new Todo(description);
    }


    /**
     * This method parses the user input and splits it into
     * the description and due date
     *
     * @param userInput This refers to the string input by the user
     * @return Deadline(description, by) This returns an object of
     * the Deadline class
     * @throws BlankDescriptionException if no description is given
     * @throws MissingDateException      if no due date is given
     */
    public static Deadline parsingDeadline(String userInput) throws BlankDescriptionException, MissingDateException {
        String[] textArray = splitTextIntoTwoFields(userInput);

        if (textArray.length <= 1) {
            throw new BlankDescriptionException();
        }
        String description = textArray[1].trim();  //Prevents data overload, just in case
        String[] descriptionArray = description.split("/", 2);
        if (descriptionArray[0].isBlank()) {
            throw new BlankDescriptionException();
        } else if (descriptionArray.length <= 1 || descriptionArray[1].isBlank()) {
            throw new MissingDateException();
        }
        description = descriptionArray[0].trim();
        String by = descriptionArray[1].trim();

        return new Deadline(description, by);
    }


    /**
     * This method parses the user input and splits it into
     * the description and date of event
     *
     * @param userInput This refers to the string input by the user
     * @return Event(description, by) This returns an object of
     * the Event class
     * @throws BlankDescriptionException if no description is given
     * @throws MissingDateException      if no date of event is given
     */
    public static Event parsingEvent(String userInput) throws BlankDescriptionException, MissingDateException {
        String[] textArray = splitTextIntoTwoFields(userInput);
        if (textArray.length <= 1) {
            throw new BlankDescriptionException();
        }
        String description = textArray[1].trim();
        if (description.isBlank()) {
            throw new BlankDescriptionException();
        }
        String[] descriptionArray = description.split("/", 2);
        if (descriptionArray[0].isBlank()) {
            throw new BlankDescriptionException();
        } else if (descriptionArray.length <= 1 || descriptionArray[1].isBlank()) {
            throw new MissingDateException();
        }
        description = descriptionArray[0].trim();
        String by = descriptionArray[1].trim();

        return new Event(description, by);
    }

    /**
     * This method parses the user input and returns the index of
     * the task to be deleted
     *
     * @param userInput This is the number of the task that the user
     *                  wants to delete
     * @return Returns index of the task to be deleted
     * @throws BlankDescriptionException if no task number is given
     * @throws OutOfRangeException       if task number given is out of range
     *                                   of maximum task number in list
     * @throws EmptyListException        if list is empty
     */
    public static int parsingDeleteCommand(String userInput) throws BlankDescriptionException, OutOfRangeException, EmptyListException {
        int taskNumber;
        String[] textArray = splitTextIntoTwoFields(userInput);
        if (textArray.length <= 1) {
            throw new BlankDescriptionException();
        }
        taskNumber = Integer.parseInt(textArray[1].strip());
        if (Tasklist.isEmpty()) {
            throw new EmptyListException();
        } else if (taskNumber > Tasklist.getSize() || taskNumber <= 0) {
            throw new OutOfRangeException();
        }

        return taskNumber - 1;

    }


    /**
     * This method parses the user input and returns the keyword
     * that the user wants to search for
     *
     * @param userInput This is the user input
     * @return Returns the keyword that the user wants to search for
     * @throws BlankDescriptionException if no keyword that the user wants
     *                                   to search for is provided
     */
    public static String parsingFindCommand(String userInput)
            throws BlankDescriptionException {
        String[] textArray = splitTextIntoTwoFields(userInput);

        if (textArray.length <= 1 || textArray[1].isBlank()) {
            throw new BlankDescriptionException();
        }
        String keyword = textArray[1].trim();
        return keyword;
    }

    /**
     * This method marks the specified task as completed
     *
     * @param userInput This is the task number for the task the user wants
     *                  to label as completed
     * @return Returns index of the task to be marked as completed
     * @throws BlankDescriptionException if no task number is provided
     * @throws OutOfRangeException       if task number given is out of range
     *                                   of maximum task number in list
     */
    public static int parsingMarkAsDone(String userInput) throws BlankDescriptionException, OutOfRangeException {
        String[] textArray = splitTextIntoTwoFields(userInput);
        if (textArray.length <= 1 || textArray[1].isBlank()) {
            throw new BlankDescriptionException();
        }

        int index = Integer.parseInt(textArray[1].strip()) - 1;
        if (index < 0 || index > Tasklist.getSize() - 1) {
            throw new OutOfRangeException();
        }
        return index;
    }
}
