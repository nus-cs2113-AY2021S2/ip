package Baggie.Parser;

import Baggie.Commands.*;
import Baggie.Baggie;
import Baggie.UI.PrintMessages;
import Baggie.UI.TEXT;

import java.util.Scanner;
import java.lang.*;

public class ProcessInput extends Baggie {
    public static final String EXIT_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String DONE_COMMAND = "done";
    public static final String DELETE_COMMAND = "delete";
    public static final String HELP_COMMAND = "help";
    public static final String KEYWORD_COMMAND = "keyword";
    public static final String DATE_COMMAND = "date";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String COMMAND_DELIMITER = " ";

    /**
     * Reads command input from user.
     * Switches to different tasks according to the first word.
     * Shows errors if the second word is invalid.
     */
    public static void processCommand() {
        Scanner userInput = new Scanner(System.in);
        while(true) {
            inputString = userInput.nextLine().trim();
            String[] inputStringSplit = inputString.split(COMMAND_DELIMITER, 2);
            System.out.println(TEXT.LINE);
            String commandKeyword = inputStringSplit[0].toLowerCase();
            String commandContent = inputString.substring(commandKeyword.length()).trim();
            switch (commandKeyword) {
            case EXIT_COMMAND:
                //ends program
                return;
            case DONE_COMMAND:
                //marks a task as done
                CommandHandler.doneHandler(commandContent);
                break;
            case DELETE_COMMAND:
                //deletes a task
                CommandHandler.deleteHandler(commandContent);
                break;
            case LIST_COMMAND:
                //shows list
                PrintListCommand.printList(0, taskCount);
                break;
            case HELP_COMMAND:
                //shows help menu
                PrintMessages.helpMenu();
                break;
            case DATE_COMMAND:
                //finds tasks on a date
                CommandHandler.dateHandler(commandContent);
                break;
            case KEYWORD_COMMAND:
                //finds tasks containing keyword
                CommandHandler.keywordHandler(commandContent);
                break;
            case TODO_COMMAND:
                CommandHandler.todoHandler(commandContent);
                break;
            case DEADLINE_COMMAND:
                CommandHandler.deadlineHandler(commandContent);
                break;
            case EVENT_COMMAND:
                CommandHandler.eventHandler(commandContent);
                break;
            default:
                PrintMessages.illegalInput();
                break;
            }
        }
    }
}
