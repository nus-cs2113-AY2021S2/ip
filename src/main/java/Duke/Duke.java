package Duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Duke.Task.*;
import Duke.UI.Ui;
import Duke.Storage.Storage;


public class Duke {
    private static final String DIVIDER = "===================================================";

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final char INPUT_COMMENT_MARKER = '#';
    public static final String COMMAND_TODO_WORD = "todo";
    public static final String COMMAND_EVENT_WORD = "event";
    public static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_DONE_WORD = "done";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_END_WORD = "bye";
    private static final String COMMAND_DELETE_WORD = "delete";
    private static final String BYE = "Bye. Hope to see you again soon!";
    private static final String ERROR_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String COMMAND_FIND_WORD = "find";
    private static final String COMMAND_HELP_WORD = "help";
    public static int count;

    public static List<Task> lists = new ArrayList<Task>();

    public static void main(String[] args) {
        Ui.showWelcomeMessage();
        Storage.readFile(lists);
        while (true) {
            String userCommand = getUserInput();
            Ui.echoUserCommand(userCommand);
            int returnValue = executeCommand(userCommand);
            if (returnValue == 0)
                break;
        }
    }

    /***
     *  Receive users input
     ***/
    private static String getUserInput() {
        System.out.print("Enter command: ");
        String inputLine = SCANNER.nextLine();
        // silently consume all blank and comment lines
        while (inputLine.trim().isEmpty() || inputLine.trim().charAt(0) == INPUT_COMMENT_MARKER) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine;
    }



    /***
     *  Run users input with a split of type and content
     ***/
    private static int executeCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        String commandArgs = commandTypeAndParams[1];
        Task taskInput;
        switch (commandType) {
        case COMMAND_TODO_WORD:
            if (checkError(commandArgs)){
            taskInput = new TodoTask(commandArgs);
            lists.add(taskInput);
            Ui.showToUser("You have add "+commandType+" "+commandArgs);
            count++;}
            return 1;
        case COMMAND_EVENT_WORD:
            if (checkError(commandArgs)){
            commandArgs = commandArgs.replace("/", "(");
            commandArgs = commandArgs.replace("at", "at:");
            commandArgs = commandArgs+")";
            taskInput = new EventTask(commandArgs);
            Ui.showToUser("You have add "+commandType+" "+commandArgs);
            lists.add(taskInput);
            count++;}
            return 1;
        case COMMAND_DEADLINE_WORD:
            if (checkError(commandArgs)){
            commandArgs = commandArgs.replace("/", "(");
            commandArgs = commandArgs.replace("by", "by:");
            commandArgs = commandArgs+")";
            taskInput = new DeadlineTask(commandArgs);
            Ui.showToUser("You have add "+commandType+" "+commandArgs);
            lists.add(taskInput);
            count++;}
            return 1;
        case COMMAND_LIST_WORD:
            printList(0,count);
            return 1;
        case COMMAND_DONE_WORD:
            if(checkError(commandArgs)){
            doneItem(commandArgs);
            Ui.showToUser("You have "+commandType+" "+commandArgs);}
            return 1;
        case COMMAND_END_WORD:
            endSystem();
            return 0;
        case COMMAND_DELETE_WORD:
            if(checkError(commandArgs)){
            deleteItem(commandArgs);
            Ui.showToUser("You have "+commandType+" "+commandArgs);}
            return 1;
        case COMMAND_FIND_WORD:
            if(checkError(commandArgs)){
            findKeyword(commandArgs);}
            return 1;
        case COMMAND_HELP_WORD:
            Ui.showHelpMenu();
            return 1;
        default:
            showError();
            return 1;
        }
    }

    /***
     *  Trims the input of when there is a empty space
     ***/
    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" }; // else case: no parameters
    }

    /***
     *  Print the list when user type list
     ***/
    public static void printList(int startIndex, int endIndex) {
        if (endIndex == 0) {
            System.out.println("List is empty :o\n" + "\n");
        } else {
            for(int i = startIndex; i < endIndex; ++i) {
                System.out.println(" " + (i + 1) + ": " + lists.get(i).toString());
            }
        }
    }

    /***
     *  Show the error message with one divider
     ***/
    public static void showError(){
        Ui.showToUser(ERROR_MESSAGE,DIVIDER);
    }

    /***
     *  Mark the selected item as done
     ***/
    public static void doneItem(String doneStringNumber){
        checkError(doneStringNumber);
        int doneInteger = Integer.parseInt(doneStringNumber)-1;
        lists.get(doneInteger).markAsDone();
    }

    /***
     *  Delete the selected item
     ***/
    public static void deleteItem(String deleteStringNumber){
        checkError(deleteStringNumber);
        if(Integer.parseInt(deleteStringNumber)>count){
            showError();
        } else{
            lists.remove(Integer.parseInt(deleteStringNumber)-1);
            count--;
        }
    }

    /***
     *  End the system by write the list into the file and show bye message
     ***/
    public static void endSystem (){
        Storage.writeFile(lists);
        Ui.showToUser(BYE);
    }


    /***
     *  Show error message if error appear
     ***/
    public static boolean checkError (String commandArgs){
        if (commandArgs.isEmpty()){
            showError();
            return false;
        }else{
            return true;
        }
    }

    /***
     *  Print the keyword related item if found the keyword inside the content
     ***/
    public static void findKeyword (String keyword){
        for(int i = 0; i < count; ++i){
            if(lists.get(i).toString().contains(keyword)){
                Ui.showToUser(lists.get(i).toString());
            }
        }
    }
}