import java.util.Scanner;

public class Duke {
    public static int MAX_SIZE = 100;
    public static Task[] timetable = new Task[MAX_SIZE];
    private static int LIST_COUNTER = 0;
    private static final String BORDER = "============================================================";
    private static final String HELP_MESSAGE = " HELP COMMANDS\n"
        + "Input: adds to timetable\n"
        + " Example: sleep\n\n"
        + "List: view timetable\n"
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
            + "    | === |See you again!";
        printBorder();
        System.out.println("Pleasure serving you...\n" + wave);
        printBorder();
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
                + "many items in your timetable!");
            printBorder();
            break;
        case 3:
            System.out.println("Your timetable is empty! Add something!");
            printBorder();
            break;
        case 4:
            System.out.println("You have already marked it as Done!");
            printBorder();
        }
    }

    /**
     * Loop through all possible commands with error code
     * Error if: Index > listCounter
     * Error if: Timetable is empty and user wants to add something
     * Error if: done command is not followed by an integer
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
                    checkTask(index - 1);
                } else if (input.substring(5).isBlank()) {
                    error(1);
                } else {
                    error(1);
                }
            } else if (input.equalsIgnoreCase("list")) {
                if (LIST_COUNTER == 0) {
                    error(3);
                    continue;
                }
                printTask();
            } else {
                addTask(input);
            }
        }
    }

    /**
     * Read Input
     * @return input as String
     */
    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Add new task to Timetable
     * @param input name of task
     */
    public static void addTask(String input) {
        timetable[LIST_COUNTER] = new Task(input);
        LIST_COUNTER += 1;
        System.out.println("I have added \"" + input + "\" "
            + "to the Timetable!");
        printBorder();
    }

    /**
     * Check task as completed
     * @param index - index of task
     */
    private static void checkTask(int index) {
        if (timetable[index].isDone) {
            error(4);
        } else {
            System.out.println("Good Job, I will mark this as done!");
            timetable[index].markAsDone();
            System.out.println("[" + timetable[index].getStatusIcon() + "] "
                + timetable[index].getName());
            printBorder();
        }
    }

    /**
     * Print out all items
     */
    private static void printTask() {
        System.out.println("TIMETABLE");
        for (int i = 0; i < LIST_COUNTER; i++) {
            System.out.println(i + 1 + ". [" + timetable[i].getStatusIcon() + "] "
                + timetable[i].getName());
        }
        printBorder();
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

}
