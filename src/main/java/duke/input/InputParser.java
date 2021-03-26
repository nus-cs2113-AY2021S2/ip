package duke.input;

import java.util.Arrays;

/**
 * Parser input data from a user via CLI or a local text file (retrieving stored user tasks).
 * An {@code InputParser} object receives and processes a given raw input string. Useful information
 * (e.g. isDone, arguments, ...) would be extracted and stored in the {@code InputParser} object.
 * Methods of returning useful information are also provided.
 */
public class InputParser {
    private String command;
    private String taskType;
    private boolean isDone = false;
    private String[] arguments;

    /**
     * Constructor of {@code InputParser}<br>
     * Processes the given {@code rawInput} based on the {@code inputType}.
     * Useful information would be extracted and stored within the {@code InputData} object.
     *
     * @param rawInput  A String of input in standard format based on {@code InputType} (e.g. "E|1|ola /at 2021-08-31"
     *                  for recordInput, "todo job1" for userInput)
     * @param inputType Type of Input (i.e. userInput / recordInput)
     */
    public InputParser(String rawInput, InputType inputType) {
        parserInput(rawInput, inputType);
    }

    public boolean isDone() {
        return isDone;
    }

    public String getCommand() {
        return command;
    }

    public String getTaskType() {
        return taskType;
    }

    public String[] getArguments() {
        return arguments;
    }

    private void parserInput(String rawInput, InputType inputType) {
        if (inputType.equals(InputType.recordInput)) {
            parserInputFromRecord(rawInput);
        } else {
            parserInputFromUser(rawInput);
        }
    }

    private void parserInputFromRecord(String rawInput) {
        String[] infoFragments = getInfoFragments(rawInput);
        if (infoFragments.length != 3) {
            throw new IllegalArgumentException("Record format is invalid.");
        }

        setTaskType(infoFragments[0]);
        setDone(infoFragments[1]);
        initializeCommandAndArgumentsFromRecord(infoFragments[2]);
    }

    private String[] getInfoFragments(String inputString) {
        return splitString(inputString, "\\|");
    }

    private void setTaskType(String input) {
        taskType = input;
    }

    private void setDone(String infoFragment) {
        if (infoFragment.equals("1")) {
            isDone = true;
        } else if (infoFragment.equals("0")) {
            isDone = false;
        } else {
            throw new IllegalArgumentException("Done section in the record is not either 1 or 0.");
        }
    }

    private void initializeCommandAndArgumentsFromRecord(String inputString) {
        arguments = splitString(inputString, " ");
    }

    private void parserInputFromUser(String rawInput) {
        String[] inputFragments = splitString(rawInput, " ");
        command = inputFragments[0];
        arguments = Arrays.copyOfRange(inputFragments, 1, inputFragments.length);
    }

    private String[] splitString(String inputString, String splitter) {
        return inputString.split(splitter);
    }
}