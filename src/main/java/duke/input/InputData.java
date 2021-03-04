package duke.input;

import java.util.Arrays;

/**
 * Wrapper of Input data from a user (input stream) or a local File system (reading a txt file for tasks
 * storage). An {@code InputData} object receives and processes a given raw input string. Useful information
 * (e.g. isDone, first argument, ...) would be extracted and stored in the {@code InputData} object.
 */
public class InputData {
    private String first;
    private boolean isDone = false;
    private String[] arguments;

    /**
     * Constructor of InputData<br>
     * Processes the given {@code rawInput} based on the inputType by calling private method {@code processInput()}.
     * Useful information would be extracted and stored within the {@code InputData} object.
     *
     * @param rawInput A String of input in standard format based on {@code InputType} (e.g. "E|1|ola /at 2021-08-31"
     *                 for recordInput, "todo job1" for userInput)
     * @param inputType Type of Input (i.e. userInput/recordInput)
     */
    public InputData(String rawInput, InputType inputType) {
        processInput(rawInput, inputType);
    }

    /**
     * Returns if the task is marked as done
     *
     * @return boolean value of whether the task is marked as done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the first argument extracted from the rawInput
     *
     * @return the first argument
     */
    public String getFirstArgument() {
        return first;
    }

    /**
     * Returns the other argument(s) extracted from the rawInput
     *
     * @return the other argument(s)
     */
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