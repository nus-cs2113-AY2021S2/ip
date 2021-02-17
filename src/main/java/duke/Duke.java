package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static duke.FileManager.*;

public class Duke {
    /**
     * Command line output constants
     * */
    final static String DIVLINE = "\t__________________________________________________________________\n";
    final static String GREETINGS = "\tHello! I'm Ayanga, your personal task manager.\n" +
            "\tWhat can I note down for you?\n" +
            "\tTo add a todo item, simply write \"todo <DESCRIPTION>\".\n" +
            "\tTo add a deadline, write \"deadline <DESCRIPTION> /by <TIME>\".\n" +
            "\tTo add an event, write \"event <DESCRIPTION> /at <VENUE>\".\n" +
            "\tSay \"list\" and I will display your tasks.\n" +
            "\tWrite \"done <NUMBER OF TASK>\" to let me know when you have completed a task.\n" +
            "\tWave \"bye\" to me if you don't need me for now.\n";
    final static String PARTINGS = "\tBye. Hope you have done your work next time I see you!\n" +
            "\tAh, and also remember to take care of yourself and sleep early :)\n";
    private static boolean isExiting = false;       // control the loop in main
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Deals with raw input, extracts keyword and calls respective methods.
     * Catches the exceptions of each method and print corresponding response.
     * Throws an exception if keyword is invalid.
     * @param   prompt  is the raw input string
     * */
    public static void processPrompt(String prompt) throws DukeException {
        String keyword = prompt.contains(" ") ? prompt.split(" ")[0] : prompt;
        switch (keyword) {
        case "bye":
            isExiting = true;
            break;
        case "list":
            try {
                displayList();
            } catch (DukeException e) {
                System.out.println(DIVLINE + e.getEmptyListMessage());
                System.out.print(DIVLINE);
            }
            break;
        case "done":
            try {
                int taskIndex = Integer.parseInt(prompt.substring(5)) - 1;
                completeTask(taskIndex);
            } catch (NumberFormatException e) {
                System.out.println(DIVLINE + "\t:( OOPS!!! You are not specifying a valid task number.");
                System.out.print(DIVLINE);
            } catch (DukeException e) {
                System.out.println(DIVLINE + e.getTaskAlreadyDoneMessage());
                System.out.print(DIVLINE);
            }
            break;
        case "deadline":
            try {
                addDeadlineToList(prompt.substring(8));
            } catch (DukeException e) {
                System.out.println(DIVLINE + e.getIllegalDeadlineMessage());
                System.out.print(DIVLINE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case "event":
            try {
                addEventToList(prompt.substring(5));
            } catch (DukeException e) {
                System.out.println(DIVLINE + e.getIllegalEventMessage());
                System.out.print(DIVLINE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case "todo":
            try {
                addTodoToList(prompt.substring(4));
            } catch (DukeException e) {
                System.out.println(DIVLINE + e.getIllegalTodoMessage());
                System.out.print(DIVLINE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case "delete":
            try {
                int taskIndex = Integer.parseInt(prompt.substring(7)) - 1;
                deleteFromList(taskIndex);
            } catch (NumberFormatException e) {
                System.out.println(DIVLINE + "\t:( OOPS!!! You are not specifying a valid task number.");
                System.out.print(DIVLINE);
            } catch (DukeException e) {
                System.out.println(DIVLINE + "Target task does not exist or you have specified nothing.");
                System.out.print(DIVLINE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        default:
            throw new DukeException("Illegal keyword.");
        }
         return;
    }

    /**
     * */
    private static void displayList() throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException("Empty list. Nothing to be displayed.");
        }
        System.out.print(DIVLINE);
        System.out.println("\tHere are the tasks in your list:");
        // Create an iterator for the list
        Iterator<Task> iter = taskList.iterator();
        // Displaying the values after iterating through the list
        int counter = 0;
        while (iter.hasNext()) {
            counter++;
            System.out.println("\t" + counter + "." + iter.next().toString());
        }
        System.out.print(DIVLINE);
    }

    private static void addDeadlineToList(String description) throws DukeException, IOException {
        if (description.startsWith(" ")){
            String ddlDscp = description.substring(1);
            int splitPoint = ddlDscp.indexOf("/by");
            if (splitPoint==-1){
                throw new DukeException("Illegal Deadline prompt detected.");
            }
            Deadline newDdl = new Deadline(ddlDscp.substring(0, splitPoint - 1),
                    ddlDscp.substring(splitPoint + 4));
            taskList.add(newDdl);
            writeToFile(initializeTextToWrite());
            printAddSuccessMessage(newDdl);
        } else {
            throw new DukeException("Illegal Deadline prompt detected.");
        }
    }

    private static void addEventToList(String description) throws DukeException, IOException {
        if (description.startsWith(" ")){
            String evtDscp = description.substring(1);
            int splitPoint = evtDscp.indexOf("/at");
            if (splitPoint==-1){
                throw new DukeException("Illegal Event prompt detected.");
            }
            Event newEvt = new Event(evtDscp.substring(0, splitPoint - 1),
                    evtDscp.substring(splitPoint + 4));
            taskList.add(newEvt);
            writeToFile(initializeTextToWrite());
            printAddSuccessMessage(newEvt);
        } else {
            throw new DukeException("Illegal Event prompt detected.");
        }
    }

    private static void addTodoToList(String description) throws DukeException, IOException {
        if (description.startsWith(" ")) {
            Task newTodo = new Todo(description.substring(1));
            taskList.add(newTodo);
            writeToFile(initializeTextToWrite());
            printAddSuccessMessage(newTodo);
        } else {
            throw new DukeException("Illegal Todo Prompt detected.");
        }
    }

    private static void printAddSuccessMessage(Task task) {
        System.out.print(DIVLINE);
        System.out.println("\tGot it. I've added this task:\n" +
                "\t  " + task.toString());
        System.out.println("\tNow you have " + taskList.size() + " tasks in the list.");
        System.out.print(DIVLINE);
    }

    private static void printDeleteSuccessMessage(Task task) {
        System.out.print(DIVLINE);
        System.out.println("\tNoted. I've removed this task: \n" +
                "\t  " + task.toString());
        System.out.println("\tNow you have " + taskList.size() + " tasks in the list.");
        System.out.print(DIVLINE);
    }

    private static void completeTask(int taskIndex) throws DukeException {
        if (taskList.get(taskIndex).getIsDone()){
            throw new DukeException("Task has already been marked done.");
        }
        taskList.get(taskIndex).markAsDone();
        System.out.print(DIVLINE);
        System.out.println("\tNice! I've marked this task as done: \n" +
                "\t" + taskList.get(taskIndex).toString());
        System.out.print(DIVLINE);
    }

    private static void deleteFromList(int taskIndex) throws DukeException, IOException {
        if (taskIndex >= taskList.size()) {
            throw new DukeException("Task does not exist");
        }
        Task toBeDeleted = taskList.get(taskIndex);
        // Some manipulation here
        taskList.remove(taskIndex);
        writeToFile(initializeTextToWrite());
        printDeleteSuccessMessage(toBeDeleted);
    }

    public static void main(String[] args) throws IOException {
        createFileIfNotExist();
        ArrayList<String> textInput = readFile();
        initializeTaskList(textInput);
        System.out.print(DIVLINE + GREETINGS + DIVLINE);
        Scanner in = new Scanner(System.in);
        while (!isExiting) {
            try{
                String prompt = in.nextLine();
                processPrompt(prompt);
            } catch (DukeException e) {
                System.out.println(DIVLINE + e.getIllegalKeywordMessage());
                System.out.print(DIVLINE);
            }
        }
        // writeToFile(initializeTextToWrite());
        System.out.println(DIVLINE + PARTINGS + DIVLINE);
    }

    private static String initializeTextToWrite() {
        String text = "";
        for (int i=0; i<taskList.size(); i++) {
            text = text + taskList.get(i).toText(i+1)+ System.lineSeparator();
        }
        return text;
    }

    private static void initializeTaskList(ArrayList<String> fileInput) {
        for (int i=0; i<fileInput.size(); i++) {
            //create tasklist according to file storage
            String[] arr = fileInput.get(i).split("\\|");
            switch (arr[0]) {
                case "T":
                    taskList.add(new Todo(arr[2]));
                    break;
                case "D":
                    taskList.add(new Deadline(arr[2], arr[3]));
                    break;
                case "E":
                    taskList.add(new Event(arr[2], arr[3]));
                    break;
                default:
            }
        }
    }


}
