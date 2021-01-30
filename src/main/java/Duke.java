import java.util.Scanner;

public class Duke {
    public static int MAX_SIZE = 100;
    public static Task[] list = new Task[MAX_SIZE];
    private static int LIST_COUNTER = 0;

    /**
     * Loop through all possible commands with error code
     * Possible Commands:
     * Todo, event, Deadline, help, list, done, bye
     */
    public static void loop() {
        while (true) {
            String input = readInput();
            if (input.equalsIgnoreCase("bye")) {
                printExit();
                System.exit(1);
            } else if (input.equalsIgnoreCase("help")) {
                System.out.println(HELP_MESSAGE);
            } else if (input.toLowerCase().startsWith("done")) {
                if (input.equalsIgnoreCase("done")) {
                    error(1);
                    continue;
                }
                if (input.substring(5).matches("[0-9]+")) {
                    int index = Integer.parseInt(input.substring(5));
                    if (index > LIST_COUNTER) {
                        error(2);
                        continue;
                    }
                    doneTask(index - 1);
                } else if (input.substring(5).isBlank()) {
                    error(1);
                } else {
                    error(1);
                }
            } else if (input.equalsIgnoreCase("list")) {
                printList();
            } else if (input.toLowerCase().startsWith("todo")){
                addTodo(input.substring(5));
            } else if (input.toLowerCase().startsWith("deadline")){
                addDeadline(input.substring(9));
            } else if (input.toLowerCase().startsWith("event")){
                addEvent(input.substring(6));
            } else {
                System.out.println(" ¯\\_(ツ)_/¯ You have an Invalid Command!");
                System.out.println("Enter \"HELP\" for help!");
                printBorder();
            }
        }
    }

    /**
     * Main Function
     * To Print Greeting as Default then loop function
     * @param args - argument
     */
    public static void main(String[] args) {
        printGreeting();
        loop();
    }

    /**
     * Add new task to Timetable
     * @param command - name of task
     */
    public static void addTodo(String command) {
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
     * @param command - add Deadline
     */
    public static void addDeadline(String command) {
        if(command.contains("/by")) {
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
     * @param command - add event
     */
    public static void addEvent(String command){
        if(command.contains ("/at")){
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
     * Check task as completed
     * @param index - index of task
     */
    private static void doneTask(int index) {
        if (list[index].isDone) {
            error(4);
        } else {
            System.out.println("Good Job, I will mark this as done!");
            list[index].markAsDone();
            System.out.println("[" + list[index].getType() + "] ["
                + list[index].getStatusIcon() + "] " + list[index].getName()
                + list[index].getDate());
            printBorder();
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
        printBorder();
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
            printBorder();
        } else {
            error(3);
        }
    }

    private static final String BORDER = "============================================================";
    private static final String HELP_MESSAGE = " HELP COMMANDS\n"
        + "todo: adds to list\n"
        + " Example: todo sleep\n\n"
        + "deadline: adds to list\n"
        + " Example: deadline homework /by tomorrow\n\n"
        + "event: adds to list\n"
        + " Example: event block event /at tuesday\n\n"
        + "List: view list\n"
        + " Example: list\n\n"
        + "Help: view help commands\n"
        + " Example: Help\n\n"
        + "Done: check task as done\n"
        + " Example: Done 2\n\n"
        + "Bye:  terminate program\n"
        + " Example: bye\n\n"
        + BORDER;

    public static void printGreeting() {
        String alfred = "⊂_ヽ\n"
            + "　 ＼ ＼ what\n"
            + "　　  ＼ ( ͡° ͜ʖ ͡°) can I \n"
            + "　　　 >　   ⌒    ヽ\n"
            + "　　　/ 　   へ      ＼\n"
            + "　　/　　  /　  ＼   do for you?\n"
            + "　　(　  (ヽ　　    ヽ _   つ\n"
            + "   |  　| \\ \n"
            + "　  | 丿 ＼ ⌒)\n"
            + "　  | |　　) /\n"
            + "   ノ )　　Lﾉ \n"
            + "  (_／";
        printBorder();
        System.out.println("Good Day, I'm Alfred.\n" + alfred);
        System.out.println("Enter HELP for commands");
        printBorder();
    }

    public static void printExit() {
        String wave = " __      __\n"
            + "( _\\    /_ )\n"
            + " \\ _\\  /_ / \n"
            + "  \\ _\\/_ /_ _\n"
            + "  |_____/_/ /|\n"
            + "  (  (_)__)J-)\n"
            + "  (  /`.,   /\n"
            + "   \\/  ;   /\n"
            + "    | === |See you again!"
            + "Developed by: Oscar Lai\n"
            + "Version 1.4";
        printBorder();
        System.out.println("Pleasure serving you...\n" + wave);
        printBorder();
    }

    /**
     * Read Input
     * @return input as String
     */
    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void printBorder() {
        System.out.println(BORDER);
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
                printBorder();
                break;
            case 2:
                System.out.println("Error! You do not have that "
                    + "many items in your list!");
                printBorder();
                break;
            case 3:
                System.out.println("Your list is empty! Add something!");
                printBorder();
                break;
            case 4:
                System.out.println("You have already marked it as Done!");
                printBorder();
                break;
            case 5:
                System.out.println("Invalid format!");
                System.out.println("Enter HELP for commands!");
                printBorder();
                break;
        }
    }
}
