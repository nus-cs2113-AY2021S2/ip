import duke.exceptions.EmptyInputException;
import duke.exceptions.IncompleteInputException;
import duke.exceptions.InvalidDateInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        String taskType;
        String taskName;
        String by = "";
        String at = "";
        Scanner splitInputScanner;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // To do textUITesting, comment line 19
        System.out.println(logo);
        printDividingLine();

        // Print welcome message
        printWelcomeMessage();

        // Load up save file, create file if it's not been created
        loadFile();
        printDividingLine();

        // Scan for input
        Scanner userInputScanner = new Scanner(System.in);
        String userInput = userInputScanner.nextLine();

        // Separate taskType and taskName(may contain
        // dates/time) if applicable
        userInput = userInput.trim();   // removes leading and trailing spaces
        if(isOneWord(userInput)) {
            taskType = userInput;
            taskName = "";
        }
        else{
            splitInputScanner = new Scanner(userInput);
            // Extract task type
            taskType = splitInputScanner.next().toLowerCase();
            // Extract task description
            taskName = splitInputScanner.nextLine();
        }

        // Loop for user input until "bye" is inputted
        while(!taskType.equals("bye")) {
            switch (taskType) {
            case "todo":
                Task t = new Todo(taskName);
                printDividingLine();
                addTaskWithValidation(userInput, t);
                printDividingLine();
                attemptSaveFile();
                break;
            case "deadline":
                by = extractTime(taskName);
                taskName  = extractTaskName(taskName);
                Task d = new Deadline(taskName, by);
                printDividingLine();
                addTaskWithValidation(userInput, d);
                printDividingLine();
                attemptSaveFile();
                break;
            case "event":
                at = extractTime(taskName);
                taskName = extractTaskName(taskName);
                Task e = new Event(taskName, at);
                printDividingLine();
                addTaskWithValidation(userInput, e);
                printDividingLine();
                attemptSaveFile();
                break;
            case "list":
                printDividingLine();
                Task.listTasks();
                printDividingLine();
                break;
            case "done":
                printDividingLine();
                Task.markAsDone(getTaskIndex(userInput));
                printDividingLine();
                attemptSaveFile();
                break;
            default:
                printCommandErrorMessage();
                break;
            }

            // Scan input again
            userInput = userInputScanner.nextLine();
            if(isOneWord(userInput)) {
                taskType = userInput;
                taskName = "";
            }
            else{
                splitInputScanner = new Scanner(userInput);
                taskType = splitInputScanner.next();
                taskName = splitInputScanner.nextLine();
            }
        }

        // Print Bye Message
        printByeMessage();
    }

    private static void attemptSaveFile() {
        try {
            saveFile("duke.txt");
        } catch (IOException e){
            System.out.println("Fail to save file.");
            e.printStackTrace();
        }
    }

    // Load the save file and update ArrayList
    private static void loadFile(){
        try {
            File f = new File("duke.txt");
            if (f.createNewFile()){
                System.out.println("Save file is created: " + f.getName());
            } else {
                try {
                    loadList("duke.txt");
                } catch (FileNotFoundException e){
                    System.out.println("Save file not found.");
                    e.printStackTrace();
                }
                System.out.println("Save file loaded successfully.");
            }
        } catch (IOException e) {
            System.out.println("Save file creation failed.");
            e.printStackTrace();
        }
    }

    // Recreate the ArrayList from save file
    private static void loadList(String filePath) throws FileNotFoundException{
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        s.nextLine();       // Skip the first line
        while (s.hasNext()){
            String listEntry = s.nextLine();
            Scanner listEntryS = new Scanner(listEntry);
            String taskType = listEntryS.next();
            String[] parser;
            String taskName;
            String taskStatus;
            String date;
            switch (taskType){
            case "T":
                parser = listEntry.split("\\|");
                taskName = parser[2].trim();
                Task t = new Todo(taskName);
                t.addTaskToArrayList();
                taskStatus = parser[1].trim();
                if (taskStatus.equals("1")){
                    t.setIsDone();
                }
                break;
            case "D":
                parser = listEntry.split("\\|");
                taskName = parser[2].trim();
                date = parser[3].trim();
                Task d = new Deadline(taskName, date);
                d.addTaskToArrayList();
                taskStatus = parser[1].trim();
                if (taskStatus.equals("1")){
                    d.setIsDone();
                }
                break;
            case "E":
                parser = listEntry.split("\\|");
                taskName = parser[2].trim();
                date = parser[3].trim();
                Task e = new Event(taskName, date);
                e.addTaskToArrayList();
                taskStatus = parser[1].trim();
                if (taskStatus.equals("1")){
                    e.setIsDone();
                }
                break;
            }
        }
    }

    private static void printFile(){
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.print(Task.getTask(i));
        }
    }

    private static void saveFile(String filePath) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        fw.write("List of tasks: " + System.lineSeparator());
        for (int i = 0; i < Task.getTaskCount(); i++) {
            fw.write(Task.getTask(i));
        }
        fw.close();
    }

    private static void addTaskWithValidation(String userInput, Task t) {
        try {
            t.addTask();
        } catch (EmptyInputException | IncompleteInputException e){
            t.printInputErrorMessage(userInput);
        } catch (InvalidDateInputException e){
            t.printInvalidDateInputMessage(userInput);
        }
    }

    private static String getTaskIndex(String userInput) {
        String[] userInputSplitted;
        String taskIndex;
        userInputSplitted = userInput.split(" ");
        taskIndex = userInputSplitted[1];
        return taskIndex;
    }

    private static boolean isOneWord(String userInput) {
        return !userInput.contains(" ");
    }

    private static void printCommandErrorMessage() {
        printDividingLine();
        System.out.println("Uh oh this command is not available :<");
        printDividingLine();
    }

    private static void printWelcomeMessage() {
        printDividingLine();
        System.out.println("Hello! I'm Duke");
        System.out.print("What can I do for you?\n");
        printDividingLine();
    }

    private static void printByeMessage() {
        printDividingLine();
        System.out.println("Bye. Hope to see you again soon! :3");
        printDividingLine();
    }

    private static void printDividingLine() {
        System.out.println("_____________________________________________________");
    }

    private static String extractTaskName(String s){
        // If s is empty
        if (isEmpty(s)){
            return "";
        }

        String[] splitArray = s.split("/");

        return splitArray[0];
    }

    private static String extractTime(String s){
        // If s is empty
        if (isEmpty(s)){
            return "";
        }

        String[] splitArray = s.split("/");

        // If there is no date field
        if (splitArray.length < 2){
            return "";
        }

        // If s is invalid input
        if (splitArray[1].split(" ").length != 2){
            return "";
        }

        Scanner sc = new Scanner(splitArray[1]);
        sc.next();              // remove 'by' or 'at'
        return sc.nextLine();   // extract time/date
    }

    private static boolean isEmpty(String s){
        return s.equals("");
    }
}
