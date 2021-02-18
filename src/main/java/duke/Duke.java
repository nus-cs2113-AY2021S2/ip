package duke;

import duke.tasks.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void dividerLine() {
        System.out.println("\t________________________________________\n");
    }

    public static void takeInput() {
        Scanner scannerObject = new Scanner(System.in);
        while (scannerObject.hasNextLine()) {
            String command = scannerObject.nextLine();
            if (command.equals("bye") || command.equals("exit")) {
                dividerLine();
                exitDuke();
                dividerLine();
                scannerObject.close();
                break;
            } else if (command.equals("list")) {
                dividerLine();
                list();
                dividerLine();
            } else if (command.startsWith("done")) {
                dividerLine();
                finishTask(command);
                dividerLine();
            } else if (command.startsWith("todo")) {
                dividerLine();
                addTodo(command);
                dividerLine();
            } else if (command.startsWith("event")) {
                dividerLine();
                addEvent(command);
                dividerLine();
            } else if (command.startsWith("deadline")) {
                dividerLine();
                addDeadline(command);
                dividerLine();
            } else if (command.startsWith("delete")) {
                dividerLine();
                deleteTask(command);
                dividerLine();
            } else {
                dividerLine();
                unknownCommand();
                dividerLine();
            }
        }
    }
    
    public static String printUsage(String type) {
        switch(type) {
        case "todo":
            return "todo [task description]";
        case "event":
            return "event [event description] /at [date]";
        case "deadline":
            return "deadline [task description] /by [due date]";
        case "done":
            return "done [index of task]";
        case "delete":
            return "delete [index of task]";
        default:
            return null;
        }
    }
    
    public static void unknownCommand() {
        System.out.println("\tSorry, this command is not available yet. Stay tuned! :)");
    }
    
    public static void list() {
        if (tasks.size()==0) {
            System.out.println("\tYou have no tasks in your list");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i=0; i<tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                System.out.println("\t" + Integer.toString(i+1) + ".[" + currentTask.getType() + "]["
                        + currentTask.getStatusIcon() + "] " + currentTask.getDescription() + " " 
                        + currentTask.getDate());
            }
        }
    }

    public static void addTodo(String command) {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length==1) {
            System.out.println("\t!!! Please specify the task description !!!");
            System.out.println("\tUsage: " + printUsage("todo"));
        } else {
            String todo = splitCommand[1];
            Todo t = new Todo(todo);
            tasks.add(t);
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t   [" + t.getType() + "][" + t.getStatusIcon() + "] " + t.getDescription());
            if (tasks.size()==1) {
                System.out.println("\tNow you have 1 task in the list.");
            } else {
                System.out.println("\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
            }
        }
    }

    public static void addEvent(String command) {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length==1) {
            System.out.println("\t!!! Please specify event description !!!");
            System.out.println("\tUsage: " + printUsage("event"));
        } else {
            try {
                String description = splitCommand[1].split("/at")[0].strip();
                String date = splitCommand[1].split("/at")[1].strip();
                Event t = new Event(description, date);
                tasks.add(t);
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t   [" + t.getType() + "][" + t.getStatusIcon() + "] "
                        + t.getDescription() + " " + t.getDate());
                System.out.println("\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\t!!! Please specify event date !!!");
                System.out.println("\tUsage: " + printUsage("event"));
            }
        }
    }

    public static void addDeadline(String command) {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length==1) {
            System.out.println("\t!!! Please specify the task description !!!");
            System.out.println("\tUsage: " + printUsage("deadline"));
        } else {
            try {
                String description = splitCommand[1].split("/by")[0].strip();
                String date = splitCommand[1].split("/by")[1].strip();
                Deadline t = new Deadline(description, date);
                tasks.add(t);
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t   [" + t.getType() + "][" + t.getStatusIcon() + "] "
                        + t.getDescription() + " " + t.getDate());
                System.out.println("\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\t!!! Please specify the task due date !!!");
                System.out.println("\tUsage: " + printUsage("deadline"));
            }
        }
    }

    public static void finishTask(String command) {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length==1) {
            System.out.println("\t!!! Please specify the index of the task done !!!");
            System.out.println("\tUsage: " + printUsage("done"));
        } else {
            int itemIndex = Integer.parseInt(splitCommand[1])-1;
            if (itemIndex >= tasks.size() || itemIndex < 0) {
                System.out.println("\t!!! Item does not exist !!!");
                list();
            } else if (tasks.get(itemIndex).isDone()) {
                System.out.println("\tThis task is already done :)");
                Task currentTask = tasks.get(itemIndex);
                System.out.println("\t  [" + currentTask.getType() + "][" + currentTask.getStatusIcon() + "] "
                        + currentTask.getDescription());
            } else {
                tasks.get(itemIndex).markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
                Task currenTask = tasks.get(itemIndex);
                System.out.println("\t  [" + currenTask.getType() + "][" + currenTask.getStatusIcon() + "] "
                        + currenTask.getDescription());
            }
        }
    }

    public static void deleteTask(String command) {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length==1) {
            System.out.println("\t!!! Please specify the index of the task to be deleted !!!");
            System.out.println("\tUsage: " + printUsage("delete"));
        } else {
            int itemIndex = Integer.parseInt(splitCommand[1])-1;
            if (itemIndex >= tasks.size() || itemIndex < 0) {
                System.out.println("\t!!! Item does not exist !!!");
                list();
            } else {
                System.out.println("\tNoted. I've removed this task:");
                Task currentTask = tasks.get(itemIndex);
                System.out.println("\t  [" + currentTask.getType() + "][" + currentTask.getStatusIcon() + "] "
                        + currentTask.getDescription());
                tasks.remove(itemIndex);
                if (tasks.size()==1) {
                    System.out.println("\tNow you have 1 task in the list.");
                } else {
                    System.out.println("\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
                }
            }
        }
    }

    public static void exitDuke() {
        System.out.println("\tBye, hope to see you again soon!");
    }

    public static void main(String[] args) {
        dividerLine();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        dividerLine();
        takeInput();
    }
}
