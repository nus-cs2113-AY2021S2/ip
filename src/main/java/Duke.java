import java.util.Scanner;

public class Duke {
    public static Task[] Tasks = new Task[100];

    public static void main(String[] args) {
        printGreeting();
        printLine();
        loopAddListAndMark();
        sayGoodbye();
    }

    /**
     * Loops through all features available.
     * Returns if user types bye or number of tasks exceeds 100
     */
    public static void loopAddListAndMark() {
        while(true) {
            if(Task.totalTasks > 100) {
                System.out.println("Maximum capacity reached");
                return;
            }
            String line = getInput();
            if(line.equalsIgnoreCase("bye")) {
                return;
            }
            // If user wants to mark a task as done
            if(line.substring(0, 4).equalsIgnoreCase("done")) {
                // Check validity of input
                if(isValidMark(line)) {
                    int listNum = Integer.parseInt(line.substring(5));
                    // Check for illegal access to out of bounds index
                    if(listNum > Task.totalTasks) {
                        System.out.println("Woah woah. Don't violate the rules aight. I'm watching you.");
                        printLine();
                        continue;
                    }
                    listNum -= 1;
                    Tasks[listNum].setDone();
                    System.out.println(Tasks[listNum].name + " set to done. You're well smart innit?");
                } else {
                    System.out.println("Aye aye that's the wrong format homie. " +
                            "To set a task to done, type done <number>.");
                }
                printLine();
                continue;
            }
            // If user wants to list all tasks
            if(line.equalsIgnoreCase("list")) {
                listItems();
            } else {
                // If user wants to add an item
                addItem(line);
            }
        }
    }

    /**
     * Lists all items added to the list
     */
    public static void listItems() {
        System.out.println("Eez are the tings you added to the list");
        for(int i = 0; i < Task.totalTasks; i++) {
            System.out.println(i+1 + ".[" + Tasks[i].getStatusIcon() + "] " + Tasks[i].name);
        }
        printLine();
    }

    /**
     * Append new item to back of the list
     * @param nameOfTask name of new item
     */
    public static void addItem(String nameOfTask) {
        int current = Task.totalTasks;
        Tasks[current] = new Task(nameOfTask);
        System.out.println("Wawaweewah! Added: " + nameOfTask);
        Task.totalTasks += 1;
        printLine();
    }

    /**
     * Checks if input is of valid format.
     * Format should be "done<space><number>.
     * @param line input string
     * @return true if line is valid format, false otherwise
     */
    public static boolean isValidMark(String line) {
        return line.substring(5).matches("[0-9]+");
    }
    public static void printGreeting() {
        System.out.println("Wagwan! I is Ali G. West side.");
        System.out.println("What is we chattin bout today?");
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void sayGoodbye() {
        System.out.println("Goodbye, big up yourself, keep it real, respekt.");
        printLine();
    }
    public static String getInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
