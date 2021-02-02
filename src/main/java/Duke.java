import java.util.Scanner;

public class Duke {

    static String by;
    static String at;
    static Task[] tasks = new Task[100];
    static String[] keyword = new String[100];
    static int numberOfTasks = 0;

    public static void printBye() {
        System.out.println("____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void printHello() {
        System.out.println("____________________________________________________________");
        System.out.println("    Hi! I'm Duke (:");
        System.out.println("    What can I do for you today?");
        System.out.println("____________________________________________________________");
    }

    public static void printDone(int description) {
        System.out.println("____________________________________________________________");
        System.out.println("This task has been done. Good job!");
        tasks[description].printDescription();
        System.out.println("\n" + "____________________________________________________________");
    }

    public static void printTotalTasks() {
        if (numberOfTasks >= 2) {
            System.out.println(numberOfTasks + " tasks left in the list");
        } else if (numberOfTasks == 1) {
            System.out.println("Only 1 task in the list");
        }
    }

    public static void listTask() {
        for (int i = 1; i <= numberOfTasks; ++i) {
            System.out.print(i + ".");
            switch (keyword[i - 1]) {
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

        public static void main (String[]args){
            String command;
            String[] words;

            Scanner in = new Scanner(System.in);
            printHello();
            command = in.nextLine();
            do {
                if (command.equals("list")) {
                    listTask();
                } else {
                    if (command.contains("done")) {
                        words = command.split(" ");
                        tasks[Integer.parseInt(words[1])-1].markAsDone();
                        printDone(Integer.parseInt(words[1])-1);
                    } else if (command.contains("todo")) {
                        tasks[numberOfTasks] = new Todo(command.replaceFirst("todo", ""));
                        Todo.printTodoDescription();
                        keyword[numberOfTasks] = "T";
                        tasks[numberOfTasks++].printDescription();
                        System.out.print("\n");
                        printTotalTasks();
                    } else if (command.contains("deadline")) {
                        by = command.substring(command.indexOf("/") + 3);
                        command = command.substring(8, command.indexOf("/"));
                        tasks[numberOfTasks] = new Deadline(command, by);
                        Deadline.printDeadlineDescription();
                        keyword[numberOfTasks] = "D";
                        tasks[numberOfTasks++].printDescription();
                        System.out.println("(by:" + by + ")");
                        printTotalTasks();
                    } else if (command.contains("event")) {
                        at = command.substring(command.indexOf("/") + 3);
                        command = command.substring(5, command.indexOf("/"));
                        tasks[numberOfTasks] = new Event(command, at);
                        Event.printEventDescription();
                        keyword[numberOfTasks] = "E";
                        tasks[numberOfTasks++].printDescription();
                        System.out.println("(at:" + at + ")");
                        printTotalTasks();
                    } else {
                        System.out.println("Invalid command! Please try again.");
                    }
                }
                command = in.nextLine();
            } while (!command.equals("bye"));
            printBye();
        }
    }
