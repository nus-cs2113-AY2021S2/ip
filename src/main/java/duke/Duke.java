package duke;

import duke.exception.IllegalTaskCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Duke {
    public static final String FILE_PATH = "Duke_Tasks.txt";

    private static void initialiseWelcomeMessage() {
        String logo
                = "******************************************************\n"
                + "*                 Systems: [Online]                  *\n"
                + "*                Protocol: [Dominion]                *\n"
                + "*                    Race: [Terran]                  *\n"
                + "******************************************************\n";

        String greeting
                = "______________________________________________________\n"
                + "Systems Accessed...\n"
                + "Decrypting Overwrite...\n"
                + "Welcome Commander, can I be of assistance, commander?\n"
                + "______________________________________________________\n";

        System.out.println(logo);
        System.out.println(greeting);
    }

    private static void printGoodbyeMessage() {
        String goodbye
                = "______________________________________________________\n"
                + "Good Bye Commander.\n"
                + "______________________________________________________\n";

        System.out.println(goodbye);
    }

    private static String getCommand(String userInput) {
        String[] inputArray = userInput.split(" ");
        String command = inputArray[0];
        return command.toUpperCase();
    }

    private static void executeCommand(String command, String userInput) throws IllegalTaskCommandException {
        String errand;
        String timestamp;
        String timestampHeader;

        try {
            switch (command) {
            case "TODO":
                errand = getSubstring(userInput);
                Task todo = new Todo(errand);
                todo.addToTaskList(errand, null); // Todo has no timestamp
                break;
            case "EVENT":
                errand = getErrand(userInput);
                timestampHeader = getTimestampHeader(userInput);
                timestamp = getTimestamp(userInput);
                Task event = new Event(errand, timestamp, timestampHeader);
                event.addToTaskList(errand, timestamp);
                break;
            case "DEADLINE":
                errand = getErrand(userInput);
                timestampHeader = getTimestampHeader(userInput);
                timestamp = getTimestamp(userInput);
                Task deadline = new Deadline(errand, timestamp, timestampHeader);
                deadline.addToTaskList(errand, timestamp);
                break;
            case "LIST":
                Task.printList();
                break;
            case "DONE":
                errand = getSubstring(userInput);
                Task.markDone(errand); // In this case, errand is the index of the task
                break;
            case "BYE":
                printGoodbyeMessage();
                break;
            case "DELETE":
                errand = getSubstring(userInput);
                Task.deleteTask(errand); // In this case, errand is the index of the task
                break;
            default:
                throw new IllegalTaskCommandException("Unacceptable Command!");
            }
        } catch (IllegalTaskCommandException e) {
            IllegalTaskCommandException.printErrorLogo();
            System.out.println(e.getMessage());
        }

    }

    private static String getSubstring(String userInput) throws IllegalTaskCommandException {
        String cleanUserInput = userInput.strip(); // Removes any leading and trailing spaces
        int spacePosition = cleanUserInput.indexOf(" ");
        if (spacePosition < 0) { // Is single command
            throw new IllegalTaskCommandException("Insufficient command parameters commander!");
        }
        return cleanUserInput.substring(spacePosition + 1);
    }

    private static String getErrand(String userInput) throws IllegalTaskCommandException {
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        if (slashPosition < 0) {
            throw new IllegalTaskCommandException("Timestamp not found commander!");
        }
        String errand = inputSubstring.substring(0, slashPosition);
        return errand.trim();
    }

    private static String getTimestampHeader(String userInput) throws IllegalTaskCommandException {
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        String timestampHeader = inputSubstring.substring(slashPosition);
        int spacePosition = timestampHeader.indexOf(" ");
        if (timestampHeader.split(" ").length < 2) {
            throw new IllegalTaskCommandException("Missing timestamp commander!");
        }
        timestampHeader = timestampHeader.substring(1, spacePosition);
        return timestampHeader;
    }

    private static String getTimestamp(String userInput) throws IllegalTaskCommandException {
        String inputSubstring = getSubstring(userInput);
        int slashPosition = inputSubstring.indexOf("/");
        String timestamp = inputSubstring.substring(slashPosition);
        int spacePosition = timestamp.indexOf(" ");
        timestamp = timestamp.substring(spacePosition);
        return timestamp.trim();
    }

    /**
     * Starts the Task Manager program.
     * <p>Runs an infinite loop until "BYE" is called</p>
     */
    private static void runTaskManager() throws IllegalTaskCommandException {
        String userInput;
        String command;
        Scanner in = new Scanner(System.in);

        do {
            userInput = in.nextLine();
            command = getCommand(userInput);
            executeCommand(command, userInput);
        } while (!command.equals("BYE"));
    }

    public static void main(String[] args) throws IllegalTaskCommandException, IOException {
        initialiseWelcomeMessage();
        loadTasks();
        runTaskManager();
        saveTasks();
    }

    private static void saveTasks() throws IOException {
        File file = new File(FILE_PATH);
        file.createNewFile();

        FileWriter writer = new FileWriter(file);
        writer.write(Task.convertToFileInput());
        writer.close();
    }

    private static void loadTasks() throws IOException, IllegalTaskCommandException {
        File file = new File(FILE_PATH);

        if (file.exists()) {
            int counter = 1;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String [] fileInput = scanner.nextLine().split(",");
                String commandCode;
                switch (fileInput[0].strip()) {
                case "T":
                    commandCode = "TODO";
                    break;
                case "D":
                    commandCode = "DEADLINE";
                    break;
                case "E":
                    commandCode = "EVENT";
                    break;
                default:
                    commandCode = "";
                    break;
                }
                StringJoiner command = new StringJoiner(" ");
                for (int i = 0; i < fileInput.length; i++) {
                    if (i == 1) {
                        continue; // Skips the done
                    }
                    command.add(fileInput[i].strip());
                }

                executeCommand(commandCode, command.toString());

                if(fileInput[1].strip().equals("X")) {
                    Task.markDone(String.valueOf(counter));
                }
                counter++;
            }
        } else {
            System.out.println("Archive not found, Commander!");
        }
    }

}
