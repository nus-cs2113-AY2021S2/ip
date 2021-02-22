package dukehandler;

import exceptions.DateTimeErrorException;
import exceptions.EmptyCommandDescriptionException;
import exceptions.EmptyListException;
import exceptions.IllegalTaskMarkedDoneException;
import exceptions.IllegalTaskRemovedException;
import exceptions.InvalidCommandException;
import exceptions.StreamErrorException;
import exceptions.TaskAlreadyMarkedException;

import taskclasses.Deadline;
import taskclasses.Event;
import taskclasses.Task;
import taskclasses.ToDo;

import ui.ErrorMessagePrinter;
import ui.SuccessMessagePrinter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

public class TaskManager {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager() {
    }

    /**
     * If 'list' command is given
     */
    public static void printAllTasks() {
        boolean isAllDone = true;
        if (tasks.isEmpty()) {
            ErrorMessagePrinter.printEmptyListMessage();
            return;
        }
        SuccessMessagePrinter.printCurrentTimeAndDate();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; ++i) {
            if ((tasks.get(i - 1).getStatusIcon()).equals(" ")) {
                isAllDone = false;
            }
            System.out.println(" " + i + "." + tasks.get(i - 1).toString());
        }
        if (isAllDone) {
            System.out.println(" Woah, all completed! Good job!");
        }
    }

    /**
     * If 'done' command is given
     * @param doneIndexString input index to be verified by Checker
     */
    public static void markTaskAsDone(String doneIndexString) {
        int doneIndexInteger;
        try {
            doneIndexInteger = Integer.parseInt(doneIndexString);
            Checker.checkTaskToMarkDone(doneIndexInteger);
        } catch (EmptyListException eel) {
            ErrorMessagePrinter.printEmptyListMessage();
            return;
        } catch (IllegalTaskMarkedDoneException | NumberFormatException nfe) {
            ErrorMessagePrinter.printDoneOrDeleteTaskErrorMessage("done", tasks.size());
            return;
        } catch (TaskAlreadyMarkedException atmd) {
            ErrorMessagePrinter.printTaskAlreadyMarkedDone();
            return;
        }
        tasks.get(doneIndexInteger - 1).markAsDone();
        SuccessMessagePrinter.printTaskMarkedDone(doneIndexInteger - 1);
    }

    /**
     * If any one of the tasks are called to be added
     * @param taskType deadline, todo, or event
     * @param fullCommand full string input to be parsed later for description
     */
    public static void addNewTask(String taskType, String fullCommand) {
        String[] part = fullCommand.split(taskType);
        try {
            Checker.checkNewTaskToAdd(taskType, part[1].trim());
            String description = part[1].trim();

            switch (taskType) {
            case "todo":
                tasks.add(new ToDo(description));
                break;
            case "deadline":
            case "event":
                parseTaskToAdd(taskType, description);
                break;
            }
            SuccessMessagePrinter.printAddedTask();
        } catch (InvalidCommandException eic) {
            ErrorMessagePrinter.printInvalidCommandMessage(taskType);
        } catch (ArrayIndexOutOfBoundsException | EmptyCommandDescriptionException oob) {
            ErrorMessagePrinter.printEmptyCommandMessage(taskType);
        } catch (DateTimeErrorException dtee) {
            ErrorMessagePrinter.printTimeParseErrorMessage();
        }
    }

    public static void parseTaskToAdd(String taskType, String fullInput) throws DateTimeErrorException {
        String[] descriptionAndTime;
        descriptionAndTime = (taskType.equals("deadline") ? fullInput.split("/by")
                : fullInput.split("/at"));
        String taskName = descriptionAndTime[0].trim();
        String[] time = descriptionAndTime[1].trim().split(" ");
        String dateToString;
        String timeToString;
        try {
            LocalDate d = LocalDate.parse(time[0].trim());
            LocalTime t = LocalTime.parse(time[1].trim());
            dateToString = d.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            timeToString = t.format(DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException dtpe) {
            throw new DateTimeErrorException();
        }
        tasks.add(taskType.equals("deadline") ? new Deadline(taskName, dateToString, timeToString)
                : new Event(taskName, dateToString, timeToString));
    }

    /**
     * If 'delete' command is given
     * @param removeIndexString input index to be verified by Checker
     */
    public static void removeTask(String removeIndexString) {
        int removeIndexInt;
        try {
            removeIndexInt = Integer.parseInt(removeIndexString);
            Checker.checkTaskToRemove(removeIndexInt);
        } catch (IllegalTaskRemovedException | NumberFormatException nfe) {
            ErrorMessagePrinter.printDoneOrDeleteTaskErrorMessage("delete", tasks.size());
            return;
        } catch (EmptyListException eel) {
            ErrorMessagePrinter.printEmptyListMessage();
            return;
        }
        SuccessMessagePrinter.printRemovedTask(removeIndexInt);
        tasks.remove(tasks.get(removeIndexInt - 1));
    }

    public static void printTasksWithKeywords(String keyword) {
        try {
            Checker.checkTasksToFind(keyword.trim());
        } catch (InvalidCommandException i) {
            ErrorMessagePrinter.printInvalidCommandMessage("'find'");
            return;
        } catch (ArrayIndexOutOfBoundsException a) {
            ErrorMessagePrinter.printEmptyCommandMessage("find command");
            return;
        }
        System.out.println(" Here are the matching tasks in your list:");
        int[] i = {1};
        tasks.stream()
                .filter((t) -> t.getTaskName().trim().contains(keyword.trim()))
                .forEach((t) -> System.out.println(" " + (i[0]++) + ". " + t));
    }
  
  public static void printOneTaskTypeWithStreams(String taskTypeInput) {
        try {
            Checker.checkTaskTypeStreamToPrint(taskTypeInput.trim());
        } catch (StreamErrorException e) {
            ErrorMessagePrinter.printTaskStreamError("type");
            return;
        }
        tasks.stream()
                .filter((t) -> t.getTaskType().trim().equals(taskTypeInput.toUpperCase()))
                .forEach(System.out::println);
    }

    public static void printOneTaskDateWithStreams(String dateInput) {
        String dateToString;
        try {
            LocalDate d = LocalDate.parse(dateInput.trim());
            dateToString = d.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (DateTimeParseException s) {
            ErrorMessagePrinter.printTaskStreamError("date");
            return;
        }
        final String date = dateToString;
        tasks.stream()
                .filter((t) -> t.getDate().trim().equals(date))
                .forEach(System.out::println);
    }

}
