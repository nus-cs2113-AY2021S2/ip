package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        int taskCount = DataStorage.readFile(list);
        printWelcome();
        interact(list, taskCount);
        DataStorage.writeSaveData(list);
        printBye();
    }

    public static void interact(ArrayList<Task> list, int taskCount) {

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();


        String[] parsedInput = Parser.inputParser(input);
        String command = parsedInput[0];
        String arguments = parsedInput[1];

        while (!command.equalsIgnoreCase("bye")) {
            switch (command.toLowerCase()) {
            case "help":
                System.out.print(Constants.LINE + Constants.HELP_MESSAGE + Constants.LINE);
                break;
            case "list":
                printList(list, taskCount);
                break;
            case "todo":
                taskCount = addToDo(arguments, list, taskCount);
                break;
            case "deadline":
                taskCount = addDeadline(arguments, list, taskCount);
                break;
            case "event":
                taskCount = addEvent(arguments, list, taskCount);
                break;
            case "done":
                markAsDone(list, taskCount, arguments);
                break;
            case "undo":
                undoMarkAsDone(list, taskCount, arguments);
                break;
            case "delete":
                delete(list, taskCount, arguments);
                break;
            default:
                System.out.print(Constants.LINE + Constants.INVALID_COMMAND_MESSAGE + Constants.LINE);
            }
            input = scan.nextLine();

            parsedInput = Parser.inputParser(input);
            command = parsedInput[0];
            arguments = parsedInput[1];
        }
    }

    public static int addToDo(String input, ArrayList<Task> list, int index) {
        if(input == null) {
            System.out.print(Constants.LINE + Constants.NO_DESCRIPTION_MESSAGE + Constants.LINE);
        } else {
            list.add(new ToDo(input));
            System.out.print(Constants.LINE + "\"" + input + "\"" + Constants.ADD_MESSAGE + Constants.LINE);
            index++;
        }
        return index;
    }

    public static int addDeadline(String input, ArrayList<Task> list, int index) {
        if (input == null) {
            System.out.print(Constants.LINE + Constants.NO_DESCRIPTION_MESSAGE + Constants.LINE);
        } else if(input.toLowerCase().contains("/by")) {
            try {
                String desc = input.substring(0, input.toLowerCase().indexOf("/by") - 1);
                String dueDate = input.substring(input.toLowerCase().indexOf("/by") + 4);
                list.add(new Deadline(desc, dueDate));
                System.out.print(Constants.LINE + "\"" + desc + "\"" + Constants.ADD_MESSAGE +
                        "Please complete by: " + dueDate + "\n" + Constants.LINE);
                index++;
            } catch (Exception e) {
                System.out.print(Constants.LINE + Constants.INVALID_ARGUMENT_MESSAGE + Constants.LINE);
            }
        } else {
            System.out.print(Constants.LINE + Constants.INVALID_ARGUMENT_MESSAGE
                    + Constants.NO_DEADLINE_MESSAGE + Constants.LINE);
        }

        return index;
    }

    public static int addEvent(String input, ArrayList<Task> list, int taskCount) {
        if (input == null) {
            System.out.print(Constants.LINE + Constants.NO_DESCRIPTION_MESSAGE + Constants.LINE);
        } else if(input.toLowerCase().contains("/at")) {
            try {
                String desc = input.substring(0, input.toLowerCase().indexOf("/at")-1);
                String date = input.substring(input.toLowerCase().indexOf("/at")+4);
                list.add(new Event(desc, date));
                System.out.print(Constants.LINE + "\"" + desc + "\"" + Constants.ADD_MESSAGE +
                        "It occurs at: " + date + "\n" + Constants.LINE);
                taskCount++;
            } catch (Exception e) {
                System.out.print(Constants.LINE + Constants.INVALID_ARGUMENT_MESSAGE + Constants.LINE);
            }
        } else {
            System.out.print(Constants.LINE + Constants.INVALID_ARGUMENT_MESSAGE + Constants.NO_TIME_MESSAGE + Constants.LINE);
        }
        return taskCount;
    }

    public static void printList(ArrayList<Task> list, int taskCount) {
        if(taskCount == 0) {
            System.out.print(Constants.LINE + Constants.EMPTY_LIST_MESSAGE + Constants.LINE);
        } else {
            System.out.print(Constants.LINE);
            for (int i = 0; i < list.size(); i++) {
                if (i < 9) {
                    System.out.print(" ");
                }
                System.out.println(i + 1 + list.get(i).toString());
            }
            System.out.print("There are " + Task.getTasksRemaining() + " undone task(s) on the list\n" + Constants.LINE);
        }
    }

    public static void markAsDone(ArrayList<Task> list, int max, String input) {
        try {
            int taskNo = Integer.parseInt(input);
            if (taskNo <= max && taskNo > 0) { //no. is valid
                if (list.get(taskNo - 1).getStatus()) {
                    System.out.print(Constants.LINE + "Task \"" + list.get(taskNo - 1).getDesc() + "\""
                            + Constants.TASK_ALREADY_CHECKED_MESSAGE + Constants.LINE);
                } else {
                    list.get(taskNo - 1).check();
                    System.out.print(Constants.LINE + Constants.TASK_CHECKED_MESSAGE);
                    System.out.println("  " + list.get(taskNo - 1).getStatusSymbol() + list.get(taskNo - 1).getDesc());
                    if (Task.getTasksRemaining() == 0) {
                        System.out.print(Constants.ALL_TASKS_CHECKED_MESSAGE);
                    }
                    System.out.print(Constants.LINE);
                }
            } else {
                printInvalidArgumentMessage();
            }
        } catch (Exception e) {
            printInvalidArgumentMessage();
        }
    }

    public static void undoMarkAsDone(ArrayList<Task> list, int max, String input) {
        try {
            int taskNo = Integer.parseInt(input);
            if (taskNo <= max && taskNo > 0) { //no. is valid
                if (!list.get(taskNo - 1).getStatus()) {
                    System.out.print(Constants.LINE + "Task \"" + list.get(taskNo - 1).getDesc() + "\""
                            + Constants.TASK_NOT_CHECKED_MESSAGE + Constants.LINE);
                } else {
                    list.get(taskNo - 1).uncheck();
                    System.out.print(Constants.LINE + Constants.TASK_UNCHECKED_MESSAGE);
                    System.out.println("  " + list.get(taskNo - 1).getStatusSymbol() + list.get(taskNo - 1).getDesc());
                    System.out.print(Constants.LINE);
                }
            } else {
                printInvalidArgumentMessage();
            }
        } catch (Exception e) {
            printInvalidArgumentMessage();
        }
    }

    private static void delete(ArrayList<Task> list, int max, String input) {
        try {
            int taskNo = Integer.parseInt(input);
            if (taskNo <= max && taskNo > 0) {
                System.out.print(Constants.LINE + Constants.TASK_DELETED_MESSAGE
                        + list.get(taskNo - 1).toString() + "\n" + Constants.LINE);
                list.get(taskNo - 1).remove();
                list.remove(taskNo - 1);
            } else {
                printInvalidArgumentMessage();
            }
        } catch (Exception e) {
            printInvalidArgumentMessage();
        }
    }

    private static void printWelcome() {
        System.out.print(Constants.WELCOME_MESSAGE);
    }

    private static void printBye() {
        System.out.print(Constants.BYE_MESSAGE);
    }

    private static void printInvalidArgumentMessage() {
        System.out.print(Constants.LINE + Constants.INVALID_ARGUMENT_MESSAGE + Constants.LINE);
    }
}
