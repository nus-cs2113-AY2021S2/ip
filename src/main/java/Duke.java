import java.util.Scanner;

public class Duke {

    static final String LINE_DIVIDER = "____________________________________________________________";
    static String by;
    static String at;
    static Task[] tasks = new Task[100];
    static String[] keywords = new String[100];
    static int numberOfTasks = 0;

    public static void printDone(int description) {
        System.out.println(LINE_DIVIDER);
        System.out.println("This task has been done. Good job!");
        tasks[description].printDescription();
        System.out.println("\n" + LINE_DIVIDER);
    }

    public static void printTotalTasks() {
        if (numberOfTasks >= 2) {
            System.out.println(numberOfTasks + " tasks left in the list");
        } else if (numberOfTasks == 1) {
            System.out.println("Only 1 task in the list");
        }
    }

    public static void executeTodo(String command) {
        tasks[numberOfTasks] = new Todo(command.replaceFirst("todo", ""));
        Todo.printTodoDescription();
        keywords[numberOfTasks] = "T";
        tasks[numberOfTasks++].printDescription();
        System.out.print("\n");
        printTotalTasks();
    }

    public static void executeDeadline(String command) {
        try {
            by = command.substring(command.indexOf("/") + 3);
            command = command.substring(8, command.indexOf("/"));
            tasks[numberOfTasks] = new Deadline(command, by);
            Deadline.printDeadlineDescription();
            keywords[numberOfTasks] = "D";
            tasks[numberOfTasks++].printDescription();
            System.out.println("(by:" + by + ")");
            printTotalTasks();
        } catch (IndexOutOfBoundsException e) {
            DukeErrors.deadlineIsEmpty();
        }
    }

    public static void executeEvent(String command) {
        try {
            at = command.substring(command.indexOf("/") + 3);
            command = command.substring(5, command.indexOf("/"));
            tasks[numberOfTasks] = new Event(command, at);
            Event.printEventDescription();
            keywords[numberOfTasks] = "E";
            tasks[numberOfTasks++].printDescription();
            System.out.println("(at:" + at + ")");
            printTotalTasks();
        } catch (IndexOutOfBoundsException e) {
            DukeErrors.eventIsEmpty();
        }
    }

    public static void executeDone(String command) {
        String[] words;
        words = command.split(" ");
        tasks[Integer.parseInt(words[1]) - 1].markAsDone();
        printDone(Integer.parseInt(words[1]) - 1);
    }

    public static void listTasks() throws ArrayIndexOutOfBoundsException {
        if (numberOfTasks == 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 1; i <= numberOfTasks; ++i) {
            System.out.print(i + ".");
            switch (keywords[i - 1]) {
            case "T":
                System.out.print("[T]");
                tasks[i - 1].printDescription();
                System.out.print("\n");
                break;
            case "D":
                System.out.print("[D]");
                tasks[i - 1].printDescription();
                System.out.println("(by:" + by + ")");
                break;
            case "E":
                System.out.print("[E]");
                tasks[i - 1].printDescription();
                System.out.println("(at:" + at + ")");
                break;
            }
        }
    }

    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        UserInterface.printHello();
        command = in.nextLine();
        do {
            // makes the input case-insensitive
            command = command.toLowerCase();
            if (command.equals("list")) {
                try {
                    listTasks();
                } catch (ArrayIndexOutOfBoundsException e) {
                    DukeErrors.listIsEmpty();
                }
            } else if (command.contains("done")) {
                executeDone(command);
            } else if (command.contains("todo")) {
                if (!DukeErrors.checkTodo(command)) {
                    executeTodo(command);
                }
            } else if (command.contains("deadline")) {
                executeDeadline(command);
            } else if (command.contains("event")) {
                executeEvent(command);
            } else {
                DukeErrors.commandIsInvalid();
            }
        command = in.nextLine();
    } while(!command.equals("bye"));
        UserInterface.printBye();
}
}
