import error.DoneCheckedException;
import error.ListEmptyException;
import error.WrongFormatException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final ArrayList<Task> list = new ArrayList<>();
    private static int listCounter = 0;

    /**
     * Loop through all possible commands
     * Possible Commands:
     * Todo, event, Deadline, help, list, done, bye
     */
    private static void loop() {
        Scanner line = new Scanner(System.in);
        while (line.hasNextLine()) {
            String input = line.nextLine();
            try{
                if (listCounter>=100) {
                    throw new IndexOutOfBoundsException();
                }
                if (input.equalsIgnoreCase("bye")) {
                    saveFile();
                    PrintOutput.printExit();
                    System.exit(1);
                } else if (input.equalsIgnoreCase("help")) {
                    System.out.println(PrintOutput.HELP_MESSAGE);
                } else if (input.toLowerCase().startsWith("done")) {
                    doneTask(input);
                } else if (input.equalsIgnoreCase("list")) {
                    printList();
                } else if (input.toLowerCase().startsWith("todo")) {
                    addTodo(input);
                } else if (input.toLowerCase().startsWith("deadline")) {
                    addDeadline(input);
                } else if (input.toLowerCase().startsWith("event")) {
                    addEvent(input);
                } else if (input.toLowerCase().startsWith("delete")){
                    deleteTask(input);
                } else {
                    checkError("INVALID_COMMAND");
                }
            } catch (IndexOutOfBoundsException | IOException e) {
                checkError("LIST_FULL");
            }
        }
    }

    /**
     * Main Function
     * Loads Duke.txt File to Start Program and loop through commands
     * Error if Duke.txt Not Found
     * @param args - argument
     */
    public static void main(String[] args) {
        PrintOutput.printGreeting();
        try {
            loadFile();
            System.out.println("Duke.txt successfully loaded!");
            PrintOutput.printBorder();
            loop();
        } catch (FileNotFoundException e) {
            checkError("FILE_NOT_FOUND");
        }

    }

    /**
     * Add new task to Timetable
     * @param input - name of task
     */
    private static void addTodo(String input) {
        try{
            if (input.equals("todo")) {
                throw new WrongFormatException();
            }
            String command = input.substring(5);
            if (command.isBlank()) {
                throw new WrongFormatException();
            }
            Todo t = new Todo(command);
            list.add(t);
            listCounter ++;
            System.out.println("I have added [" + t.getType() + "]["
                    + t.getStatusIcon() + "] \""
                    + t.getName() + "\" " + "to the List!");
            printNoOfTask();
        } catch(WrongFormatException e) {
            checkError("INVALID_FORMAT");
        }
    }

    /**
     * Add deadline to list
     * @param input - add Deadline
     */
    private static void addDeadline(String input) {
        try{
            if (input.equals("deadline")) {
                throw new WrongFormatException();
            }
            String command = input.substring(9);
            if (!command.contains(" /by ")) {
                throw new WrongFormatException();
            }
            String[] parts = command.split(" /by ");
            String description = parts[0];
            String date = parts[1];
            Deadline t = new Deadline(description,date);
            list.add(t);
            listCounter ++;
            System.out.println("I have added [" + t.getType() + "]["
                    + t.getStatusIcon() + "] \""
                    + t.getName() + t.getDate() + "\"" + " to the List!");
            printNoOfTask();
        } catch (WrongFormatException e) {
            checkError("INVALID_FORMAT");
        }
    }

    /**
     * Add event to list
     * @param input - add event
     */
    private static void addEvent(String input){
        try {
            if (input.equals("event")) {
                throw new WrongFormatException();
            }
            String command = input.substring(6);
            if (!command.contains(" /at ")) {
                throw new WrongFormatException();
            }
            String[] parts = command.split(" /at ");
            String description = parts[0];
            String date = parts[1];
            Event t = new Event(description,date);
            list.add(t);
            listCounter ++;
            System.out.println("I have added [" + t.getType() + "]["
                    + t.getStatusIcon() + "] \""
                    + t.getName() + t.getDate() + "\"" + " to the List!");
            printNoOfTask();
        } catch (WrongFormatException e) {
            checkError("INVALID_FORMAT");
        }
    }

    /**
     * Check if done command is valid
     * @param input - index of task
     */
    private static void doneTask(String input) {
        try{
            if (input.equalsIgnoreCase("done")) {
                throw new WrongFormatException();
            }
            if (!input.substring(5).matches("[0-9]+")) {
                throw new WrongFormatException();
            }
            int index = Integer.parseInt(input.substring(5));
            if (index > listCounter) {
                throw new IndexOutOfBoundsException();
            }
            checkTask(index - 1);
        } catch (IndexOutOfBoundsException e) {
            checkError("INDEX_EXCEEDS_LIST");
        } catch (WrongFormatException e) {
            checkError("WRONG_DONE_FORMAT");
        }
    }

    /**
     * Delete task from the list
     * @param input - Command
     */
    private static void deleteTask(String input){
        try {
            if (input.equalsIgnoreCase("delete")) {
                throw new WrongFormatException();
            }
            if (input.substring(7).matches("0")) {
                throw new WrongFormatException();
            }
            if (!input.substring(7).matches("[0-9]+")) {
                throw new WrongFormatException();
            }
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index + 1 > listCounter) {
                throw new IndexOutOfBoundsException();
            }

            System.out.println("Noted! I will delete this at once!");
            System.out.println("[" + list.get(index).getType() + "] ["
                    + list.get(index).getStatusIcon() + "] " + list.get(index).getName()
                    + list.get(index).getDate());
            list.remove(index);
            listCounter--;
            printNoOfTask();
        } catch (WrongFormatException e) {
            checkError("WRONG_DELETE_FORMAT");
        } catch (IndexOutOfBoundsException e){
            checkError("DELETE_EMPTY_LIST");
        }
    }

    /**
     * Check respective task as done
     * @param index - index of list
     */
    private static void checkTask(int index){
        try{
            if (list.get(index).checkIsDone()) {
                throw new DoneCheckedException();
            }
            System.out.println("Good Job, I will mark this as done!");
            list.get(index).markAsDone();
            System.out.println("[" + list.get(index).getType() + "] ["
                    + list.get(index).getStatusIcon() + "] " + list.get(index).getName()
                    + list.get(index).getDate());
            PrintOutput.printBorder();
        } catch (DoneCheckedException e) {
            checkError("DONE_CHECKED_ERROR");
        }
    }


    /**
     * Print the number of task in list
     */
    private static void printNoOfTask() {
        System.out.print("You have " + listCounter + " task");
        if (listCounter > 1) {
            System.out.print("s");
        }
        System.out.print(" in total!\n");
        PrintOutput.printBorder();
    }

    /**
     * Print List
     */
    private static void printList(){
        try {
            if (!(listCounter>0)) {
                throw new ListEmptyException();
            }
            System.out.println(" LIST");
            for (int i = 0; i < listCounter; i++) {
                System.out.println(i + 1 +  ". [" + list.get(i).getType() +  "] " + "["
                        + list.get(i).getStatusIcon() + "] " + list.get(i).getName()
                        + list.get(i).getDate());
            }
            PrintOutput.printBorder();
        } catch (ListEmptyException e) {
            checkError("EMPTY_LIST");
        }
    }

    /**
     * Method to load Duke.txt
     * Throws Exception if Duke.txt not Found
     * @throws FileNotFoundException - File Not Found Exception
     */
    private static void loadFile() throws FileNotFoundException {
        File file = new File("Duke.txt");
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        Scanner line = new Scanner(file);
        while (line.hasNext()) {
            String input = line.nextLine();
            if (input.toLowerCase().substring(4).startsWith("todo")) {
                importTodo(input);
            } else if (input.toLowerCase().substring(4).startsWith("deadline")) {
                importDeadline(input);
            } else if (input.toLowerCase().substring(4).startsWith("event")) {
                importEvent(input);
            }
        }
    }

    /**
     * Imports todo from Duke.txt to Program
     * @param input - Command
     */
    private static void importTodo(String input) {
        try{
            if (input.substring(4).equals("todo")) {
                throw new WrongFormatException();
            }
            String command = input.substring(9);
            if (command.isBlank()) {
                throw new WrongFormatException();
            }
            Todo t = new Todo(command);
            list.add(t);
            listCounter ++;
            if (input.charAt(1) == '\u2713') {
                t.markAsDone();
            }
            System.out.println("Imported \"Todo: " + t.getName()
                    + "\" from Duke.txt!");
            PrintOutput.printBorder();
        } catch(WrongFormatException e) {
            checkError("INVALID_FORMAT");
        }
    }

    /**
     * Imports Deadline from Duke.txt to Program
     * @param input - Command
     */
    private static void importDeadline(String input) {
        try{
            if (input.substring(4).equals("deadline")) {
                throw new WrongFormatException();
            }
            String command = input.substring(13);
            if (!command.contains(" /by ")) {
                throw new WrongFormatException();
            }
            String[] parts = command.split(" /by ");
            String description = parts[0];
            String date = parts[1];
            Deadline t = new Deadline(description,date);
            list.add(t);
            listCounter ++;
            if (input.charAt(1) == '\u2713') {
                t.markAsDone();
            }
            System.out.println("Imported \"Deadline: " + t.getName()
                    + "\" from Duke.txt!");
            PrintOutput.printBorder();
        } catch (WrongFormatException e) {
            checkError("INVALID_FORMAT");
        }
    }

    /**
     * Imports Event from Duke.txt to Program
     * @param input - Command
     */
    private static void importEvent(String input) {
        try {
            if (input.equals("event")) {
                throw new WrongFormatException();
            }
            String command = input.substring(10);
            if (!command.contains(" /at ")) {
                throw new WrongFormatException();
            }
            String[] parts = command.split(" /at ");
            String description = parts[0];
            String date = parts[1];
            Event t = new Event(description, date);
            list.add(t);
            listCounter++;
            if (input.charAt(1) == '\u2713') {
                t.markAsDone();
            }
            System.out.println("Imported \"Event: " + t.getName()
                    + "\" from Duke.txt!");
            PrintOutput.printBorder();
        } catch (WrongFormatException e) {
            checkError("INVALID_FORMAT");
        }
    }

    /**
     * Method to update Duke.txt
     * Throws Exception if Duke.txt not found
     * @throws IOException - IO Exception Error
     */
    private static void saveFile() throws IOException {
        File file = new File("Duke.txt");
        if (!file.exists()){
            if(!file.createNewFile()) {
                throw new IOException();
            }
        }
        FileWriter fw = new FileWriter("Duke.txt");
        for(int i = 0; i< listCounter; i++) {
            fw.write(list.get(i).outputData());
            fw.write("\n");
        }
        fw.close();
    }

    /**
     * Check Error Method
     * Returns error code message
     * @param ERROR_MESSAGE - Error Message
     */
    private static void checkError(String ERROR_MESSAGE) {
        switch (ERROR_MESSAGE) {
        case "WRONG_DONE_FORMAT":
            System.out.println("Error! You must enter an integer after"
                    + " \"done\"!");
            PrintOutput.printBorder();
            break;
        case "INDEX_EXCEEDS_LIST":
            System.out.println("Error! You do not have that "
                    + "many items in your list!");
            PrintOutput.printBorder();
            break;
        case "EMPTY_LIST":
            System.out.println("Your list is empty! Add something!");
            PrintOutput.printBorder();
            break;
        case "DONE_CHECKED_ERROR":
             System.out.println("You have already marked it as Done!");
             PrintOutput.printBorder();
             break;
        case "INVALID_FORMAT":
             System.out.println("¯\\_(ツ)_/¯ That is an invalid format!");
             System.out.println("Enter HELP for commands!");
             PrintOutput.printBorder();
             break;
        case "LIST_FULL":
            System.out.println("List is full!");
            PrintOutput.printBorder();
            break;
        case "WRONG_DELETE_FORMAT":
            System.out.println("Error! You must enter a valid integer after"
                    + " \"delete\"!");
            PrintOutput.printBorder();
            break;
        case "DELETE_EMPTY_LIST":
            System.out.println("Error! You cannot delete what does not exist!");
            PrintOutput.printBorder();
            break;
        case "INVALID_COMMAND":
            System.out.println("¯\\_(ツ)_/¯ That is an invalid command!");
            System.out.println("Enter \"HELP\" for commands!");
            PrintOutput.printBorder();
            break;
        case "FILE_NOT_FOUND":
            System.out.println("Duke.txt not found in directory. "
                    + "Please try again!");
            PrintOutput.printBorder();
        }
    }
}
