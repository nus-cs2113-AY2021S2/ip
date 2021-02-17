package duke.command;

import java.util.Arrays;

public class InputData {
    private String command;
    private String[] arguments;

    public InputData(String rawInput, String splittingString) {
        processInput(rawInput, splittingString);
    }

    private void processInput(String rawInput, String splittingString) {
        String[] inputFragments = rawInput.split(splittingString);
        command = inputFragments[0];
        arguments = Arrays.copyOfRange(inputFragments, 1, inputFragments.length);
    }

    public String getFirstArgument() {
        return command;
    }

    public String[] getOtherArguments() {
        return arguments;
    }
}