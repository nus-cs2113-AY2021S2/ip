import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class Parser {

    static Scanner scanner = new Scanner(System.in);
    static final String LINEBREAK = "____________________________________________________________\n";
    static ArrayList<String> commandList = new ArrayList<String>(
            Arrays.asList("bye", "list", "done", "todo", "deadline", "event")
    );
    static final String EXITCOMMAND = "bye";
    static final String TASKLIST_SAVE_PATH = "data/saveTaskList.txt";

    public static void printLine() {
        System.out.print(LINEBREAK);
    }

    static List list = new List();

    public static void Instance() {
        int saveNumber = 0;

        //checking if there is a save file and accessing it
        File savedList = new File(TASKLIST_SAVE_PATH);
        boolean doesSaveExist = savedList.exists();
        try {
            if (!doesSaveExist) {
                savedList.getParentFile().mkdirs();
                savedList.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! I am unable to create a new directory or file :-(");
        }
        try {
            Scanner saveFileScanner = new Scanner(savedList);

            while (saveFileScanner.hasNext()) {
                String currentRead = saveFileScanner.nextLine();
                String[] taskSave = currentRead.trim().split(" \\| ");
                switch (taskSave[0].trim()) {
                    case "todo":
                        saveNumber++;
                        list.addTaskFromSave(taskSave[2]);
                        if (taskSave[1].equals("1")) {
                            list.markDoneFromSave(saveNumber);
                        }
                        break;
                    case "deadline":
                        saveNumber++;
                        list.addDeadlineFromSave(taskSave[2], taskSave[3]);
                        if (taskSave[1].equals("1")) {
                            list.markDoneFromSave(saveNumber);
                        }
                        break;
                    case "event":
                        saveNumber++;
                        list.addEventFromSave(taskSave[2], taskSave[3]);
                        if (taskSave[1].equals("1")) {
                            list.markDoneFromSave(saveNumber);
                        }
                        break;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("☹ OOPS!!! I am unable to read the file :-(");
        }

        //main while loop that takes in the user input
        String input = scanner.nextLine().trim();

        while (!input.equals(EXITCOMMAND)) {
            String[] stringTokens = input.split(" ", 2);
            String command = stringTokens[0];
            boolean isValidCommand = false;
            printLine();
            try {
                isValidCommand = isCommand(command);
            } catch (UnrecognizedCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (isValidCommand) {
                switch (command) {
                    case "list":
                        list.printList();
                        break;
                    case "done":
                        try {
                            list.markDone(Integer.parseInt(stringTokens[1]));
                        } catch(ArrayIndexOutOfBoundsException e) {
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty :-(");
                        }
                        break;
                    case "todo":
                        try {
                            list.addTask(stringTokens[1]);
                            saveNumber++;
                        } catch(ArrayIndexOutOfBoundsException e) {
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty :-(");
                        }
                        break;
                    case "deadline":
                        try {
                            list.addDeadline(stringTokens[1]);
                            saveNumber++;
                        } catch(ArrayIndexOutOfBoundsException e) {
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty :-(");
                        }
                        break;
                    case "event":
                        try {
                            list.addEvent(stringTokens[1]);
                            saveNumber++;
                        } catch(ArrayIndexOutOfBoundsException e) {
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty :-(");
                        }
                        break;
                    default:
                        break;
                }
            }
            printLine();
            input = scanner.nextLine().trim();
        }

        //formatting and saving
        try {
            FileWriter saveFileWriter = new FileWriter(TASKLIST_SAVE_PATH);
            for (int i = 0; i < saveNumber; i++) {
                saveFileWriter.write(list.taskType(i) + "\n");
            }
            saveFileWriter.close();
        } catch (java.io.IOException e) {
            System.out.println("☹ OOPS!!! The file can't be saved :-(");
        }
    }

    private static boolean isCommand(String command) throws UnrecognizedCommandException {
        if (commandList.contains(command)) {
            return true;
        }
        throw new UnrecognizedCommandException();
    }

    private static class UnrecognizedCommandException extends Exception {
    }
}
