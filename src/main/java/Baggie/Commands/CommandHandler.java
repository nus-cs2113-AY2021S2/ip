package Baggie.Commands;

import Baggie.Exceptions.*;
import Baggie.UI.PrintMessages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static Baggie.UI.TEXT.DATE_FORMAT;
import static Baggie.UI.TEXT.NULL;

/**
 * Handles commands before passing them to a specific method.
 */
public class CommandHandler {

    /**
     * Handles "done" command and passes the command to "markAsDone" method.
     *
     * @param commandContent the content of the command in String.
     */
    public static void doneHandler(String commandContent) {
        try {
            int index = convertIndex(commandContent);
            MarkAsDoneCommand.markAsDone(index);
        } catch (EmptyIndexException e) {
            PrintMessages.noIndexInput();
        } catch (IllegalIndexException e) {
            PrintMessages.illegalInput();
        }
    }

    /**
     * Handles "delete" command and passes the command to "deleteTask" method.
     *
     * @param commandContent the content of the command in String.
     */
    public static void deleteHandler(String commandContent) {
        try {
            int index = convertIndex(commandContent);
            DeleteCommand.deleteTask(index);
        } catch (EmptyIndexException e) {
            PrintMessages.noIndexInput();
        } catch (IllegalIndexException e) {
            PrintMessages.illegalInput();
        }
    }

    /**
     * Converts task number in String to task index in integer.
     *
     * @param taskNumber number of the task in String.
     * @return the index of the task in the list.
     * @throws IllegalIndexException if the index is illegal.
     * @throws EmptyIndexException   if the index field is empty.
     */
    public static int convertIndex(String taskNumber) throws IllegalIndexException, EmptyIndexException {
        try {
            if (taskNumber.equals(NULL)) {
                throw new EmptyIndexException();
            }
            return Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException nfe) {
            throw new IllegalIndexException();
        }
    }

    /**
     * Handles "date" command and passes the command to "FindWithDate" method.
     *
     * @param commandContent the content of the command in String.
     */
    public static void dateHandler(String commandContent) {
        try {
            String date = convertDate(commandContent);
            FindTaskCommand.findWithDate(date);
        } catch (EmptyDateException e) {
            PrintMessages.dateEmpty();
        } catch (IllegalDateException e) {
            PrintMessages.illegalDate();
        }
    }

    /**
     * Converts task number in String to task index in integer.
     *
     * @param dateInString number of the task in String.
     * @return the index of the task in the list.
     * @throws IllegalDateException if the index is illegal.
     * @throws EmptyDateException   if the index field is empty.
     */
    public static String convertDate(String dateInString) throws IllegalDateException, EmptyDateException {
        try {
            if (dateInString.equals(NULL)) {
                throw new EmptyDateException();
            }
            LocalDate parsedDate = LocalDate.parse(dateInString);
            return parsedDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (DateTimeParseException e) {
            throw new IllegalDateException();
        }
    }

    /**
     * Handles "date" command and passes the command to "FindWithDate" method.
     *
     * @param commandContent the content of the command in String.
     */
    public static void keywordHandler(String commandContent) {
        try {
            if (commandContent.equals(NULL)) {
                throw new EmptyKeywordException();
            }
            FindTaskCommand.findWithKeyword(commandContent);
        } catch (IllegalKeywordException e) {
            PrintMessages.cannotFind(commandContent);
        } catch (EmptyKeywordException e) {
            PrintMessages.keywordEmpty();
        }
    }

    /**
     * Handles "to do" command and passes the command to "AddTodoCommand".
     *
     * @param commandContent the content of the command in String.
     */
    public static void todoHandler(String commandContent) {
        if (commandContent.equals(NULL)) {
            PrintMessages.taskDescriptionEmpty();
        } else {
            AddTodoCommand.execute(commandContent);
        }
    }

    /**
     * Handles "deadline" command and passes the command to "AddDeadlineCommand".
     *
     * @param commandContent the content of the command in String.
     */
    public static void deadlineHandler(String commandContent) {
        if (commandContent.equals(NULL)) {
            PrintMessages.taskDescriptionEmpty();
        } else {
            AddDeadlineCommand.execute(commandContent);
        }
    }

    /**
     * Handles "event" command and passes the command to "AddEventCommand".
     *
     * @param commandContent the content of the command in String.
     */
    public static void eventHandler(String commandContent) {
        if (commandContent.equals(NULL)) {
            PrintMessages.taskDescriptionEmpty();
        } else {
            AddEventCommand.execute(commandContent);
        }
    }
}