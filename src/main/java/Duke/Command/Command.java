package Duke.Command;
import Duke.Duke;
import Duke.UI.Ui;
import Duke.Storage.Storage;
import Duke.Parser.Parser;
import Duke.Task.*;

public class Command {
    private static final String BYE = "Bye. Hope to see you again soon!";
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_DONE_WORD = "done";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_END_WORD = "bye";
    private static final String COMMAND_DELETE_WORD = "delete";
    private static final String COMMAND_FIND_WORD = "find";
    private static final String COMMAND_HELP_WORD = "help";

    /***
     *  Print the keyword related item if found the keyword inside the content
     ***/
    public static void findKeyword (String keyword){
        for(int i = 0; i < Duke.count; ++i){
            if(Duke.lists.get(i).toString().contains(keyword)){
                Ui.showToUser(Duke.lists.get(i).toString());
            }
        }
    }

    /***
     *  Print the list when user type list
     ***/
    public static void printList(int startIndex, int endIndex) {
        if (endIndex == 0) {
            System.out.println("List is empty :o\n" + "\n");
        } else {
            for(int i = startIndex; i < endIndex; ++i) {
                System.out.println(" " + (i + 1) + ": " + Duke.lists.get(i).toString());
            }
        }
    }

    /***
     *  Mark the selected item as done
     ***/
    public static void doneItem(String doneStringNumber){
        Parser.checkError(doneStringNumber);
        int doneInteger = Integer.parseInt(doneStringNumber)-1;
        Duke.lists.get(doneInteger).markAsDone();
    }

    /***
     *  Delete the selected item
     ***/
    public static void deleteItem(String deleteStringNumber){
        Parser.checkError(deleteStringNumber);
        if(Integer.parseInt(deleteStringNumber)>Duke.count){
            Ui.showError();
        } else{
            Duke.lists.remove(Integer.parseInt(deleteStringNumber)-1);
            Duke.count--;
        }
    }

    /***
     *  End the system by write the list into the file and show bye message
     ***/
    public static void endSystem (){
        Storage.writeFile(Duke.lists);
        Ui.showToUser(BYE);
    }

    /***
     *  Run users input with a split of type and content
     ***/
    public static int executeCommand(String userInputString) {
        final String[] commandTypeAndParams = Parser.splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        String commandArgs = commandTypeAndParams[1];
        Task taskInput;
        switch (commandType) {
        case COMMAND_TODO_WORD:
            if (Parser.checkError(commandArgs)){
            taskInput = new TodoTask(commandArgs);
            Duke.lists.add(taskInput);
            Ui.showToUser("You have add "+commandType+" "+commandArgs);
            Duke.count++;}
            return 1;
        case COMMAND_EVENT_WORD:
            if (Parser.checkError(commandArgs)){
            commandArgs = commandArgs.replace("/", "(");
            commandArgs = commandArgs.replace("at", "at:");
            commandArgs = commandArgs+")";
            taskInput = new EventTask(commandArgs);
            Ui.showToUser("You have add "+commandType+" "+commandArgs);
            Duke.lists.add(taskInput);
            Duke.count++;}
            return 1;
        case COMMAND_DEADLINE_WORD:
            if (Parser.checkError(commandArgs)){
            commandArgs = commandArgs.replace("/", "(");
            commandArgs = commandArgs.replace("by", "by:");
            commandArgs = commandArgs+")";
            taskInput = new DeadlineTask(commandArgs);
            Ui.showToUser("You have add "+commandType+" "+commandArgs);
            Duke.lists.add(taskInput);
            Duke.count++;}
            return 1;
        case COMMAND_LIST_WORD:
            printList(0,Duke.count);
            return 1;
        case COMMAND_DONE_WORD:
            if(Parser.checkError(commandArgs)){
            doneItem(commandArgs);
            Ui.showToUser("You have "+commandType+" "+commandArgs);}
            return 1;
        case COMMAND_END_WORD:
            endSystem();
            return 0;
        case COMMAND_DELETE_WORD:
            if(Parser.checkError(commandArgs)){
            deleteItem(commandArgs);
            Ui.showToUser("You have "+commandType+" "+commandArgs);
            printList(0,Duke.count);}
            return 1;
        case COMMAND_FIND_WORD:
            if(Parser.checkError(commandArgs)){
            findKeyword(commandArgs);}
            return 1;
        case COMMAND_HELP_WORD:
            Ui.showHelpMenu();
            return 1;
        default:
            Ui.showError();
            return 1;
        }
    }


}
