package duke.input;

import java.util.Arrays;

public class InputData {
    private String first;
    private boolean isDone = false;
    private String[] arguments;

    public InputData(String rawInput, InputType inputType) {
        processInput(rawInput, inputType);
    }

    public boolean isDone() {
        return isDone;
    }

    private void processInput(String rawInput, InputType inputType) {
        String[] inputFragments;
        if (inputType.equals(InputType.recordInput)) {
            inputFragments = rawInput.split("\\|");
            isDone = inputFragments[1].equals("1");
            arguments = inputFragments[2].split(" ");
        } else {
            inputFragments = rawInput.split(" ");
            arguments = Arrays.copyOfRange(inputFragments, 1, inputFragments.length);
        }
        first = inputFragments[0];
    }

    public String getFirstArgument() {
        return first;
    }

    public String[] getOtherArguments() {
        return arguments;
    }
}