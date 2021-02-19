package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import commands.Command;
import commands.CommandResult;
import commands.Parser;
import commands.byeCommand;
import commands.listCommand;



public class Duke {

    private static todoList inputList = new todoList();

    public static void dukeStartup() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

    }


    public static void createDataFile(String path, File importedList) throws IOException { // Checks if data file exists and if not, creates the directory and file if required.
        if (!importedList.exists()){
            try{
                writeToFile("data/duke.txt", "");
            }catch(FileNotFoundException e){
                (new File(path)).mkdir();
                writeToFile("data/duke.txt", "");
            }
            System.out.println("No Task List was found!\n"
                    + "Task List file was created at: " + path + "\n"
                    + "____________________________________________________________\n");
        }
    }

    public static void dukeInitialisation(todoList list){ // Tries to import data from the default path using importData if available. If not, creates data file using createDataFile
        String path = System.getProperty("user.dir") + "\\data";
        File importedList = new File("data/duke.txt");
        try{
            runImportLoopUntilEOF();
        }catch(FileNotFoundException e){
            try {
                createDataFile(path, importedList);
            } catch (IOException ioException) {
                System.out.println("Unable to create data file!");
            }
        }catch(IncorrectFormatException e){
            System.out.println("Error importing data!");
        }
    }

    public static void writeToFile(String filePath, String addText) throws IOException, IOException { // Creates file at provided path and writes provided string to file.
        FileWriter fw = new FileWriter(filePath);
        fw.write(addText);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException { // Appends to file at provided path
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void updateFile(todoList list) throws IOException{ // Updates data file with current list of tasks
        String file = "data/duke.txt";
        writeToFile(file,"");
        String out = list.tasksUpdate();
        try {
            appendToFile(file, out);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    private static CommandResult executeCommand(Command command) {
        command.setData(inputList);
        CommandResult result = command.execute();
        return result;
    }

    private static void consoleOutput(CommandResult input){
        String format = "____________________________________________________________\n" +
                "%s" + "\n" +
                "____________________________________________________________\n";
        System.out.println(String.format(format, input.feedbackToUser));
    }
    private static void consoleOutput(CommandResult input, String arg){
        String format = "____________________________________________________________\n" +
                "%s" + "\n" +
                "____________________________________________________________\n";
        System.out.println(String.format(format, arg + input.feedbackToUser));
    }

    private static void runCommandLoopUntilExitCommand() {
        Command command = null;
        do {
            Scanner in = new Scanner(System.in);
            String userCommandText = in.nextLine();
            try {
                command = new Parser().parseCommand(userCommandText);
            }catch(IncorrectFormatException e){
                System.out.println(e.getMessage()); ;
                continue;
            }
            CommandResult result = executeCommand(command);
            consoleOutput(result);
            try {
                updateFile(inputList);
            } catch (IOException e) {
                System.out.println("Unable to save to data file!");
            }
        } while (!byeCommand.isBye(command));
    }

    private static void runImportLoopUntilEOF() throws FileNotFoundException, IncorrectFormatException {
        Command command;
        String path = System.getProperty("user.dir") + "\\data";
        File importedList = new File("data/duke.txt");
        Scanner s = new Scanner(importedList);
        try {
            do {
                String userCommandText = s.nextLine();
                command = new Parser().parseImportTasks(userCommandText);
                CommandResult result = executeCommand(command);
            } while (s.hasNext());
        }catch(NoSuchElementException ignored){

        }
        String MESSAGE_LIST_IMPORTED = "Task List has been imported! Here are your tasks: \n";
        CommandResult result = executeCommand(new listCommand());
        consoleOutput(result, MESSAGE_LIST_IMPORTED);
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String output;
        dukeStartup();
        dukeInitialisation(inputList);
        runCommandLoopUntilExitCommand();

    }







}


