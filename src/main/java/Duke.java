import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    static ArrayList<Task> tasks = new ArrayList<> ();
    static int tasksCount = 0 ;
    static final int TODOLENGTH = 4;
    static final int EVENTLENGTH = 5;
    static final int DEADLINELENGTH = 8;
    enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    public static void printTaskList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i=0; i<tasks.size(); i++) {
            System.out.println("    " + (i+1) + ". " + tasks.get(i));
        }
    }

    public static void printErrorMsgInvalidInput() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void addDeadline(String userInput) throws DukeException {
        if (userInput.length() == DEADLINELENGTH ) {
            throw new DukeException(TaskType.DEADLINE);
        }
        String description, by;
        userInput = userInput.substring(DEADLINELENGTH+1).trim();
        if (userInput.contains("/")){
            int idx = userInput.indexOf('/');
            description =userInput.substring(0, idx);
            by = userInput.substring(idx+3).trim();
        }
        else {
            description =userInput;
            by = "";
        }

        Deadline d = new Deadline(description, by);
        tasks.add(d);
    }

    public static void addEvent(String userInput) throws DukeException {
        if (userInput.length() == EVENTLENGTH) {
            throw new DukeException(TaskType.EVENT);
        }
        String description, at;
        userInput = userInput.substring(EVENTLENGTH+1).trim();
        if (userInput.contains("/")){
            int idx = userInput.indexOf('/');
            description =userInput.substring(0, idx);
            at = userInput.substring(idx+3).trim();
        }
        else {
            description =userInput;
            at = "";
        }

        Event e = new Event(description, at);
        tasks.add(e);
    }

    public static void addTodo(String userInput) throws DukeException {
        if (userInput.length() == TODOLENGTH) {
            throw new DukeException(TaskType.TODO);
        }
        String description = userInput.substring(TODOLENGTH+1);
        Todo t = new Todo(description);
        tasks.add(t);
    }


    public static void addTask(String userInput) {
        try {
            if (userInput.startsWith("deadline")){
                addDeadline(userInput);
            }
            else if (userInput.startsWith("event")){
                addEvent(userInput);
            }
            else if (userInput.startsWith("todo")){
                addTodo(userInput);
            }
            else {
                printErrorMsgInvalidInput();
                return;
            }
            System.out.println("    Got it. I've added this task: \n      " + tasks.get(tasksCount));
            tasksCount++;
            System.out.println("    Now you have " + tasksCount + " tasks in the list.");
        } catch (DukeException e) {
            e.printErrorTaskCannotBeEmpty();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");

        boolean toContinueAddingTask = true;
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();

        while (toContinueAddingTask) {
            if (task.equalsIgnoreCase("bye")) {
                toContinueAddingTask = false;
                break;
            }
            else if (task.equalsIgnoreCase("list")) {
                printTaskList();
                System.out.println("------------------------------------------");
            }
            else if (task.contains("done")){
                int idx = Integer.parseInt(String.valueOf(task.charAt(5)));
                tasks.get(idx-1).markAsDone();
                System.out.println("------------------------------------------");
                System.out.println("    Nice! I've marked this task as done: ");
                System.out.println("    " + tasks.get(idx-1));
            }
            else {
                addTask(task);
            }
            task = sc.nextLine().trim();
        }
        System.out.println("------------------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
