package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Duke.Task.*;

public class Duke {
    private static final String VERSION = "Duke - Version 1.0";
    private static final String DIVIDER = "===================================================";
    private static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello! I'm Duke\n" + "What can I do for you?\n";

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final char INPUT_COMMENT_MARKER = '#';
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_DONE_WORD = "done";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_END_WORD = "bye";
    private static final String COMMAND_DELETE_WORD = "delete";
    private static final String BYE = "Bye. Hope to see you again soon!";
    private static final String ERROR_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String FILE_LOCATION = "src/main/java/Duke/Duke.txt";

    private static final int CAPACITY = 100;
    private static final int ITEM_DATA_COUNT = 3;

    private static final String LEFTPAR = "[";
    private static final String RIGHTPAR = "] ";

    private static String[][] allItems;
    private static int count;

    private static String DONE = "done";
    private static String UNDO = "undo";

    public static List<Task> lists = new ArrayList<Task>();

    public static void main(String[] args) {
        showWelcomeMessage();
        readFile(lists);
        // initItemBook();
        // showWelcomeMessage();
        // while (true) {
        //     String userCommand = getUserInput();
        //     echoUserCommand(userCommand);
        //     int returnValue = executeCommand(userCommand);
        //     if (returnValue == 0)
        //         break;
        // }
    }

    private static void readFile(List<Task> lists){
        try {
            File file = new File("src/main/java/Duke/Duke.txt");
            if (file.createNewFile()) {
                System.out.println("A new file has been created! ^_^");
            } else {
                System.out.println("Reading saved Task Lists ^_^");
                Scanner readingFile = new Scanner(file);
                while (readingFile.hasNextLine()) {
                    String line = readingFile.nextLine();
                    String[] parts = line.split("-", 3);
                    String type = parts[0];
                    String isDone = parts[1];
                    String task = parts[2];
                    if (type.equals(COMMAND_EVENT_WORD)) {
                        Task taskInFile = new EventTask(task);
                        lists.add(taskInFile);
                        showToUser(taskInFile.toString());
                        if (isDone.equals("true")) {
                            taskInFile.markAsDone();
                        }
                    }else if (type.equals(COMMAND_DEADLINE_WORD)) {
                        Task taskInFile = new DeadlineTask(task);
                        lists.add(taskInFile);
                        showToUser(taskInFile.toString());
                        if (isDone.equals("true")) {
                            taskInFile.markAsDone();
                        }
                    }else if (type.equals(COMMAND_TODO_WORD)) {
                        Task taskInFile = new TodoTask(task);
                        lists.add(taskInFile);
                        showToUser(taskInFile.toString());
                        if (isDone.equals("true")) {
                            taskInFile.markAsDone();
                        }
                    }
                }
        }
    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(List<Task> lists) {
        try {
            FileWriter writer = new FileWriter("src/main/java/Duke/Duke.txt",false);
            for (Task taskInList : lists) {
                writer.write(taskInList.getTaskType() + "-" + taskInList.isDone() + "-" + taskInList.getTask());
                writer.write("\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addItemToItemBook(String commandType, String commandData) {
        allItems[count][0] = commandType;
        allItems[count][1] = UNDO;
        allItems[count][2] = commandData;
        count++;
        // writeFile(commandType+"-"+UNDO+"-"+commandData);
    }

    private static void showWelcomeMessage() {
        showToUser(DIVIDER, DIVIDER, VERSION, LOGO, GREETING, DIVIDER);
    }

    private static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    private static String getUserInput() {
        System.out.print("Enter command: ");
        String inputLine = SCANNER.nextLine();
        // silently consume all blank and comment lines
        while (inputLine.trim().isEmpty() || inputLine.trim().charAt(0) == INPUT_COMMENT_MARKER) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine;
    }

    private static void echoUserCommand(String userCommand) {
        showToUser("[Command entered:" + userCommand + "]", DIVIDER);
    }

    private static int executeCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        Task taskInFile;
        switch (commandType) {
        case COMMAND_TODO_WORD:
            taskInFile = new TodoTask(commandArgs);
            lists.add(taskInFile);
            // addTodoItem(commandArgs);
            return 1;
        case COMMAND_EVENT_WORD:
            taskInFile = new EventTask(commandArgs);
            lists.add(taskInFile);
            // addEventItem(commandArgs);
            return 1;
        case COMMAND_DEADLINE_WORD:
            taskInFile = new DeadlineTask(commandArgs);
            lists.add(taskInFile);
            return 1;
        case COMMAND_LIST_WORD:
            showListItem();
            return 1;
        case COMMAND_DONE_WORD:
            doneItem(commandArgs);
            return 1;
        case COMMAND_END_WORD:
            endSystem();
            return 0;
        case COMMAND_DELETE_WORD:
            deleteItem(commandArgs);
            return 1;
        default:
            showError();
            return 1;
        }
    }

    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" }; // else case: no parameters
    }


    public static void showListItem(){
        for (int i = 0; i<count; i++){
            int index = i+1;
            showToUser(index+". "+LEFTPAR+allItems[i][0]+RIGHTPAR+LEFTPAR+allItems[i][1]+RIGHTPAR+allItems[i][2]);}
    }

    public static void showError(){
        showToUser(ERROR_MESSAGE,DIVIDER);
    }

    public static void addDeadlineItem(String commandArgs){
        checkError(commandArgs);
        commandArgs = commandArgs.replace("/", "(");
        commandArgs = commandArgs.replace("by", "by:");
        commandArgs = commandArgs+")";
        addItemToItemBook(COMMAND_DEADLINE_WORD,commandArgs);
    }

    public static void addTodoItem(String commandArgs){
        checkError(commandArgs);
        addItemToItemBook(COMMAND_TODO_WORD,commandArgs);
    }

    public static void addEventItem(String commandArgs){
        checkError(commandArgs);
        commandArgs = commandArgs.replace("/", "(");
        commandArgs = commandArgs.replace("at", "at:");
        commandArgs = commandArgs+")";
        addItemToItemBook(COMMAND_EVENT_WORD,commandArgs);
    }

    public static void doneItem(String doneStringNumber){
        checkError(doneStringNumber);
        int doneInteger = Integer.parseInt(doneStringNumber)-1;
        allItems[doneInteger][1] = DONE;
    }

    public static void deleteItem(String deleteStringNumber){
        checkError(deleteStringNumber);
        if(Integer.parseInt(deleteStringNumber)>=count){
            showError();
        } else{
            for(int i=Integer.parseInt(deleteStringNumber)-1;i<=count;i++){
                allItems[i][0]=allItems[i+1][0];
                allItems[i][1]=allItems[i+1][1];
                allItems[i][2]=allItems[i+1][2];
            }
            count--;
        }
    }

    public static void endSystem(){
        writeFile();
        showToUser(BYE);
    }

    public static void checkError(String commandArgs){
        if (commandArgs.isEmpty()){
            showError();}
    }
    
}