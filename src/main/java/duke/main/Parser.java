package duke.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import duke.exceptions.*;
import duke.items.Deadline;
import duke.items.Event;
import duke.items.Task;
import duke.items.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;


import static duke.main.Storage.createFile;
import static duke.main.Storage.loadFile;
import static duke.main.UI.convertDateFormat;


public class Parser {
    private static final String[] VALID_COMMANDS = {"findBy", "findAt", "find", "bye", "done", "list", "todo", "event", "deadline", "delete"};


    public static int validateCommand(String line){
        if ((Arrays.asList(VALID_COMMANDS).contains(line))) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void validateTodoCommand(String line) throws InvalidParameterLengthExceptions {
        if (line.split(" ").length < 2){
            throw new InvalidParameterLengthExceptions();
        }
    }
    public static int validateDeadlineCommand(String line) throws DeadlineParameterExceptions, InvalidParameterLengthExceptions {
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
    public static int validateEventCommand(String line) throws EventParameterExceptions, InvalidParameterLengthExceptions {
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

    public static int commandHandler(String line) throws InvalidCommandExceptions, InvalidParameterLengthExceptions,
            DeadlineParameterExceptions, EventParameterExceptions, InvalidIndexExceptions, IOException, ParseException {

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
            UI.listPreamble();
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
            UI.listUpdate();
            break;
        case ("find"):
            UI.findPreamble();
            Task.findTask(line.split(" ")[1]);
            break;
        case ("findAt"):
            UI.findPreamble();
            Task.findAt(convertDateFormat(line.split(" ")[1]));
            break;
        case ("findBy"):
            UI.findPreamble();
            Task.findBy(line.split(" ")[1]);
            break;
        case ("todo"):

            validateTodoCommand(line);
            System.out.println("Got it. I've added this task: ");
            Todo todo = new Todo(line.substring(line.indexOf(" ") + 1));
            Task.addTask(todo);
            System.out.print("  ");
            todo.print();
            UI.listUpdate();
            break;

        case ("deadline"):
            int indexOfSlash = validateDeadlineCommand(line);
            System.out.println("Got it. I've added this task: ");
            String item = line.substring(line.indexOf(" ") + 1, indexOfSlash - 1);
            String extra = line.substring(indexOfSlash + 4);
            Deadline deadline = new Deadline(item, convertDateFormat(extra));
            Task.addTask(deadline);
            System.out.print("  ");
            deadline.print();
            UI.listUpdate();
            break;

        case ("event"):
            indexOfSlash = validateEventCommand(line);
            System.out.println("Got it. I've added this task: ");
            item = line.substring(line.indexOf(" ") + 1, indexOfSlash - 1);
            extra = line.substring(indexOfSlash + 4);
            Event event = new Event(item, convertDateFormat(extra));
            Task.addTask(event);
            System.out.print("  ");
            event.print();
            UI.listUpdate();
        }

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
            } catch (ParseException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            UI.printLine();
        }
    }

}

