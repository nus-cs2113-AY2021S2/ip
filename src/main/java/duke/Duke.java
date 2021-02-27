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
        Ui.printWelcomeMessage();
        interact(list, taskCount);
        DataStorage.writeSaveData(list);
        Ui.printByeMessage();
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
                Ui.printHelpMessage();
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
                Ui.printInvalidCommandMessage();
            }
            input = scan.nextLine();

            parsedInput = Parser.inputParser(input);
            command = parsedInput[0];
            arguments = parsedInput[1];
        }
    }

    public static int addToDo(String input, ArrayList<Task> list, int index) {
        if(input == null) {
            Ui.printInvalidArgumentMessage(Constants.NO_DESCRIPTION_MESSAGE);
        } else {
            list.add(new ToDo(input));
            Ui.printToDoAdded(input);
            index++;
        }
        return index;
    }

    public static int addDeadline(String input, ArrayList<Task> list, int index) {
        if (input == null) {
            Ui.printInvalidArgumentMessage(Constants.NO_DESCRIPTION_MESSAGE);
        } else if(input.toLowerCase().contains("/by")) {
            try {
                String desc = input.substring(0, input.toLowerCase().indexOf("/by") - 1);
                String dueDate = input.substring(input.toLowerCase().indexOf("/by") + 4);
                list.add(new Deadline(desc, dueDate));
                Ui.printDeadlineAdded(desc, dueDate);
                index++;
            } catch (Exception e) {
                Ui.printGenericErrorMessage();
            }
        } else {
            Ui.printInvalidArgumentMessage(Constants.NO_DEADLINE_MESSAGE);
        }

        return index;
    }

    public static int addEvent(String input, ArrayList<Task> list, int taskCount) {
        if (input == null) {
            Ui.printInvalidArgumentMessage(Constants.NO_DESCRIPTION_MESSAGE);
        } else if(input.toLowerCase().contains("/at")) {
            try {
                String description = input.substring(0, input.toLowerCase().indexOf("/at")-1);
                String time = input.substring(input.toLowerCase().indexOf("/at")+4);
                list.add(new Event(description, time));
                Ui.printEventAdded(description, time);
                taskCount++;
            } catch (Exception e) {
                Ui.printGenericErrorMessage();
            }
        } else {
                Ui.printInvalidArgumentMessage(Constants.NO_TIME_MESSAGE);
        }
        return taskCount;
    }

    public static void printList(ArrayList<Task> list, int taskCount) {
        if(taskCount == 0) {
            Ui.printEmptyListMessage();
        } else {
            ArrayList<String> taskList = new ArrayList<>();
            StringBuilder task = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (i < 9) {
                    task.append(" ");
                }
                task.append(i+1).append(list.get(i).toString());
                taskList.add(task.toString());
                task.setLength(0);
            }
            taskList.add(String.format(Constants.UNDONE_TASKS_REMAINING_MESSAGE, Task.getTasksRemaining()));
            Ui.dukePrinter(taskList);
        }
    }

    public static void markAsDone(ArrayList<Task> list, int max, String input) {
        try {
            int taskNo = Integer.parseInt(input);
            if (taskNo <= max && taskNo > 0) { //no. is valid
                if (list.get(taskNo - 1).getStatus()) {
                    Ui.printTaskAlreadyCheckedMessage(taskNo, list.get(taskNo - 1).getDesc());
                } else {
                    list.get(taskNo - 1).check();
                    Ui.printTaskChecked(taskNo, list.get(taskNo - 1).getStatusSymbol(),
                            list.get(taskNo - 1).getDesc(), Task.getTasksRemaining());
                }
            } else {
                Ui.printInvalidArgumentMessage(String.format(Constants.TASK_DOES_NOT_EXIST_MESSAGE, taskNo));
            }
        } catch (Exception e) {
            Ui.printGenericErrorMessage();
        }
    }

    public static void undoMarkAsDone(ArrayList<Task> list, int max, String input) {
        try {
            int taskNo = Integer.parseInt(input);
            if (taskNo <= max && taskNo > 0) { //no. is valid
                if (!list.get(taskNo - 1).getStatus()) {
                    Ui.printTaskNotCheckedMessage(taskNo, list.get(taskNo - 1).getDesc());
                } else {
                    list.get(taskNo - 1).uncheck();
                    Ui.printTaskUnchecked(taskNo, list.get(taskNo - 1).getStatusSymbol(),
                            list.get(taskNo - 1).getDesc());
                }
            } else {
                Ui.printInvalidArgumentMessage(String.format(Constants.TASK_DOES_NOT_EXIST_MESSAGE, taskNo));
            }
        } catch (Exception e) {
            Ui.printGenericErrorMessage();
        }
    }

    private static void delete(ArrayList<Task> list, int max, String input) {
        try {
            int taskNo = Integer.parseInt(input);
            if (taskNo <= max && taskNo > 0) {
                Ui.printTaskDeleted(taskNo, list.get(taskNo - 1).toString());
                list.get(taskNo - 1).remove();
                list.remove(taskNo - 1);
            } else {
                Ui.printInvalidArgumentMessage(String.format(Constants.TASK_DOES_NOT_EXIST_MESSAGE, taskNo));
            }
        } catch (Exception e) {
            Ui.printGenericErrorMessage();
        }
    }
}
