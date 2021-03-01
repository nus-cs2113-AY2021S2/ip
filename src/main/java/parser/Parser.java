package parser;

import myexceptions.*;
import tasklist.*;

import java.util.Locale;

public class Parser {

    public static String[] splitTextIntoTwoFields(String text) {
        String[] textArray = text.split(" ",2);
        return textArray;
    }

    public static Todo parsingTodo(String userInput) throws BlankDescriptionException {
        String[] textArray = splitTextIntoTwoFields(userInput);
        if (textArray.length <= 1 || textArray[1].isBlank() ) {
            throw new BlankDescriptionException();
        }
        String description = textArray[1].trim();
        return new Todo(description);
    }

    public static Deadline parsingDeadline(String userInput) throws BlankDescriptionException, MissingDateException {
        String[] textArray = splitTextIntoTwoFields(userInput);

        if (textArray.length <= 1) {
            throw new BlankDescriptionException();
        }
        String description = textArray[1].trim();  //Prevents data overload, just in case
        String[] descriptionArray = description.split("/",2);
        if (descriptionArray[0].isBlank()) {
            throw new BlankDescriptionException();
        } else if (descriptionArray.length <= 1 || descriptionArray[1].isBlank()) {
            throw new MissingDateException();
        }
        description = descriptionArray[0].trim();
        String by = descriptionArray[1].trim();

        return new Deadline(description,by);
    }

    public static Event parsingEvent(String userInput) throws BlankDescriptionException, MissingDateException {
        String[] textArray = splitTextIntoTwoFields(userInput);
        if (textArray.length <= 1) {
            throw new BlankDescriptionException();
        }
        String description = textArray[1].trim();
        if (description.isBlank()) {
            throw new BlankDescriptionException();
        }
        String[] descriptionArray = description.split("/",2);
        if (descriptionArray[0].isBlank()){
            throw new BlankDescriptionException();
        } else if (descriptionArray.length <= 1 || descriptionArray[1].isBlank()) {
            throw new MissingDateException();
        }
        description = descriptionArray[0].trim();
        String by = descriptionArray[1].trim();

        return new Event(description,by);
    }

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

    public static String parsingFindCommand(String userInput)
            throws InvalidSpaceException, BlankDescriptionException {
        String[] textArray = splitTextIntoTwoFields(userInput);

        if(textArray.length <= 1 || textArray[1].isBlank()) {
            throw new BlankDescriptionException();
        }
        String keyword = textArray[1].trim();
        return keyword;
    }

    public static int parsingMarkAsDone(String userInput) throws BlankDescriptionException, OutOfRangeException {
        String[] textArray = splitTextIntoTwoFields(userInput);
        if(textArray.length <= 1 || textArray[1].isBlank()) {
            throw new BlankDescriptionException();
        }

        int index = Integer.parseInt(textArray[1].strip()) - 1;
        if (index < 0 || index > Tasklist.getSize() - 1) {
            throw new OutOfRangeException();
        }
        return index;
    }
}
