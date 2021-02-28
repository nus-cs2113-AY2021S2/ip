package duke;

import commands.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Storage {

    protected static String path = System.getProperty("user.dir") + "\\data";
    protected static final File importedList = new File(path);
    protected static todoList inputList;

    public Storage(){
    }
    public Storage (todoList input){
        inputList = input;
    }





    public static void returnPath(){
        String path = System.getProperty("user.dir") + "\\" +Storage.path;
        System.out.println(path);
    }

    public static void setData(todoList input){
        inputList = input;
    }

    public static String createDataFile() throws IOException {
        (new File(path)).mkdir();
        writeToFile("data/duke.txt", "");
        return "No Data file found!\nData file created at: " + path;
    }
    public static void writeToFile(String filePath, String addText) throws IOException { // Creates file at provided path and writes provided string to file.
        FileWriter fw = new FileWriter(filePath);
        fw.write(addText);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException { // Appends to file at provided path
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void updateFile() { // Updates data file with current list of tasks
        String file = "data/duke.txt";
        String out = inputList.tasksUpdate();
        try {
            writeToFile(file,"");
            appendToFile(file, out);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static String runImportLoopUntilEOF() throws FileNotFoundException, IncorrectFormatException {
        Command command;
        File importedList = new File("data/duke.txt");
        Scanner s = new Scanner(importedList);
        try {
            do {
                String userCommandText = s.nextLine();
                command = new Parser().parseImportTasks(userCommandText);
                CommandResult result = executeCommand(command);
                if(userCommandText.charAt(2) == 'X'){
                    command = new doneCommand(inputList);
                    result = command.execute();
                }
            } while (s.hasNext());
        }catch(NoSuchElementException ignored){
        }
        String MESSAGE_LIST_IMPORTED = "Task List has been imported! Here are your tasks: \n";
        CommandResult result = executeCommand(new listCommand());
        return MESSAGE_LIST_IMPORTED + result.feedbackToUser;
        //consoleOutput(result, MESSAGE_LIST_IMPORTED);
    }

    private static CommandResult executeCommand(Command command) {
        command.setData(inputList);
        try {
            return command.execute();
        }catch (IncorrectFormatException e){
            return new CommandResult(e.getMessage());
        }
    }

    public static String initialiseStorage() throws IncorrectFormatException {
        try{
            return runImportLoopUntilEOF();
        }catch(FileNotFoundException e){
            try{
                return createDataFile();
            }
            catch (IOException a){
                return "error creating data file";
            }
        }
    }


}
