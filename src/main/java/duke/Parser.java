package duke;

import duke.commands.*;
import duke.exception.DukeException;
import duke.exception.TaskType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

/**
 * Provides methods to parse user input and call appropriate methods.
 */
public class Parser {

    public Parser() {}

    /**
     * Extracts task details from user input.
     *
     * @param input full user input for adding task, containing keyword.
     * @param keyword keyword identifying what taskType the user wants to add.
     * @return array of details, depending on the taskType. [description, (deadline/timeslot)]
     */
    public static String[] extractDetailsFromInput(String input, String keyword) {
        String[] inputArray = new String[2];
        String inputWithoutKeyword = input.substring(keyword.length());
        int numDetails = 0;
        switch(keyword) {
        case "deadline":
            inputArray = inputWithoutKeyword.split("/by");
            numDetails += 2;
            break;
        case "event":
            inputArray = inputWithoutKeyword.split("/at");
            numDetails += 2;
            break;
        case "todo":
            inputArray[0] = inputWithoutKeyword;
            numDetails++;
            break;
        default:
            break;
        }

        for (int i = 0; i < numDetails; i++) {
            inputArray[i] = inputArray[i].strip();
        }
        return inputArray;
    }

    /**
     * Extracts indexes when multiple are given in user input.
     *
     * @param input Full user input with multiple indexes.
     * @param keyword Command keyword found in input.
     * @return ArrayList of indexes identified in input.
     */
    public static ArrayList<Integer> getInputIndexes(String input, String keyword) {
        String[] inputArray = input.split(" ");
        ArrayList<Integer> indexes = new ArrayList<>();

        //completedIndex holds the index of valid integer(s) in inputArray (indicating index in tasklist)
        int completedIndex;
        for (String word: inputArray) {
            if (word.equals(keyword)) {
                continue;
            } else {
                completedIndex = Integer.parseInt(word);
                //ensure that the index given is valid
                if (completedIndex > 0 && completedIndex <= Task.taskCount){
//                    tasks.get(completedIndex - 1).markAsDone();
                    indexes.add(completedIndex-1);
                } else {
                    System.out.printf("Task %d does not exist! Enter 'list' to view tasklist :)\n", completedIndex);
                }
            }
        }

        return indexes;
    }

    /**
     * Parses user input by identifying command words.
     * Processes input by identifying important details in input (e.g. description).
     * Returns specific new Command object depending on keyword identified (e.g. ListCommand, AddCommand).
     *
     * @param input Full user input.
     * @return new Command object.
     * @throws DukeException If keyword is not identified/input is not in the right format
     */
    public Command parseInput(String input) throws DukeException {
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("done")) {
            //this keeps track of indexes that user calls for actions on
            ArrayList<Integer> indexes = new ArrayList<>();
            indexes = getInputIndexes(input, "done");
            return new MarkDoneCommand(indexes);
        } else if (input.startsWith("delete")) {
            //this keeps track of indexes that user calls for actions on
            ArrayList<Integer> indexes = new ArrayList<>();
            indexes = getInputIndexes(input, "delete");
            return new DeleteCommand(indexes);
        } else {
            Task newTask = parseNewTask(input);
            return new AddCommand(newTask);
        }
    }

    /**
     * Parses input to identify important Task details.
     * If no valid keywords are found, INVALID DukeException will be thrown.
     *
     * @param input Full user input.
     * @return new Task object, depending on keyword identified.
     * @throws DukeException If input does not have any keyword/has keyword but incorrect format.
     */
    public Task parseNewTask(String input) throws DukeException {
        String[] inputArray;
        if (input.startsWith("deadline")) {
            try {
                inputArray = extractDetailsFromInput(input, "deadline");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(TaskType.DEADLINE);
            }
            return new Deadline(inputArray[0], inputArray[1]);
        } else if (input.startsWith("event")) {
            try {
                inputArray = extractDetailsFromInput(input, "event");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(TaskType.EVENT);
            }
            return new Event(inputArray[0], inputArray[1]);
        } else if (input.startsWith("todo")){
            try {
                inputArray = extractDetailsFromInput(input, "todo");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(TaskType.TODO);
            }
            return new Todo(inputArray[0]);
        } else {
            throw new DukeException(TaskType.INVALID);
        }
    }


}
