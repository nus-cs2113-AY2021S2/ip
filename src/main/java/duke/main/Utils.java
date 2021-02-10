package duke.main;

import java.util.Arrays;

import duke.exceptions.*;
import duke.items.*;

public class Utils {
    private static String[] VALID_COMMANDS = {"done", "list", "todo", "event", "deadline"};

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void welcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.main.Duke\nWhat can I do for you?");
    }

    public static int validateCommand(String line){
        if ((Arrays.asList(VALID_COMMANDS).contains(line))) {
            return 1;
        } else {
            return 0;
        }
    }
    public static int validateParameter(String line) {
        int indexOfSlash = -1;
        if (line.split(" ")[0].equals("deadline")) {
            indexOfSlash = line.indexOf("/by");
        } else if (line.split(" ")[0].equals("event")) {
            indexOfSlash = line.indexOf("/at");
        }
        return indexOfSlash;
    }

    public static int commandHandler(String line) throws InvalidCommandExceptions,InvalidParameterLengthExceptions,
            DeadlineParameterExceptions, EventParameterExceptions, InvalidIndexExceptions {

        String[] arr;
        arr = line.split(" ");
        if (validateCommand(arr[0]) == 0){
            throw new InvalidCommandExceptions();
        }
        switch (arr[0]) {

        case ("bye"):
            return -1;

        case ("list"):
            System.out.println("Here are the tasks in your list:");
            Task.printList();
            break;
        case ("done"):
            Task.setDone(Integer.parseInt(line.split(" ")[1]) - 1);
            break;
        default:
            int indexOfSpace = line.indexOf(" ");
            if (line.split(" ").length < 2){
                throw new InvalidParameterLengthExceptions();
            }
            String type = line.substring(0, indexOfSpace);

            switch (type) {
            case ("todo"):
                System.out.println("Got it. I've added this task: ");
                Task.addTask(new Todo(line.substring(indexOfSpace + 1)));
                System.out.println("  [T][\u2718] " + line.substring(indexOfSpace + 1));
                break;

            case ("deadline"):
                int indexOfSlash = validateParameter(line);
                if (indexOfSlash == -1){
                    throw new DeadlineParameterExceptions();
                }
                System.out.println("Got it. I've added this task: ");
                String item = line.substring(indexOfSpace + 1, indexOfSlash - 1);
                String extra = line.substring(indexOfSlash + 4);
                Task.addTask(new Deadline(item, extra));
                System.out.println("  [D][\u2718] " + item + " (by: " + extra + ")");
                break;

            case ("event"):
                indexOfSlash = validateParameter(line);
                if ( indexOfSlash == -1){
                    throw new EventParameterExceptions();
                }
                System.out.println("Got it. I've added this task: ");
                item = line.substring(indexOfSpace + 1, indexOfSlash - 1);
                extra = line.substring(indexOfSlash + 4);
                Task.addTask(new Event(item, extra));
                System.out.println("  [E][\u2718] " + item + " (at: " + extra + ")");
                break;
            default:
                System.out.println(9);
                throw new InvalidCommandExceptions();
            }
            System.out.println("Now you have " + Task.getNumOfTasks() + " tasks in the list.");
        }
        return 1;
        }

}

