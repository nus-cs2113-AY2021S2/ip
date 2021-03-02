package parser;

import java.util.Arrays;

public class Parser {

    public boolean parseCommand(String[] input){
        String[] validInputs = {"list", "done", "todo", "deadline", "event", "delete"};
        return !Arrays.asList(validInputs).contains(input[0]);
    }

    public boolean parseToDoCommand(String[] input) {
        return input.length == 1;
    }

    public boolean parseListCommand(String[] input) {
        return input.length > 1;
    }
}
