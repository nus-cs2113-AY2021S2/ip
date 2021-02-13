package duke;

import duke.taskexceptions.EmptyTaskDateException;
import duke.taskexceptions.NoTaskDateException;
import duke.taskexceptions.NoTaskNameException;
import duke.taskexceptions.TaskDateFormatException;

import duke.tasksmanager.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static String line = "____________________________________________________________";
    public static final int INPUT_PHRASES_COUNT = 2;

    public static ArrayList<Tasks> tasks = new ArrayList<>(); //for storing (all types of) tasks
    public static int taskCount = 0; //for counting tasks
    public static String taskInputString; //contains taskName and taskDate (from user's input)
    public static String taskName;
    public static String taskDate;

    public static File dukePrevInputList = new File("data/dukeList.txt");
    // creates a File object to represent a file duke.txt
    // that exists in the data directory relative to the current working directory

    /**
     * Prints some lines to welcome the user:
     */
    public static void saysHiToUser() {
        //Greeting:
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Prints some lines when user exits:
     */
    public static void saysByeToUser() {
        //exits with "bye":
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!"); //exits
        System.out.println(line);
    }

    /**
     * Takes in User's input string
     * Adds todo type task to 'tasks' array
     * Prints added todo task
     *
     * @param input - user's input string
     */
    public static void addToDo(String input) {
        try {
            separateTypeOfTaskAndTaskInputString(input);
            taskName = taskInputString;
            tasks.add(new ToDos(taskName)); //add task to list

            System.out.println("Got it. I've added this task:");
            printAddedTask();

        } catch (NoTaskNameException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Takes in User's input string
     * Adds deadline type task to 'tasks' array
     * Prints added deadline task
     *
     * @param input - user's input string
     */
    public static void addDeadline(String input) {
        try {
            separateTypeOfTaskAndTaskInputString(input);
            splitTaskNameAndDate(taskInputString);
            tasks.add(new Deadlines(taskName, taskDate)); //add task to list

            System.out.println("Got it. I've added this task:");
            printAddedTask();

        } catch (NoTaskNameException e) {
            System.out.println("☹ OOPS!!! The description of a Deadline task cannot be empty.");
        } catch (TaskDateFormatException e) {
            System.out.println("Please add date in the format of \"Task /by: Date\"."); //wrong date format
        } catch (NoTaskDateException e) {
            System.out.println("Please add a date to the Deadline description. :))"); //missing date
        } catch (EmptyTaskDateException e) {
            System.out.println("Please add a date to the Deadline task description. :))"); //empty date
        }
    }

    /**
     * Takes in User's input string
     * Adds event type task to 'tasks' array
     * Prints added event task
     *
     * @param input - user's input string
     */
    public static void addEvent(String input) {
        try {
            separateTypeOfTaskAndTaskInputString(input);
            splitTaskNameAndDate(taskInputString);
            tasks.add(new Events(taskName, taskDate)); //add task to list

            System.out.println("Got it. I've added this task:");
            printAddedTask();

        } catch (NoTaskNameException e) {
            System.out.println("☹ OOPS!!! The description of a Event task cannot be empty.");
        } catch (TaskDateFormatException e) {
            System.out.println("Please add date in the format of \"Task /at: Date\"."); //wrong date format
        } catch (NoTaskDateException e) {
            System.out.println("Please add a date to the Event description. :))"); //missing date
        } catch (EmptyTaskDateException e) {
            System.out.println("Please add a date to the Event task description. :))"); //empty date
        }
    }

    /**
     * Takes in User's input string
     * Splits into 2 parts, the command 'delete' & TaskNumber
     * NumberFormatException() - when user does not input a TaskNumber
     * Obtains Index of Task to be deleted from it's taskNumber
     *
     * IndexOutOfBoundsException() - when user inputs an 'out of range' taskNumber
     * Removes Task from 'tasks' array
     *
     * Prints deleted task
     *
     * @param input - user's input string - format: 'delete taskNumber'
     */
    private static void deleteAndPrintTask(String input) {
        try {
            String[] commandAndTaskNumber = input.split(" ");
            if (commandAndTaskNumber.length < 2) {
                throw new NumberFormatException(); //throws NumberFormatException() when user does not input a number after word 'done'
            }
            int index = Integer.parseInt(commandAndTaskNumber[1]) - 1; //obtain index from task number(which starts from 1)

            Tasks deletedTask = tasks.get(index); //throws IndexOutOfBoundsException() if taskNumber out of bounds
            tasks.remove(index); //remove task
            taskCount--;

            System.out.println("Noted. I've removed this task: ");
            System.out.println(" " + deletedTask.convertToTaskOutputString());

        } catch (NumberFormatException e) {
            System.out.println("Please input in the format of \'delete taskNumber\'"); //wrong format for TaskNumber
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input a smaller or bigger valid task number.");
            System.out.println("You can list all tasks to check the total number of tasks you have. :))"); //invalid TaskNumber
        }
    }

    /**
     * Takes in User's input string
     * Splits into 2 parts, the command 'done' & TaskNumber
     * NumberFormatException() - when user does not input a TaskNumber
     * Obtains Index of Task to be deleted from it's taskNumber
     *
     * IndexOutOfBoundsException() - when user inputs an 'out of range' taskNumber
     * Marks Task as done (in 'tasks' array)
     *
     * Prints marked task
     *
     * @param input - user's input string - format: 'delete taskNumber'
     */
    public static void markAndPrintsTaskAsDone(String input) {
        try {
            String[] commandAndTaskNumber = input.split(" ");
            if (commandAndTaskNumber.length < 2) {
                throw new NumberFormatException(); //throws NumberFormatException() when user does not input a number after word 'done'
            }
            int index = Integer.parseInt(commandAndTaskNumber[1]) - 1; //obtain index from task number(which starts from 1)

            Tasks taskDone = tasks.get(index); //throws IndexOutOfBoundsException() if taskNumber out of bounds
            taskDone.markAsDone(); //mark task given by current command as 'done'

            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" " + taskDone.convertToTaskOutputString());

        } catch (NumberFormatException e) {
            System.out.println("Please input in the format of \'done taskNumber\'"); //wrong format for TaskNumber
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please input a smaller or bigger valid task number.");
            System.out.println("You can list all tasks to check the total number of tasks you have. :))"); //invalid TaskNumber
        }
    }

    /**
     * Prints all tasks in current List 'tasks'
     * */
    private static void printsList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            int taskNumber = i+1; //stores the current numbering of the bulleted tasks
            System.out.println(taskNumber + "." + tasks.get(i).convertToTaskOutputString());
        }
    }


    //END OF METHODS USED IN MAIN FUNCTION

    //Methods used by the 'Main' methods above:
    /**
     * Removes taskType from user's entire input string
     * Stores remaining string (with taskName and taskDate combined) in static variable taskInputString
     *
     * throws exception if user string does not contain proper taskName (taskName, taskDate etc.)
     * @param input - entire input string of the user, made of taskType + taskName + taskDate
     */
    public static void separateTypeOfTaskAndTaskInputString(String input) throws NoTaskNameException {
        //find position between taskType and rest of task description:
        int taskInputStringPosition = input.indexOf(" ") + 1;
        taskInputString = input.substring(taskInputStringPosition);

        boolean hasNoTaskInputString = false;
        if(taskInputStringPosition == 0) {
            hasNoTaskInputString = true; //since input.indexOf(" ") returns -1 if no TaskName
        }

        if (hasNoTaskInputString || taskInputString.isBlank() || taskInputString.startsWith("/")) {
            throw new NoTaskNameException();
        }
    }

    /**
     * Takes in the remaining 'taskInputString' of the user's input
     * Splits it into two parts, then stores into TaskName
     * and TaskDateString of the task
     *
     * Splits TaskDateString into 2 parts, complement word "by:" or "at:" & taskDate (further split by " ")
     *
     * TaskDateFormatException() - TaskDateString is written in an incorrect format
     * NoTaskDateException() - no date after '/at' or '/by'
     * EmptyTaskDateException() - empty date after '/at' or '/by'
     *
     * End Results: TaskName and TaskDate
     * @param taskInput - essentially taskInputString, which does not include taskType
     */
    public static void splitTaskNameAndDate(String taskInput) throws TaskDateFormatException, NoTaskDateException, EmptyTaskDateException {
        int beforeDatePosition = taskInput.indexOf("/");
        if (beforeDatePosition == -1) {
            throw new TaskDateFormatException(); //if no '/', asks user to change TaskDateString format
        }
        taskName = taskInput.substring(0, beforeDatePosition);

        int dateStringPosition = beforeDatePosition + 1;
        String taskDateString = taskInput.substring(dateStringPosition);

        String[] taskDateStringWord = taskDateString.split(" ");
        if (!(taskDateStringWord[0].equals("at") || taskDateStringWord[0].equals("by"))) {
            throw new TaskDateFormatException(); //if incorrect complement word in non-empty string after '/', asks user to change TaskDateString format
        }

        int datePosition = taskDateString.indexOf(" ") + 1;
        if (datePosition == 0) {
            throw new NoTaskDateException(); //if no date after '/at:' or '/by:', asks user to 'add date'
        }
        taskDate = taskDateString.substring(datePosition);
        if (taskDate.isBlank()) {
            throw new EmptyTaskDateException(); //if empty date after '/at:' or '/by:', asks user to 'add date'
        }
    }

    /**
     * Prints out the taskType, status, taskName and taskDate of the task added by the user
     * and prints the current total number of tasks in the user's list
     * Add to total taskCount (since new task is added)
     */
    public static void printAddedTask() {
        System.out.println("  " + tasks.get(taskCount).convertToTaskOutputString()); //prints task added
        taskCount++;
        //prints current total number of tasks (in the list of tasks):
        System.out.print("Now you have " + taskCount + " task");
        if (taskCount > 1) {
            System.out.print("s");
        }
        System.out.println(" in the list.");
    }

    //MAIN:
    public static void main(String[] args) {

        try {
            loadPrevListIntoNewList(); //load previous ArrayList from local hard disk
        } catch (IOException e) {
            System.out.println("IO exception :O");
        }

        saysHiToUser();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine(); //take in User's first input:
        //list, mark or add user's tasks (until user inputs "bye"):
        while (!input.equals("bye")) {

            System.out.println(line); //start of current response to User

            //add and store task to list of tasks
            if (input.startsWith("todo")) {
                addToDo(input);
            } else if (input.startsWith("deadline")) {
                addDeadline(input);
            } else if (input.startsWith("event")) {
                addEvent(input);
            }
            //OR: remove task from list of tasks
            else if (input.startsWith("delete")) {
                deleteAndPrintTask(input);
            }
            //OR: mark current task as 'done' & outputs the taskType,taskStatus,taskName(and taskDate):
            else if (input.startsWith("done")) {
                markAndPrintsTaskAsDone(input);
            }
            //OR: lists all the user's current tasks in the format of taskType,taskStatus,taskName(and taskDate):
            else if (input.startsWith("list")) {
                printsList();
            }
            //OR: deal with invalid command
            else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            System.out.println(line); //end of current response to User
            //takes in next input:
            input = in.nextLine();

        }

        //write to file:
        try {
            PrintWriter writer = new PrintWriter("data/dukeList.txt");
            writer.print("");
            writer.close();
            for (int i = 0; i < taskCount; i++) {
                Tasks currentTask = tasks.get(i);
                appendToFile(currentTask.typeOfTask + "/" +
                        currentTask.isDone + "/" +
                        currentTask.description);
                if (currentTask.typeOfTask.equals("D") || currentTask.typeOfTask.equals("E")) {
                    appendToFile("/" + currentTask.date);
                }
                appendToFile(System.lineSeparator());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Write: File not found");
        } catch (IOException e) {
            System.out.print("Unable to write to file");
        }

        saysByeToUser();
    }

    /**
     * If file exists, loads older data from file into ArrayList 'tasks'
     * Else, creates new file
     * In both cases, file used to store new data in this iteration of Duke Main
     * @throws IOException
     */
    private static void loadPrevListIntoNewList() throws IOException {
        try {
            Scanner s = new Scanner(dukePrevInputList); // create a Scanner using the File as the source
            // add one todo, deadline OR event task
            // based on current entry:
            while (s.hasNext()) {
                String[] prevListEntryWord = s.nextLine().split("/");
                switch (prevListEntryWord[0]) {
                case "T":
                    tasks.add(new ToDos(prevListEntryWord[2]));
                    break;
                case "D":
                    tasks.add(new Deadlines(prevListEntryWord[2], prevListEntryWord[3]));
                    break;
                case "E":
                    tasks.add(new Events(prevListEntryWord[2], prevListEntryWord[3]));
                    break;
                default:
                    break;
                }
                if (prevListEntryWord[1].equals("true")) {
                    tasks.get(taskCount).markAsDone(); //if is a 'no', task is auto-marked as not done
                }
                taskCount++; //increment taskCount for added task
            }

        } catch (FileNotFoundException e) {
            //For the first time, create a new file for the user:
            File dataDirectory = new File("data");
            dataDirectory.mkdir();
            File dukeFile = new File("data","dukeList.txt");
            dukeFile.createNewFile();
        }
    }

    private static void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("data/dukeList.txt", true);
        fw.write(textToAdd);
        fw.close();
    }

}
