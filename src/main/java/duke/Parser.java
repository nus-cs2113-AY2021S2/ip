package duke;

import duke.commands.*;
import duke.exception.DukeException;
import duke.exception.TaskType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Collections;

public class Parser {

    public Parser() {
    }

    //details extracted: task description, (task deadline/timeslot) depending on taskType
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
