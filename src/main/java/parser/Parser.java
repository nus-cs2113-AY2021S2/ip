package parser;

import myexceptions.BlankDescriptionException;
import myexceptions.MissingDateException;
import tasklist.Deadline;
import tasklist.Event;
import tasklist.Todo;

public class Parser {

    public static String[] splitText(String text) {
        String[] textArray = text.split(" ",2);
        return textArray;
    }

    public static Todo parsingTodo(String userInput) throws BlankDescriptionException {
        String[] textArray = splitText(userInput);
        if (textArray.length <= 1 || !textArray[0].equalsIgnoreCase("todo")) {
            throw new BlankDescriptionException();
        }
        String description = textArray[1];
        return new Todo(description);
    }

    public static Deadline parsingDeadline(String userInput) throws BlankDescriptionException, MissingDateException {
        String[] textArray = splitText(userInput);

        if (textArray.length <= 1 || !textArray[0].equalsIgnoreCase("deadline")) {
            throw new BlankDescriptionException();
        }
        String description = textArray[1];
        String[] descriptionArray = description.split("/",2);
        if (descriptionArray[0].isBlank()) {
            throw new BlankDescriptionException();
        }
        else if (descriptionArray.length <= 1 || descriptionArray[1].isBlank()) {
            throw new MissingDateException();
        }
        description = descriptionArray[0];
        String by = descriptionArray[1];

        return new Deadline(description,by);
    }

    public static Event parsingEvent(String userInput) throws BlankDescriptionException, MissingDateException {
        String[] textArray = splitText(userInput);
        if (textArray.length <= 1 || !textArray[0].equalsIgnoreCase("event")) {
            throw new BlankDescriptionException();
        }
        String description = textArray[1];
        if (description.isBlank()) {
            throw new BlankDescriptionException();
        }
        String[] descriptionArray = description.split("/",2);
        if (descriptionArray[0].isBlank()){
            throw new BlankDescriptionException();
        }
        else if (descriptionArray.length <= 1 || descriptionArray[1].isBlank()) {
            throw new MissingDateException();
        }
        description = descriptionArray[0];
        String by = descriptionArray[1];

        return new Event(description,by);
    }
}
