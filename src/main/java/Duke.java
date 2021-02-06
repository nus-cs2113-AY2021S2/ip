import error.DoneCheckedException;
import error.ListEmptyException;
import error.WrongFormatException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import java.util.Scanner;

public class Duke {
    public static final int MAX_SIZE = 100;
    public static Task[] list = new Task[MAX_SIZE];
    private static int listCounter = 0;

    /**
     * Loop through all possible commands
     * Possible Commands:
     * Todo, event, Deadline, help, list, done, bye
     */
    public static void loop() {
        Scanner line = new Scanner(System.in);
        while (line.hasNextLine()) {
            String input = line.nextLine();
            try{
                if (listCounter>=100) {
                    throw new IndexOutOfBoundsException();
                }
                if (input.equalsIgnoreCase("bye")) {
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
                } else {
                    checkError("INVALID_COMMAND");
                }
            } catch (IndexOutOfBoundsException e) {
                checkError("INDEX_EXCEEDS_LIST");
            }
        }
    }

    /**
     * Main Function
     * To Print Greeting as Default then loop function
     * @param args - argument
     */
    public static void main(String[] args) {
        PrintOutput.printGreeting();
        loop();
    }

    /**
     * Add new task to Timetable
     * @param input - name of task
     */
    public static void addTodo(String input) {
        try{
            if (input.equals("todo")) {
                throw new WrongFormatException();
            }
            String command = input.substring(5);
            if (command.isBlank()) {
                throw new WrongFormatException();
            }
            Todo t = new Todo(command);
            list[listCounter] = t;
            listCounter += 1;
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
    public static void addDeadline(String input) {
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
            list[listCounter] = t;
            listCounter += 1;
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
    public static void addEvent(String input){
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
            list[listCounter] = t;
            listCounter += 1;
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
     * Check respective task as done
     * @param index - index of list
     */
    public static void checkTask(int index){
        try{
            if (list[index].checkIsDone()) {
                throw new DoneCheckedException();
            }
            System.out.println("Good Job, I will mark this as done!");
            list[index].markAsDone();
            System.out.println("[" + list[index].getType() + "] ["
                    + list[index].getStatusIcon() + "] " + list[index].getName()
                    + list[index].getDate());
            PrintOutput.printBorder();
        } catch (DoneCheckedException e) {
            checkError("DONE_CHECKED_ERROR");
        }
    }

    /**
     * Print the number of task in list
     */
    public static void printNoOfTask() {
        System.out.print("You have " + listCounter + " task");
        if (listCounter > 1) {
            System.out.print("s");
        }
        System.out.print(" in total!\n");
        PrintOutput.printBorder();
    }

    /**
     * print List
     */
    public static void printList(){
        try {
            if (!(listCounter>0)) {
                throw new ListEmptyException();
            }
            System.out.println(" LIST");
            for (int i = 0; i < listCounter; i++) {
                System.out.println(i + 1 +  ". [" + list[i].getType() +  "] " + "["
                        +list[i].getStatusIcon() + "] " + list[i].getName()
                        + list[i].getDate());
            }
            PrintOutput.printBorder();
        } catch (ListEmptyException e) {
            checkError("EMPTY_LIST");
        }
    }

    /**
     * Check Error Method
     * Returns error code message
     * @param ERROR_MESSAGE - Error Message
     */
    public static void checkError(String ERROR_MESSAGE) {
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
        case "INVALID_COMMAND":
            System.out.println("¯\\_(ツ)_/¯ That is an invalid command!");
            System.out.println("Enter \"HELP\" for commands!");
            PrintOutput.printBorder();
            break;

        }
    }
}
