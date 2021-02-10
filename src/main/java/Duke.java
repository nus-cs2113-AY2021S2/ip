import java.util.Scanner;

public class Duke {
    static String sectionDivider = "____________________________________________________________";
    private static void greetUser(){
        String greeting = "\t" + sectionDivider + "\n"
                + "\tHello! I'm Duke. \n"
                + "\tWhat can I do for you? \n"
                + "\t" + sectionDivider;
        System.out.println(greeting);
    }
    private static void signOff(){
        String sign_off = "\t" + sectionDivider + "\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t" + sectionDivider;
        System.out.println(sign_off);
    }
    private static final String COMMAND_WORDS = "list" + "done" + "todo" + "deadline" + "event";

    static int numberOfTasks = 0;
    static Task tasks[] = new Task[100];

    public static void addTask(String taskDescription) throws
            MissingDeadlineException, MissingEventTimeException {
        Task t;
        if (taskDescription.contains("todo")) {
            if (taskDescription.length() < 5) {
                throw new IndexOutOfBoundsException();
            }
            t = new Todo(taskDescription.substring(5));
            System.out.println("\tGot it. I've added this task: ");
        } else if (taskDescription.contains("deadline")) {
            // Adds task as a Deadline
            taskDescription.replace("deadline ", "");
            int byIndex = taskDescription.indexOf("/by ");
            if (byIndex == -1 || taskDescription.length() < byIndex + 4) {
                throw new MissingDeadlineException();
            } else {
                t = new Deadline(taskDescription.substring(9, byIndex - 1), taskDescription.substring(byIndex + 4));
            }
        } else {
            // Adds task as an Event.
            taskDescription.replace("event ", "");
            int atIndex = taskDescription.indexOf("/at ");
            if (atIndex == -1 || taskDescription.length() < atIndex + 4) {
                throw new MissingEventTimeException();
            } else {
                t = new Event(taskDescription.substring(6, atIndex - 1), taskDescription.substring(atIndex + 4));
            }
        }
        tasks[numberOfTasks] = t;
        System.out.println("\t" + t);
        numberOfTasks++;
        System.out.println("\tNow you have " + numberOfTasks + " tasks in the list.");
    }

    public static void markTaskAsDone(String taskDescription){
        // Marks one of the tasks as done.
        System.out.println("\t Nice! I've marked this task as done: ");
        String[] input = taskDescription.split(" ");
        int taskIndex = Integer.parseInt(input[1]);
        tasks[taskIndex-1].markAsDone();
        String output = String.format("%02d. [%s] %s", taskIndex, tasks[taskIndex-1].getStatusIcon(), tasks[taskIndex-1].getDescription());
        System.out.println("\t" + output);
    }

    public static void listTasks(){
        // Lists out the things under tasks.
        System.out.println("\t Here are the tasks in your list:");
        for(int i= 0; i!=numberOfTasks; i++){
            String output = String.format("%02d. %s", i+1, tasks[i]);
            System.out.println("\t" + output);
        }
    }

    public static void main(String[] args) {
        greetUser();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(line.toLowerCase().equals("bye") != true){
            System.out.println("\t" + sectionDivider);
            try {
                handleInput(line);
            } catch (IllegalCommandWordException e){
                System.out.print("\tInvalid input! Please start with a valid command word!\n");
            } finally {
                System.out.println("\t" + sectionDivider);
                line = in.nextLine();
            }
        }
        signOff();
    }

    private static void handleInput(String line) throws IllegalCommandWordException {
        line = line.trim();
        String[] wordsEntered = line.split(" ");
        if (!COMMAND_WORDS.contains(wordsEntered[0].toLowerCase())) {
            throw new IllegalCommandWordException();
        } else if (line.toLowerCase().equals("list")){
            listTasks();
        } else if (line.toLowerCase().contains("done")){
            markTaskAsDone(line);
        } else {
            try {
                addTask(line);
            } catch (MissingDeadlineException e) {
                System.out.print("\tSorry, please indicate a valid deadline!\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.print("\tSorry, please check if sufficient inputs are given.\n" +
                        "\tTODO: details\n" +
                        "\tDeadline: details, deadline\n" +
                        "\tEvent: details, time of event\n");
            } catch (MissingEventTimeException e) {
                System.out.print("\tSorry, please indicate a valid event time!\n");
            }
        }
    }
}
