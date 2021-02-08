package duke.command;

import java.util.Arrays;

public class UserInput {
    private String command;
    private String[] arguments;

    public UserInput(String rawInput) {
        processInput(rawInput);
    }

    private void processInput(String rawInput) {
        String[] inputFragments = rawInput.split(" ");
        command = inputFragments[0];
        arguments = Arrays.copyOfRange(inputFragments, 1, inputFragments.length);
    }

    public String getCommand() {
        return command;
    }

    public String[] getArguments() {
        return arguments;
    }
}