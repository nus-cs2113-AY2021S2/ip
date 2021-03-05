package duke.input;

import java.util.Arrays;

/**
 * Wrapper class of input data from a user via CLI or a local text file (retriving stored user tasks).
 * An {@code InputDataHandler} object receives and processes a given raw input string. Useful information
 * (e.g. isDone, first argument, ...) would be extracted and stored in the {@code InputDataHandler} object.
 * Methods of returning stored useful information is also provided.
 */
public class InputDataHandler {
    private String first;
    private boolean isDone = false;
    private String[] arguments;

    /**
     * Constructor of InputDataHandler<br>
     * Processes the given {@code rawInput} based on the {@code inputType} by calling private method
     * {@code processInput()}. Useful information would be extracted and stored within the {@code InputData} object.
     *
     * @param rawInput A String of input in standard format based on {@code InputType} (e.g. "E|1|ola /at 2021-08-31"
     *                 for recordInput, "todo job1" for userInput)
     * @param inputType Type of Input (i.e. userInput / recordInput)
     */
    public InputDataHandler(String rawInput, InputType inputType) {
        processInput(rawInput, inputType);
    }

    public boolean isDone() {
        return isDone;
    }

    public String getFirstArgument() {
        return first;
    }

    public String[] getOtherArguments() {
        return arguments;
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
}