package Baggie.Parser;

import Baggie.Commands.*;
import Baggie.Baggie;
import Baggie.UI.PrintMessages;
import Baggie.UI.TEXT;

import java.util.Scanner;

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

    /**
     * Reads command input from user
     * Switches to different tasks according to the first word
     * Shows errors if the second word is invalid
     */
    public static void processCommand(){
        Scanner userInput = new Scanner(System.in);
        while(true) {
            inputString = userInput.nextLine().trim();
            String[] inputStringSplit = inputString.split(" ", 2);
            System.out.println(TEXT.LINE);
            switch (inputStringSplit[0].toLowerCase()) {
            case EXIT_COMMAND:
                //ends program
                return;
            case DONE_COMMAND:
                //marks a task as done
                if (inputStringSplit.length > 1) {
                    //makes sure there's an index after "done", otherwise shows error
                    MarkAsDoneCommand.markAsDone(inputStringSplit[1]);
                } else {
                    PrintMessages.noIndexInput();
                }
                break;
            case DELETE_COMMAND:
                //deletes a task
                if (inputStringSplit.length > 1) {
                    DeleteCommand.deleteTask(inputStringSplit[1]);
                } else {
                    PrintMessages.noIndexInput();
                }
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
                if (inputStringSplit.length > 1) {
                    FindTaskCommand.FindWithDate(inputStringSplit[1]);
                } else {
                    PrintMessages.dateEmpty();
                }
                break;
            case KEYWORD_COMMAND:
                //finds tasks containing keyword
                if (inputStringSplit.length > 1) {
                    FindTaskCommand.FindWithKeyword(inputStringSplit[1]);
                } else {
                    PrintMessages.keywordEmpty();
                }
                break;
            case TODO_COMMAND:
                if (inputStringSplit.length > 1) {
                    AddTodoCommand.execute(inputStringSplit[1]);
                } else {
                    PrintMessages.taskDescriptionEmpty();
                }
                break;
            case DEADLINE_COMMAND:
                if (inputStringSplit.length > 1) {
                    AddDeadlineCommand.execute(inputStringSplit[1]);
                } else {
                    PrintMessages.taskDescriptionEmpty();
                }
                break;
            case EVENT_COMMAND:
                if (inputStringSplit.length > 1) {
                    AddEventCommand.execute(inputStringSplit[1]);
                } else {
                    PrintMessages.taskDescriptionEmpty();
                }
                break;
            default:
                PrintMessages.illegalInput();
                break;
            }
        }
    }
}
