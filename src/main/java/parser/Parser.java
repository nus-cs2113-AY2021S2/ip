package parser;

import java.util.Arrays;

public class Parser {

    /**
     * Checks if the command is a valid input
     *
     * @param input User input
     * @return true if command is invalid otherwise false
     */
    public boolean parseCommand(String[] input){
        String[] validInputs = {"list", "done", "todo", "deadline", "event", "delete", "find"};
        return !Arrays.asList(validInputs).contains(input[0]);
    }

    /**
     * Checks if todo command is a valid input
     *
     * @param input User input
     * @return true if input is of size 1 otherwise false
     */
    public boolean parseToDoCommand(String[] input) {
        return input.length == 1;
    }

    /**
     * Checks if list command is a valid input
     *
     * @param input User input
     * @return true if input size is greater than 1 otherwise false
     */
    public boolean parseListCommand(String[] input) {
        return input.length > 1;
    }
}
