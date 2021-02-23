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
    private static final String COMMAND_FIND_WORD = "find";

    // private static final String LEFTPAR = "[";
    // private static final String RIGHTPAR = "] ";

    private static int count;

    // private static String DONE = "done";
    // private static String UNDO = "undo";

    public static List<Task> lists = new ArrayList<Task>();

    public static void main(String[] args) {
        showWelcomeMessage();
        readFile(lists);
        showWelcomeMessage();
        while (true) {
            String userCommand = getUserInput();
            echoUserCommand(userCommand);
            int returnValue = executeCommand(userCommand);
            if (returnValue == 0)
                break;
        }
    }

    private static void readFile(List<Task> lists){
        try {
            File file = new File(FILE_LOCATION);
            if (file.createNewFile()) {
                System.out.println("A new file has been created!");
            } else {
                System.out.println("Reading saved Task Lists!");
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
                    count++;
                }
        }
    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(List<Task> lists) {
        try {
            FileWriter writer = new FileWriter(FILE_LOCATION,false);
            for (Task taskInList : lists) {
                writer.write(taskInList.getTaskType() + "-" + taskInList.isDone() + "-" + taskInList.getTask());
                writer.write("\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        String commandArgs = commandTypeAndParams[1];
        Task taskInput;
        switch (commandType) {
        case COMMAND_TODO_WORD:
            taskInput = new TodoTask(commandArgs);
            lists.add(taskInput);
            count++;
            return 1;
        case COMMAND_EVENT_WORD:
            checkError(commandArgs);
            commandArgs = commandArgs.replace("/", "(");
            commandArgs = commandArgs.replace("at", "at:");
            commandArgs = commandArgs+")";
            taskInput = new EventTask(commandArgs);
            lists.add(taskInput);
            count++;
            return 1;
        case COMMAND_DEADLINE_WORD:
            commandArgs = commandArgs.replace("/", "(");
            commandArgs = commandArgs.replace("by", "by:");
            commandArgs = commandArgs+")";
            taskInput = new DeadlineTask(commandArgs);
            lists.add(taskInput);
            count++;
            return 1;
        case COMMAND_LIST_WORD:
            printList(0,count);
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
        case COMMAND_FIND_WORD:
            findKeyword(commandArgs);
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

    public static void printList(int startIndex, int endIndex) {
        if (endIndex == 0) {
            System.out.println("List is empty :o\n" + "\n");
        } else {
            for(int i = startIndex; i < endIndex; ++i) {
                System.out.println(" " + (i + 1) + ": " + lists.get(i).toString());
            }
        }
    }

    public static void showError(){
        showToUser(ERROR_MESSAGE,DIVIDER);
    }

    public static void doneItem(String doneStringNumber){
        checkError(doneStringNumber);
        int doneInteger = Integer.parseInt(doneStringNumber)-1;
        lists.get(doneInteger).markAsDone();
    }

    public static void deleteItem(String deleteStringNumber){
        checkError(deleteStringNumber);
        if(Integer.parseInt(deleteStringNumber)>=count){
            showError();
        } else{
            lists.remove(Integer.parseInt(deleteStringNumber)-1);
            count--;
        }
    }

    public static void endSystem(){
        writeFile(lists);
        showToUser(BYE);
    }

    public static void checkError(String commandArgs){
        if (commandArgs.isEmpty()){
            showError();}
    }

    public static void findKeyword(String keyword){
        for(int i = 0; i < count; ++i){
            if(lists.get(i).toString().contains(keyword)){
                showToUser(lists.get(i).toString());
            }
        }
    }
    
}