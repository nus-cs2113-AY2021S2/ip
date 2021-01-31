import java.util.Scanner;

public class Duke {
    public static int MAX_SIZE = 100;
    public static Task[] list = new Task[MAX_SIZE];
    private static int LIST_COUNTER = 0;

    /**
     * Loop through all possible commands
     * Possible Commands:
     * Todo, event, Deadline, help, list, done, bye
     */
    public static void loop() {
        Scanner line = new Scanner(System.in);
        while (line.hasNextLine()) {
            String input = line.nextLine();
            if(LIST_COUNTER == 100){
                error(6);
                return;
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
            } else if (input.toLowerCase().startsWith("todo")){
                addTodo(input);
            } else if (input.toLowerCase().startsWith("deadline")){
                addDeadline(input);
            } else if (input.toLowerCase().startsWith("event")){
                addEvent(input);
            } else {
                System.out.println("¯\\_(ツ)_/¯ That is an invalid command!");
                System.out.println("Enter \"HELP\" for commands!");
                PrintOutput.printBorder();
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
        if(input.equals("todo")){
            error(5);
            return;
        }
        String command = input.substring(5);
        if(!command.isBlank()){
            Todo t = new Todo(command);
            list[LIST_COUNTER] = t;
            LIST_COUNTER += 1;
            System.out.println("I have added [" + t.getType() + "]["
                + t.getStatusIcon() + "] \""
                + t.getName() + "\" " + "to the List!");
            printNoOfTask();
        } else{
            error(5);
        }
    }

    /**
     * Add deadline to list
     * @param input - add Deadline
     */
    public static void addDeadline(String input) {
        if(input.equals("deadline")){
            error(5);
            return;
        }
        String command = input.substring(9);
        if(command.contains(" /by ")) {
            String[] parts = command.split(" /by ");
            String description = parts[0];
            String date = parts[1];
            Deadline t = new Deadline(description,date);
            list[LIST_COUNTER] = t;
            LIST_COUNTER += 1;
            System.out.println("I have added [" + t.getType() + "]["
                + t.getStatusIcon() + "] \""
                + t.getName() + t.getDate() + "\"" + " to the List!");
            printNoOfTask();
        } else {
            error(5);
        }
    }

    /**
     * Add event to list
     * @param input - add event
     */
    public static void addEvent(String input){
        if(input.equals("event")){
            error(5);
            return;
        }
        String command = input.substring(6);
        if(command.contains (" /at ")){
            String[] parts = command.split(" /at ");
            String description = parts[0];
            String date = parts[1];
            Event t = new Event(description,date);
            list[LIST_COUNTER] = t;
            LIST_COUNTER += 1;
            System.out.println("I have added [" + t.getType() + "]["
                + t.getStatusIcon() + "] \""
                + t.getName() + t.getDate() + "\"" + " to the List!");
            printNoOfTask();
        } else {
            error(5);
        }
    }

    /**
     * Check if done command is valid
     * @param input - index of task
     */
    private static void doneTask(String input) {
        if (input.equalsIgnoreCase("done")) {
            error(1);
            return;
        }
        if (input.substring(5).matches("[0-9]+")) {
            int index = Integer.parseInt(input.substring(5));
            if (index > LIST_COUNTER) {
                error(2);
                return;
            }
            checkTask(index - 1);
        } else if (input.substring(5).isBlank()) {
            error(1);
        } else {
            error(1);
        }
    }

    /**
     * Check respective task as done
     * @param index - index of list
     */
    public static void checkTask(int index){
        if (list[index].isDone) {
            error(4);
        } else {
            System.out.println("Good Job, I will mark this as done!");
            list[index].markAsDone();
            System.out.println("[" + list[index].getType() + "] ["
                + list[index].getStatusIcon() + "] " + list[index].getName()
                + list[index].getDate());
            PrintOutput.printBorder();
        }
    }

    /**
     * Print the number of task in list
     */
    public static void printNoOfTask() {
        System.out.print("You have " + LIST_COUNTER + " task");
        if(LIST_COUNTER > 1){
            System.out.print("s");
        }
        System.out.print(" in total!\n");
        PrintOutput.printBorder();
    }

    /**
     * print List
     */
    public static void printList(){
        if (LIST_COUNTER > 0) {
            System.out.println(" LIST");
            for (int i = 0; i < LIST_COUNTER; i++) {
                System.out.println(i + 1 +  ". [" + list[i].getType() +  "] " + "["
                    +list[i].getStatusIcon() + "] " + list[i].getName()
                    + list[i].getDate());
            }
            PrintOutput.printBorder();
        } else {
            error(3);
        }
    }


    /**
     * Returns error code message
     * Error Code 1: done command in wrong format
     * Error Code 2: done Index is more than size of array
     * Error Code 3: Timetable is empty
     * Error Code 4: Task already marked done
     * Error Code 5: Invalid Command format
     * @param code - error code
     */
    public static void error(int code) {
        switch (code) {
            case 1:
                System.out.println("Error! You must enter an integer after"
                    + " \"done\"!");
                PrintOutput.printBorder();
                break;
            case 2:
                System.out.println("Error! You do not have that "
                    + "many items in your list!");
                PrintOutput.printBorder();
                break;
            case 3:
                System.out.println("Your list is empty! Add something!");
                PrintOutput.printBorder();
                break;
            case 4:
                System.out.println("You have already marked it as Done!");
                PrintOutput.printBorder();
                break;
            case 5:
                System.out.println("¯\\_(ツ)_/¯ That is an invalid format!");
                System.out.println("Enter HELP for commands!");
                PrintOutput.printBorder();
                break;
            case 6:
                System.out.println("List is full!");
                PrintOutput.printBorder();
                break;
        }
    }
}
