package duke.controller;

import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.UI;

import java.util.ArrayList;

public class TaskManager {
    /**
     * processTask is the main class which redirects and classifies various inputs
     * @param input is the raw user input
     * @throws InvalidInputException in the event that an invalid command is issued
     */
    public static void processTask(String input) throws InvalidInputException {
        String[] inputData = input.split(" ");
        String[] taskDetails;
        switch (inputData[0]) {
            case "done":
            case "delete":
                processDoneOrDeletedTask(inputData);
                break;
            case "deadline":
                processDeadlineTask(inputData);
                break;
            case "event":
                processEventTask(inputData);
                break;
            case "todo":
                processTodoTask(inputData);
                break;
            case "find":
                processFindTask(inputData);
                break;
            default:
                throw new InvalidInputException();
        }
    }

    private static void processFindTask(String[] inputData) {
        try {
            ArrayList<Task> matches = Task.find(inputData[1]);
            UI.tasksFound(inputData[1], matches);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a search query");
            UI.requestInput();
        }

    }

    private static void processTodoTask(String[] inputData) {
        String[] taskDetails;
        try {
            taskDetails = parseTaskDetails(inputData);
            Todo.checkTodoInput(taskDetails);
            Task.addNewTask(new Todo(taskDetails[0]));
            UI.taskAddedSuccessfully();
        } catch (MissingTaskDescriptionException e) {
            System.out.println(e.getMessage());
            UI.requestInput();
        }
    }

    private static void processEventTask(String[] inputData) {
        String[] taskDetails;
        try {
            taskDetails = parseTaskDetails(inputData);
            Event.checkEventInput(taskDetails);
            Task.addNewTask(new Event(taskDetails[0], taskDetails[1]));
            UI.taskAddedSuccessfully();
        } catch (MissingEventDurationException e) {
            System.out.println(e.getMessage());
            UI.requestInput();
        } catch (MissingTaskDescriptionException e) {
            System.out.println(e.getMessage());
            UI.requestInput();
        } catch (InvalidDateTimeException e) {
            System.out.println(e.getMessage());
            UI.requestInput();
        }
    }

    private static void processDeadlineTask(String[] inputData) {
        String[] taskDetails;
        try {
            taskDetails = parseTaskDetails(inputData);
            Deadline.checkDeadlineInput(taskDetails);
            Task.addNewTask(new Deadline(taskDetails[0], taskDetails[1]));
            UI.taskAddedSuccessfully();
        } catch (MissingDueDateException e) {
            System.out.println(e.getMessage());
            UI.requestInput();
        } catch (MissingTaskDescriptionException e) {
            System.out.println(e.getMessage());
            UI.requestInput();
        } catch (InvalidDateTimeException e) {
            System.out.println(e.getMessage());
            UI.requestInput();
        }
    }

    private static void processDoneOrDeletedTask(String[] inputData) {
        try {
            int taskIndex = Integer.parseInt(inputData[1]);
            taskIndex--;
            Task.checkTaskIndex(taskIndex);
            if (inputData[0].equals("done")) {
                Task.checkTaskComplete(taskIndex);
                Task.completeTask(taskIndex);
                UI.taskCompletedSuccessfully();
            }
            else {
                Task.deleteTask(taskIndex);
                UI.taskDeletedSuccessfully();
            }

        } catch (NumberFormatException e) {
            System.out.println("That didn't work...please enter a valid number");
            UI.requestInput();
        } catch (TaskAlreadyCompletedException e) {
            System.out.println(e.getMessage());
            UI.requestInput();
        } catch (TaskNotExistException e) {
            System.out.println(e.getMessage());
            UI.requestInput();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input the correct number of the task you wish to interact with");
            UI.requestInput();
        }
    }


    /**
     * This method seeks to separate and organize user input
     * @param inputData is the raw user input data, separated by space
     * @return the separated data ready to create the respective Task objects
     */
    public static String[] parseTaskDetails(String[] inputData) {
        String[] parsedData = new String[2];
        String prefix = "";
        StringBuilder inputDueDate = new StringBuilder();
        StringBuilder inputDescription = new StringBuilder();
        int separatorIndex = -1;
        for (int i = 1; i < inputData.length; i++) {
            if (inputData[i].equals("||")) {
                separatorIndex = i;
                break;
            } else {
                inputDescription.append(prefix).append(inputData[i]);
                prefix = " ";
            }
        }
        parsedData[0] = inputDescription.toString();
        if (parsedData[0].equals("")) {
            parsedData[0] = null;
        }
        if (separatorIndex != -1) {
            prefix = "";
            for (int j = separatorIndex + 1; j < inputData.length; j++) {
                inputDueDate.append(prefix).append(inputData[j]);
                prefix = " ";
            }
            parsedData[1] = inputDueDate.toString();
            if (parsedData[1].equals("")) {
                parsedData[1] = null;
            }
        } else {
            parsedData[1] = null;
        }
        return parsedData;
    }
}
