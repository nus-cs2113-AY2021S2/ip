package duke.main;

import java.io.FileNotFoundException;
import java.util.Arrays;
import duke.exceptions.*;
import duke.items.*;
import java.io.IOException;
import java.util.Scanner;

import static duke.main.Storage.createFile;
import static duke.main.Storage.loadFile;


public class Parser {
    private static String[] VALID_COMMANDS = {"bye", "done", "list", "todo", "event", "deadline", "delete"};


    public static int validateCommand(String line){
        if ((Arrays.asList(VALID_COMMANDS).contains(line))) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void validateTodoParameter(String line) throws InvalidParameterLengthExceptions {
        if (line.split(" ").length < 2){
            throw new InvalidParameterLengthExceptions();
        }
    }
    public static int validateDeadlineParameter(String line) throws DeadlineParameterExceptions, InvalidParameterLengthExceptions {
        int indexOfSlash;
        if (line.split(" ").length < 4){
            throw new InvalidParameterLengthExceptions();
        }
        indexOfSlash = line.indexOf("/by");
        if (indexOfSlash == -1) {
            throw new DeadlineParameterExceptions();
        }
        return indexOfSlash;
    }
    public static int validateEventParameter(String line) throws EventParameterExceptions, InvalidParameterLengthExceptions {
        int indexOfSlash;
        if (line.split(" ").length < 4){
            throw new InvalidParameterLengthExceptions();
        }
        indexOfSlash = line.indexOf("/at");
        if (indexOfSlash == -1){
            throw new EventParameterExceptions();
        }
        return indexOfSlash;
    }

    public static int commandHandler(String line) throws InvalidCommandExceptions,InvalidParameterLengthExceptions,
            DeadlineParameterExceptions, EventParameterExceptions, InvalidIndexExceptions, IOException {

        String[] arr;
        arr = line.split(" ");
        Storage store = new Storage();
        if (validateCommand(arr[0]) == 0){
            throw new InvalidCommandExceptions();
        }
        switch (arr[0]) {

        case ("bye"):
            store.writeToFile();
            return -1;

        case ("list"):
            UI.list();
            Task.printList();
            break;
        case ("done"):
            Task.setDone(Integer.parseInt(line.split(" ")[1]) - 1);
            break;
        case ("delete"):
            int indexToDelete = Integer.parseInt(line.split(" ")[1]) - 1;
            if (indexToDelete >= Task.getNumOfTasks()){
                throw new InvalidIndexExceptions();
            }
            Task.deleteTask(Integer.parseInt(line.split(" ")[1]) - 1);
            break;
        case ("todo"):

            int indexOfSpace = line.indexOf(" ");
            validateTodoParameter(line);
            System.out.println("Got it. I've added this task: ");
            Task.addTask(new Todo(line.substring(line.indexOf(" ") + 1)));
            System.out.println("  [T][\u2718] " + line.substring(indexOfSpace + 1));
            break;

        case ("deadline"):
            int indexOfSlash = validateDeadlineParameter(line);
            System.out.println("Got it. I've added this task: ");
            String item = line.substring(line.indexOf(" ") + 1, indexOfSlash - 1);
            String extra = line.substring(indexOfSlash + 4);
            Task.addTask(new Deadline(item, extra));
            System.out.println("  [D][\u2718] " + item + " (by: " + extra + ")");
            break;

        case ("event"):
            indexOfSlash = validateEventParameter(line);
            System.out.println("Got it. I've added this task: ");
            item = line.substring(line.indexOf(" ") + 1, indexOfSlash - 1);
            extra = line.substring(indexOfSlash + 4);
            Task.addTask(new Event(item, extra));
            System.out.println("  [E][\u2718] " + item + " (at: " + extra + ")");
            break;
        }
        UI.listUpdate();

        return 1;
        }

    public static void fileHandling(){
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            createFile();
        }
    }

    public static void run(){
        Scanner in = new Scanner(System.in);
        while (true) {
            String line;
            line = in.nextLine();
            UI.printLine();
            try {
                if (commandHandler(line) == -1) {
                    break;
                }
            } catch (InvalidCommandExceptions e) {
                UI.InvalidCommandErrorMessage();
            } catch (InvalidParameterLengthExceptions e){
                UI.InvalidParameterLengthErrorMessage(line);
            } catch (EventParameterExceptions e){
                UI.EventParameterErrorMessage();
            } catch (DeadlineParameterExceptions e){
                UI.DeadlineParameterErrorMessage();
            } catch (InvalidIndexExceptions e){
                UI.InvalidIndexErrorMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            UI.printLine();
        }
    }

}

